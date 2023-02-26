package com.mostafa.starwarsfans.presentation.species

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
import com.mostafa.starwarsfans.presentation.planets.PlanetsListAdapter
import com.mostafa.starwarsfans.presentation.planets.PlanetsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SpeciesFragment : Fragment() {

    private val viewModel: SpeciesViewModel by viewModels()
    lateinit var binding:FragmentSpeciesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_species, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSpecies()
        val speciesAdapter = SpeciesListAdapter()
        val recyclerView: RecyclerView = binding.allSpeciesList

        recyclerView.adapter = speciesAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.species.collectLatest {
                    speciesAdapter.submitData(it!!)
                }
            }
        }

        binding.searchBarS.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("Search", "onQueryTextSubmit: $query")
                viewModel.getSpecies(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("Search", "onQueryTextChange: $newText")
                viewModel.getSpecies(newText!!)
                return true
            }


        })


    }

}