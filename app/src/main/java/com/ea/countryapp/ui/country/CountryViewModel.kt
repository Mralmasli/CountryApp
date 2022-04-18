package com.ea.countryapp.ui.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ea.domain.interactor.GetCountryDetailUseCase
import com.ea.domain.interactor.GetCountryListUseCase
import com.ea.domain.model.country.CountryDO
import com.ea.domain.model.wrapper.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountryListUseCase: GetCountryListUseCase,
    private val getCountryDetailUseCase: GetCountryDetailUseCase
) : ViewModel() {

    private val _countryList = MutableStateFlow<UiState<List<CountryDO>>>(UiState.Empty)
    val countryList = _countryList.asSharedFlow()

    private val _countryDetail = MutableStateFlow<UiState<CountryDO?>>(UiState.Empty)
    val countryDetail = _countryDetail.asSharedFlow()

    fun getCountryList() {
        _countryList.value = UiState.Loading
        viewModelScope.launch {
            when (val response =
                getCountryListUseCase.execute(GetCountryListUseCase.EmptyRequest)) {
                is ResultWrapper.Error -> {
                    _countryList.value =
                        UiState.Error(response.rawResponse.message ?: UNEXPECTED_ERROR)
                }
                is ResultWrapper.Success -> {
                    _countryList.value = UiState.Success(result = response.result.data ?: listOf())
                }
            }
        }
    }

    fun getCountryDetail(code: String) {
        _countryDetail.value = UiState.Loading
        viewModelScope.launch {
            when (val response =
                getCountryDetailUseCase.execute(GetCountryDetailUseCase.Params(code))) {
                is ResultWrapper.Error -> {
                    _countryDetail.value =
                        UiState.Error(response.rawResponse.message ?: UNEXPECTED_ERROR)
                }
                is ResultWrapper.Success -> {
                    _countryDetail.value = UiState.Success(result = response.result.data)
                }
            }
        }
    }

    companion object {
        const val UNEXPECTED_ERROR = "Unexpected error"
    }

}

sealed class UiState<out T> {
    object Empty : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Error(val message: String) : UiState<Nothing>()
    data class Success<out T>(val result: T) : UiState<T>()
}