package com.mostafa.starwarsfans.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mostafa.starwarsfans.data.films.model.Films
import com.mostafa.starwarsfans.data.films.paging.FilmsPagingDS
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.people.paging.PeoplePagingDS
import com.mostafa.starwarsfans.data.species.model.Species
import com.mostafa.starwarsfans.data.species.paging.SpeciesPagingDS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSpeciesUseCase @Inject constructor(private val speciesPagingDS: SpeciesPagingDS) {

    operator fun invoke(searchKey:String): Flow<PagingData<Species>> {
        return Pager(
            config = PagingConfig(
                pageSize =10,
                enablePlaceholders = false
            ), pagingSourceFactory = {speciesPagingDS.createPagingSourceWithKey(searchKey)}
        ).flow
    }
}