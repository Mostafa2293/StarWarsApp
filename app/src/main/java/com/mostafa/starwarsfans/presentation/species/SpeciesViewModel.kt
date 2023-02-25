package com.mostafa.starwarsfans.presentation.species

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.plantes.model.Planet
import com.mostafa.starwarsfans.data.species.model.Species
import com.mostafa.starwarsfans.domain.GetAllPlanetsUseCase
import com.mostafa.starwarsfans.domain.GetAllSpeciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpeciesViewModel @Inject constructor(
    private val getAllSpeciesUseCase: GetAllSpeciesUseCase
):ViewModel(){

    private val _species: MutableStateFlow<PagingData<Species>?> = MutableStateFlow(null)
    val species = _species.asStateFlow()

    fun getSpecies(){
        viewModelScope.launch {
            try {
                getAllSpeciesUseCase.invoke().cachedIn(this).collectLatest { pagingData ->
                    _species.value = pagingData.map { model ->
                       Species(
                           average_height = model.average_height,
                           average_lifespan = model.average_lifespan,
                           classification = model.classification,
                           created = model.created,
                           designation = model.designation,
                           edited = model.edited,
                           eye_colors = model.eye_colors,
                           films = model.films,
                           hair_colors = model.hair_colors,
                           language = model.language,
                           name = model.name,
                           people = model.people,
                           skin_colors = model.skin_colors,
                           url = model.url
                       )
                    }
                }
            }catch (e: Exception){
                Log.e("PeopleViewModel",e.message.toString())
            }
        }
    }

}