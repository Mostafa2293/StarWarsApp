package com.mostafa.starwarsfans.data.films.repo

import com.mostafa.starwarsfans.data.films.model.FilmsResponse

interface FilmsRepo {
    suspend fun getAllFilmsFromRemote(page:Int,searchKey:String):FilmsResponse
}