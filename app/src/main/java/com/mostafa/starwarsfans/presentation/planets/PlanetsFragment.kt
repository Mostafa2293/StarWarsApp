package com.mostafa.starwarsfans.presentation.planets

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.mostafa.starwarsfans.R
import com.mostafa.starwarsfans.databinding.FragmentPlanetsBinding
import com.mostafa.starwarsfans.presentation.people.PeopleListAdapter
import com.mostafa.starwarsfans.presentation.people.PeopleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class PlanetsFragment : Fragment() {

    private val viewModel: PlanetsViewModel by viewModels()
    lateinit var binding:FragmentPlanetsBinding
    private val requestCodeSpeech = 102


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planets, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPlanets()
        val planetsAdapter = PlanetsListAdapter()
        val recyclerView: RecyclerView = binding.allPlanetsList

        recyclerView.adapter = planetsAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.planets.collectLatest {
                    planetsAdapter.submitData(it!!)
                }
            }
        }


        binding.searchBarP.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("Search", "onQueryTextSubmit: $query")
                viewModel.getPlanets(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("Search", "onQueryTextChange: $newText")
                viewModel.getPlanets(newText!!)
                return true
            }


        })

        binding.searchButton.setOnClickListener {
            Log.d("button", "onSearchBtnClicked: Clicked!!")
            requestSpeechInput()
        }
    }

    private fun requestSpeechInput() {
        if(!SpeechRecognizer.isRecognitionAvailable(requireActivity())){
            Toast.makeText(requireActivity(),"speech recognition is not available", Toast.LENGTH_SHORT).show()
        }else{
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something !")
            startActivityForResult(intent,requestCodeSpeech)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==requestCodeSpeech && resultCode == Activity.RESULT_OK){
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            binding.searchBarP.setQuery(result!![0].toString(),true)
            binding.searchBarP.isIconified = false
        }
    }
}