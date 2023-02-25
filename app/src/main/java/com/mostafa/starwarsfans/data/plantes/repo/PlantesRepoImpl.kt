package com.mostafa.starwarsfans.data.plantes.repo

import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.people.model.PeopleResponse
import com.mostafa.starwarsfans.data.people.repo.PeopleRepo
import com.mostafa.starwarsfans.data.plantes.model.PlanetsResponse

class PlantesRepoImpl (private val apiService: ApiService): PlantesRepo {
    override suspend fun getAllPlantesFromRemote(page: Int): PlanetsResponse {
        return apiService.getPlanets(page)
    }

}