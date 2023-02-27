package com.mostafa.starwarsfans.data.vehicles.paging

import com.mostafa.starwarsfans.data.BasePagingDataSource
import com.mostafa.starwarsfans.data.startships.model.Starship
import com.mostafa.starwarsfans.data.startships.repo.StarshipsRepo
import com.mostafa.starwarsfans.data.vehicles.model.Vehicle
import com.mostafa.starwarsfans.data.vehicles.repo.VehiclesRepo
import javax.inject.Inject

class VehiclesPagingDS  @Inject constructor(private val repo: VehiclesRepo) : BasePagingDataSource<Vehicle>() {

    override suspend fun loadData(page: Int, size: Int): List<Vehicle> {
        return repo.getAllVehiclesFromRemote(page,searchKey).let {
            it.vehicles
        }
    }
}