package com.mostafa.starwarsfans.presentation.films

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
import com.mostafa.starwarsfans.databinding.FragmentFilmsBinding
import com.mostafa.starwarsfans.presentation.people.PeopleListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class FilmsFragment : Fragment() {

    private val viewModel: FilmsViewModel by viewModels()
    lateinit var binding:FragmentFilmsBinding
    private val requestCodeSpeech = 102


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_films, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFilms()
        val filmsAdapter = FilmsListAdapter()
        val recyclerView: RecyclerView = binding.allFilmsList

        recyclerView.adapter = filmsAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.films.collectLatest {
                    filmsAdapter.submitData(it!!)
                }
            }
        }

        binding.searchBarF.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("Search", "onQueryTextSubmit: $query")
                viewModel.getFilms(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("Search", "onQueryTextChange: $newText")
                viewModel.getFilms(newText!!)
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
            binding.searchBarF.setQuery(result!![0].toString(),true)
            binding.searchBarF.isIconified = false
        }
    }

}