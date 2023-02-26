package com.mostafa.starwarsfans.data.plantes.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mostafa.starwarsfans.data.BasePagingDataSource
import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.people.repo.PeopleRepo
import com.mostafa.starwarsfans.data.plantes.model.Planet
import com.mostafa.starwarsfans.data.plantes.repo.PlantesRepo
import javax.inject.Inject

class PlantesPagingDS @Inject constructor(private val repo: PlantesRepo) : BasePagingDataSource<Planet>() {

    override suspend fun loadData(page: Int, size: Int): List<Planet> {
       return repo.getAllPlantesFromRemote(page,searchKey).let {
           it.planets
       }
    }
}