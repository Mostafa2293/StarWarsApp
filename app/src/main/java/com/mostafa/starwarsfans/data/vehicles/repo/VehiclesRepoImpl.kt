package com.mostafa.starwarsfans.data.vehicles.repo

import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.startships.repo.StarshipsRepo
import com.mostafa.starwarsfans.data.vehicles.model.VehiclesResponse

class VehiclesRepoImpl(private val apiService: ApiService): VehiclesRepo  {
    override suspend fun getAllVehiclesFromRemote(page: Int, searchKey: String): VehiclesResponse {
        return apiService.getVehicles(page,searchKey)
    }
}