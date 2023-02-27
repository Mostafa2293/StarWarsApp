package com.mostafa.starwarsfans.presentation.vehicles

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.plantes.model.Planet
import com.mostafa.starwarsfans.data.species.model.Species
import com.mostafa.starwarsfans.data.vehicles.model.Vehicle
import com.mostafa.starwarsfans.domain.GetAllPlanetsUseCase
import com.mostafa.starwarsfans.domain.GetAllSpeciesUseCase
import com.mostafa.starwarsfans.domain.GetAllVehiclesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehiclesViewModel @Inject constructor(
    private val getAllVehiclesUseCase: GetAllVehiclesUseCase
):ViewModel(){

    private val _vehicles: MutableStateFlow<PagingData<Vehicle>?> = MutableStateFlow(null)
    val vehicles = _vehicles.asStateFlow()

    fun getVehicles(searchKey:String=""){
        viewModelScope.launch {
            try {
                getAllVehiclesUseCase.invoke(searchKey).cachedIn(this).collectLatest { pagingData ->
                    _vehicles.value = pagingData.map { model ->
                       Vehicle(
                          cargo_capacity = model.cargo_capacity,
                          consumables = model.consumables,
                          cost_in_credits = model.cost_in_credits,
                          crew = model.crew,
                          length = model.length,
                          manufacturer = model.manufacturer,
                          model = model.model,
                          max_atmosphering_speed = model.max_atmosphering_speed,
                          name = model.name,
                          passengers = model.passengers,
                          vehicle_class = model.vehicle_class
                       )
                    }
                }
            }catch (e: Exception){
                Log.e("VehiclesViewModel",e.message.toString())
            }
        }
    }

}