package com.mostafa.starwarsfans.presentation.starships

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
import com.mostafa.starwarsfans.databinding.FragmentSpeciesBinding
import com.mostafa.starwarsfans.databinding.FragmentStarShipsBinding
import com.mostafa.starwarsfans.presentation.species.SpeciesListAdapter
import com.mostafa.starwarsfans.presentation.species.SpeciesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class StarShipsFragment : Fragment() {
    private val viewModel: StarshipsViewModel by viewModels()
    lateinit var binding:FragmentStarShipsBinding
    private val requestCodeSpeech = 102


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_star_ships, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStarships()
        val starshipAdapter = StarshipsListAdapter()
        val recyclerView: RecyclerView = binding.allStarshipsList

        recyclerView.adapter = starshipAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.starships.collectLatest {
                    starshipAdapter.submitData(it!!)
                }
            }
        }

        binding.searchBarSS.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("Search", "onQueryTextSubmit: $query")
                viewModel.getStarships(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("Search", "onQueryTextChange: $newText")
                viewModel.getStarships(newText!!)
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
            binding.searchBarSS.setQuery(result!![0].toString(),true)
            binding.searchBarSS.isIconified = false
        }
    }

}