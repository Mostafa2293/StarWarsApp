package com.mostafa.starwarsfans.data.species.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mostafa.starwarsfans.data.BasePagingDataSource
import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.people.repo.PeopleRepo
import com.mostafa.starwarsfans.data.plantes.model.Planet
import com.mostafa.starwarsfans.data.plantes.repo.PlantesRepo
import com.mostafa.starwarsfans.data.species.model.Species
import com.mostafa.starwarsfans.data.species.repo.SpeciesRepo
import javax.inject.Inject

class SpeciesPagingDS @Inject constructor(private val repo: SpeciesRepo) : BasePagingDataSource<Species>() {

    override suspend fun loadData(page: Int, size: Int): List<Species> {
       return repo.getAllSpeciesFromRemote(page,searchKey).let {
           it.species
       }
    }
}