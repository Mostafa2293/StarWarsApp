package com.mostafa.starwarsfans.data.people.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mostafa.starwarsfans.data.BasePagingDataSource
import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.people.repo.PeopleRepo
import javax.inject.Inject

class PeoplePagingDS @Inject constructor(private val repo: PeopleRepo) : BasePagingDataSource<People>() {

    override suspend fun loadData(page: Int, size: Int): List<People> {
       return repo.getAllPeopleFromRemote(page,searchKey).let {
           it.people
       }
    }
}