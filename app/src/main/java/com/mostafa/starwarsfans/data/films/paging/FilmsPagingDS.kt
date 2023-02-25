package com.mostafa.starwarsfans.data.films.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mostafa.starwarsfans.data.BasePagingDataSource
import com.mostafa.starwarsfans.data.films.model.Films
import com.mostafa.starwarsfans.data.films.repo.FilmsRepo
import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.people.repo.PeopleRepo
import javax.inject.Inject

class FilmsPagingDS @Inject constructor(private val repo: FilmsRepo) : BasePagingDataSource<Films>() {

    override suspend fun loadData(page: Int, size: Int): List<Films> {
       return repo.getAllFilmsFromRemote(page).let {
           it.films
       }
    }
}