package com.mostafa.starwarsfans.data.species.repo

import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.people.model.PeopleResponse
import com.mostafa.starwarsfans.data.people.repo.PeopleRepo
import com.mostafa.starwarsfans.data.plantes.model.PlanetsResponse
import com.mostafa.starwarsfans.data.species.model.SpeciesResponse

class SpeciesRepoImpl (private val apiService: ApiService): SpeciesRepo {
    override suspend fun getAllSpeciesFromRemote(page: Int,searchKey:String): SpeciesResponse {
        return apiService.getSpecies(page,searchKey)
    }


}