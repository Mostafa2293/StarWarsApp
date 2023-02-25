package com.mostafa.starwarsfans.presentation.planets

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
import com.mostafa.starwarsfans.presentation.people.PeopleListAdapter
import com.mostafa.starwarsfans.presentation.people.PeopleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlanetsFragment : Fragment() {

    private val viewModel: PlanetsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPlanets()
        val planetsAdapter = PlanetsListAdapter()
        val recyclerView: RecyclerView = view.findViewById(R.id.allPlanetsList)

        recyclerView.adapter = planetsAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.planets.collectLatest {
                    planetsAdapter.submitData(it!!)
                }
            }
        }

    }
}