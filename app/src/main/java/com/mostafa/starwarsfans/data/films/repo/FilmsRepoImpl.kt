package com.mostafa.starwarsfans.data.films.repo

import com.mostafa.starwarsfans.data.films.model.FilmsResponse
import com.mostafa.starwarsfans.data.network.ApiService

class FilmsRepoImpl(private val apiService: ApiService):FilmsRepo {
    override suspend fun getAllFilmsFromRemote(page: Int): FilmsResponse {
        return apiService.getFilms(page)
    }

}