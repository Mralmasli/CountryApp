package com.ea.countryapp.ui.country

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ea.countryapp.R
import com.ea.countryapp.databinding.FragmentItemDetailBinding
import com.ea.domain.model.country.CountryDO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CountryDetailFragment : Fragment() {
    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!
    private val countryViewModel: CountryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                countryViewModel.getCountryDetail(it.getString(ARG_ITEM_ID)?:"AZ")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver(){
        lifecycleScope.launchWhenResumed {
            countryViewModel.countryDetail.collectLatest { state->
                when(state){
                    UiState.Empty -> {}
                    is UiState.Error -> {
                        Toast.makeText(requireContext(),state.message,Toast.LENGTH_SHORT).show()
                    }
                    UiState.Loading -> {}
                    is UiState.Success -> {
                        updateContent(state.result)
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateContent(countryDO: CountryDO?) {
        binding.tvCountryName.text = countryDO?.name + countryDO?.emoji
        binding.tvCountryCapital.text = "${getString(R.string.capital)} ${countryDO?.capital}"
        binding.tvCountryCurrency.text = "${getString(R.string.currency)} ${countryDO?.currency}"
        binding.tvCountryCode.text = "${getString(R.string.code)} ${countryDO?.code}"
        binding.tvCountryNative.text = "${getString(R.string.natives)} ${countryDO?.native}"
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}