package com.mostafa.starwarsfans.data.species.repo

import com.mostafa.starwarsfans.data.people.model.PeopleResponse
import com.mostafa.starwarsfans.data.plantes.model.PlanetsResponse
import com.mostafa.starwarsfans.data.species.model.SpeciesResponse

interface SpeciesRepo {
    suspend fun getAllSpeciesFromRemote(page:Int,searchKey:String):SpeciesResponse
}