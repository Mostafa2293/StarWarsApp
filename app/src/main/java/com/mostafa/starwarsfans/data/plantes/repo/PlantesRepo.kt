package com.mostafa.starwarsfans.data.plantes.repo

import com.mostafa.starwarsfans.data.people.model.PeopleResponse
import com.mostafa.starwarsfans.data.plantes.model.PlanetsResponse

interface PlantesRepo {
    suspend fun getAllPlantesFromRemote(page:Int,searchKey:String):PlanetsResponse
}