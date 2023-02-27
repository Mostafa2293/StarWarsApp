package com.mostafa.starwarsfans.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mostafa.starwarsfans.data.vehicles.model.Vehicle
import com.mostafa.starwarsfans.data.vehicles.paging.VehiclesPagingDS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllVehiclesUseCase @Inject constructor(private val vehiclesPagingDS: VehiclesPagingDS) {

    operator fun invoke(searchKey:String): Flow<PagingData<Vehicle>> {
        return Pager(
            config = PagingConfig(
                pageSize =10,
                enablePlaceholders = false
            ), pagingSourceFactory = {vehiclesPagingDS.createPagingSourceWithKey(searchKey)}
        ).flow
    }
}