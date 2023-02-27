package com.mostafa.starwarsfans.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mostafa.starwarsfans.data.startships.model.Starship
import com.mostafa.starwarsfans.data.startships.paging.StarshipsPagingDS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllStarshipsUseCase  @Inject constructor(private val starshipsPagingDS: StarshipsPagingDS) {

    operator fun invoke(searchKey:String): Flow<PagingData<Starship>> {
        return Pager(
            config = PagingConfig(
                pageSize =10,
                enablePlaceholders = false
            ), pagingSourceFactory = {starshipsPagingDS.createPagingSourceWithKey(searchKey)}
        ).flow
    }
}