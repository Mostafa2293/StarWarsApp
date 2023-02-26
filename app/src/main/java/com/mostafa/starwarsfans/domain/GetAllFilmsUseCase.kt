package com.mostafa.starwarsfans.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mostafa.starwarsfans.data.films.model.Films
import com.mostafa.starwarsfans.data.films.paging.FilmsPagingDS
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.people.paging.PeoplePagingDS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFilmsUseCase @Inject constructor(private val filmsPagingDS: FilmsPagingDS) {

    operator fun invoke(searchKey:String): Flow<PagingData<Films>> {
        return Pager(
            config = PagingConfig(
                pageSize =10,
                enablePlaceholders = false
            ), pagingSourceFactory = {filmsPagingDS.createPagingSourceWithKey(searchKey)}
        ).flow
    }
}