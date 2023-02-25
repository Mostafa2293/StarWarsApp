package com.mostafa.starwarsfans.presentation.films

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mostafa.starwarsfans.data.films.model.Films
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.domain.GetAllFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val getAllFilmsUseCase: GetAllFilmsUseCase
) :ViewModel(){

    private val _films: MutableStateFlow<PagingData<Films>?> = MutableStateFlow(null)
    val films = _films.asStateFlow()

    fun getFilms(){
        viewModelScope.launch {
            try {

                getAllFilmsUseCase.invoke().cachedIn(this).collectLatest { pagingData ->
                    _films.value = pagingData.map { model ->
                        Films(
                            characters = model.characters,
                            created = model.created,
                            edited = model.edited,
                            director = model.director,
                            episode_id = model.episode_id,
                            opening_crawl = model.opening_crawl,
                            planets = model.planets,
                            producer = model.producer,
                            release_date = model.release_date,
                            species = model.species,
                            starships = model.starships,
                            title = model.title,
                            url = model.url,
                            vehicles = model.vehicles
                        )
                    }
                }
            }catch(e: Exception){
                Log.e("PeopleViewModel",e.message.toString())
            }
        }
    }

}