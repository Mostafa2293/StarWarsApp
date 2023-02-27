package com.mostafa.starwarsfans.data.vehicles

import com.mostafa.starwarsfans.data.vehicles.paging.VehiclesPagingDS
import com.mostafa.starwarsfans.domain.GetAllVehiclesUseCase
import dagger.Provides

object VehiclesUseCaseModule {

    @Provides
    fun provideVehiclesUseCase(vehiclesPagingDS: VehiclesPagingDS): GetAllVehiclesUseCase {
        return GetAllVehiclesUseCase(vehiclesPagingDS)
    }
}