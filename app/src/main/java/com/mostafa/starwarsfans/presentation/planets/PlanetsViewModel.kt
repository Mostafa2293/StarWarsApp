package com.mostafa.starwarsfans.presentation.planets

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.plantes.model.Planet
import com.mostafa.starwarsfans.domain.GetAllPlanetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val getAllPlanetsUseCase: GetAllPlanetsUseCase
):ViewModel(){

    private val _planets: MutableStateFlow<PagingData<Planet>?> = MutableStateFlow(null)
    val planets = _planets.asStateFlow()

    fun getPlanets(){
        viewModelScope.launch {
            try {
                getAllPlanetsUseCase.invoke().cachedIn(this).collectLatest { pagingData ->
                    _planets.value = pagingData.map { model ->
                        Planet(
                            climate = model.climate,
                            created = model.created,
                            diameter = model.diameter,
                            edited = model.edited,
                            films = model.films,
                            gravity = model.gravity,
                            name = model.name,
                            orbital_period = model.orbital_period,
                            population = model.population,
                            residents = model.residents,
                            rotation_period = model.rotation_period,
                            surface_water = model.surface_water,
                            terrain = model.terrain,
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