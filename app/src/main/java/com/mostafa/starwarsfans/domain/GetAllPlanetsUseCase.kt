package com.mostafa.starwarsfans.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mostafa.starwarsfans.data.films.model.Films
import com.mostafa.starwarsfans.data.films.paging.FilmsPagingDS
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.people.paging.PeoplePagingDS
import com.mostafa.starwarsfans.data.plantes.model.Planet
import com.mostafa.starwarsfans.data.plantes.paging.PlantesPagingDS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPlanetsUseCase @Inject constructor(private val planetsPagingDS: PlantesPagingDS) {
    operator fun invoke(searchKey:String): Flow<PagingData<Planet>> {
        return Pager(
            config = PagingConfig(
                pageSize =10,
                enablePlaceholders = false
            ), pagingSourceFactory = {planetsPagingDS.createPagingSourceWithKey(searchKey)}
        ).flow
    }
}