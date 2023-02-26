package com.mostafa.starwarsfans.presentation.people

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.people.model.PeopleResponse
import com.mostafa.starwarsfans.domain.GetAllPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getAllPeopleUseCase: GetAllPeopleUseCase
):ViewModel(){

    private val _people:MutableStateFlow<PagingData<People>?> = MutableStateFlow(null)
    val people = _people.asStateFlow()

    fun getPeople(searchKey:String=""){
        viewModelScope.launch {
            try {
                getAllPeopleUseCase.invoke(searchKey).cachedIn(this).collectLatest { pagingData ->
                   _people.value = pagingData.map { model ->
                       People(
                           birth_year = model.birth_year,
                           created = model.created,
                           edited = model.edited,
                           eye_color = model.eye_color,
                           films = model.films,
                           gender = model.gender,
                           hair_color = model.hair_color,
                           height = model.height,
                           homeworld = model.homeworld,
                           mass = model.mass,
                           name = model.name,
                           skin_color = model.skin_color,
                           species = model.species,
                           starships = model.starships,
                           url = model.url,
                           vehicles = model.vehicles
                       )
                   }
               }
            }catch (e: Exception){
                Log.e("PeopleViewModel",e.message.toString())
            }
        }
    }
}