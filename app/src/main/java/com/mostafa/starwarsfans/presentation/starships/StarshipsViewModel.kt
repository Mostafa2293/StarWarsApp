package com.mostafa.starwarsfans.presentation.starships

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.plantes.model.Planet
import com.mostafa.starwarsfans.data.species.model.Species
import com.mostafa.starwarsfans.data.startships.model.Starship
import com.mostafa.starwarsfans.domain.GetAllPlanetsUseCase
import com.mostafa.starwarsfans.domain.GetAllSpeciesUseCase
import com.mostafa.starwarsfans.domain.GetAllStarshipsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarshipsViewModel @Inject constructor(
    private val getAllStarshipsUseCase: GetAllStarshipsUseCase
):ViewModel(){

    private val _starships: MutableStateFlow<PagingData<Starship>?> = MutableStateFlow(null)
    val starships = _starships.asStateFlow()

    fun getStarships(searchKey:String=""){
        viewModelScope.launch {
            try {
                getAllStarshipsUseCase.invoke(searchKey).cachedIn(this).collectLatest { pagingData ->
                    _starships.value = pagingData.map { model ->
                        Starship(
                            name = model.name,
                            MGLT = model.MGLT,
                            cargo_capacity = model.cargo_capacity,
                            consumables = model.consumables,
                            cost_in_credits = model.cost_in_credits,
                            crew = model.crew,
                            hyperdrive_rating = model.hyperdrive_rating,
                            length = model.length,
                            manufacturer = model.manufacturer,
                            max_atmosphering_speed = model.max_atmosphering_speed,
                            model = model.model,
                            passengers = model.passengers,
                            starship_class = model.starship_class
                        )
                    }
                }
            }catch (e: Exception){
                Log.e("StarshipsViewModel",e.message.toString())
            }
        }
    }

}