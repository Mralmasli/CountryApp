package com.ea.countryapp.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ea.countryapp.R
import com.ea.countryapp.databinding.FragmentItemListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CountryListFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val countryListAdapter = CountryListAdapter()
    private val countryViewModel: CountryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryViewModel.getCountryList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.itemList

        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-land)
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        setupRecyclerView(recyclerView, itemDetailFragmentContainer)

        initObserver()
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        itemDetailFragmentContainer: View?
    ) {

        recyclerView.adapter = countryListAdapter

        countryListAdapter.onItemClick = {
            val bundle = Bundle()
            bundle.putString(
                CountryDetailFragment.ARG_ITEM_ID,
                it.code
            )
            if (itemDetailFragmentContainer != null) {
                itemDetailFragmentContainer.findNavController()
                    .navigate(R.id.fragment_item_detail, bundle)
            } else {
                requireView().findNavController().navigate(R.id.show_item_detail, bundle)
            }
        }
    }

    private fun initObserver(){
        lifecycleScope.launchWhenResumed {
            countryViewModel.countryList.collectLatest { state->
                when(state){
                    UiState.Empty -> {
                        binding.progressCircular?.visibility = View.GONE
                    }
                    is UiState.Error -> {
                        binding.progressCircular?.visibility = View.GONE
                        Toast.makeText(requireContext(),state.message,Toast.LENGTH_SHORT).show()
                    }
                    UiState.Loading -> {
                        binding.progressCircular?.visibility = View.VISIBLE
                    }
                    is UiState.Success -> {
                        binding.progressCircular?.visibility = View.GONE
                        countryListAdapter.submitList(state.result)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}