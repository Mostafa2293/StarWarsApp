package com.mostafa.starwarsfans.data.startships.repo

import com.mostafa.starwarsfans.data.startships.model.StarshipsResponse


interface StarshipsRepo {
    suspend fun getAllStarshipsFromRemote(page:Int,searchKey:String): StarshipsResponse
}