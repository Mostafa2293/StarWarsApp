package com.mostafa.starwarsfans.presentation.species

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.mostafa.starwarsfans.R
import com.mostafa.starwarsfans.presentation.planets.PlanetsListAdapter
import com.mostafa.starwarsfans.presentation.planets.PlanetsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SpeciesFragment : Fragment() {

    private val viewModel: SpeciesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_species, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSpecies()
        val speciesAdapter = SpeciesListAdapter()
        val recyclerView: RecyclerView = view.findViewById(R.id.allSpeciesList)

        recyclerView.adapter = speciesAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.species.collectLatest {
                    speciesAdapter.submitData(it!!)
                }
            }
        }

    }

}