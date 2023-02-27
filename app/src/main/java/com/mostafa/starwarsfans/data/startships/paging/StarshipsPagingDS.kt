package com.mostafa.starwarsfans.data.startships.paging

import com.mostafa.starwarsfans.data.BasePagingDataSource
import com.mostafa.starwarsfans.data.startships.model.Starship
import com.mostafa.starwarsfans.data.startships.repo.StarshipsRepo
import javax.inject.Inject

class StarshipsPagingDS  @Inject constructor(private val repo: StarshipsRepo) : BasePagingDataSource<Starship>() {

    override suspend fun loadData(page: Int, size: Int): List<Starship> {
        return repo.getAllStarshipsFromRemote(page,searchKey).let {
            it.starships
        }
    }
}