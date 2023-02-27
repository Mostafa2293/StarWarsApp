package com.mostafa.starwarsfans.data.vehicles.repo

import com.mostafa.starwarsfans.data.vehicles.model.VehiclesResponse

interface VehiclesRepo {
    suspend fun getAllVehiclesFromRemote(page:Int,searchKey:String): VehiclesResponse

}