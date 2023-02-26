package com.mostafa.starwarsfans.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.people.paging.PeoplePagingDS
import com.mostafa.starwarsfans.data.people.repo.PeopleRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPeopleUseCase @Inject constructor(private val peoplePagingDS:PeoplePagingDS) {

    operator fun invoke(searchKey:String): Flow<PagingData<People>> {
        return Pager(
            config = PagingConfig(
                pageSize =10,
                enablePlaceholders = false
            ), pagingSourceFactory = {peoplePagingDS.createPagingSourceWithKey(searchKey)}
        ).flow
    }
}