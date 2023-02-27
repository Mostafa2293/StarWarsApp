package com.mostafa.starwarsfans.data.startships.repo

import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.startships.model.StarshipsResponse

class StarshipsRepoImpl(private val apiService: ApiService): StarshipsRepo {
    override suspend fun getAllStarshipsFromRemote(page: Int, searchKey: String): StarshipsResponse {
        return apiService.getStarships(page,searchKey)
    }
}