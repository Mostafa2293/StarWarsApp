package com.mostafa.starwarsfans.data.vehicles

import com.mostafa.starwarsfans.data.vehicles.paging.VehiclesPagingDS
import com.mostafa.starwarsfans.domain.GetAllVehiclesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object VehiclesUseCaseModule {

    @Provides
    fun provideVehiclesUseCase(vehiclesPagingDS: VehiclesPagingDS): GetAllVehiclesUseCase {
        return GetAllVehiclesUseCase(vehiclesPagingDS)
    }
}