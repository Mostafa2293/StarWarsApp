package com.mostafa.starwarsfans.data.people.repo

import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.people.model.PeopleResponse

class PeopleRepoImpl(private val apiService: ApiService):PeopleRepo {
    override suspend fun getAllPeopleFromRemote(page: Int): PeopleResponse {
        return apiService.getPeople(page)
    }
}