package com.mostafa.starwarsfans.presentation.vehicles

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.mostafa.starwarsfans.R
import com.mostafa.starwarsfans.databinding.FragmentSpeciesBinding
import com.mostafa.starwarsfans.databinding.FragmentVehiclesBinding
import com.mostafa.starwarsfans.presentation.species.SpeciesListAdapter
import com.mostafa.starwarsfans.presentation.species.SpeciesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VehiclesFragment : Fragment() {
    private val viewModel: VehiclesViewModel by viewModels()
    lateinit var binding: FragmentVehiclesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_vehicles, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getVehicles()
        val vehicleAdapter = VehiclesListAdapter()
        val recyclerView: RecyclerView = binding.allStarshipsList

        recyclerView.adapter = vehicleAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.vehicles.collectLatest {
                    vehicleAdapter.submitData(it!!)
                }
            }
        }

        binding.searchBarV.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("Search", "onQueryTextSubmit: $query")
                viewModel.getVehicles(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("Search", "onQueryTextChange: $newText")
                viewModel.getVehicles(newText!!)
                return true
            }


        })


    }


}