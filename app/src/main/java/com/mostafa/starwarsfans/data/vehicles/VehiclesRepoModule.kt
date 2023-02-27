package com.mostafa.starwarsfans.data.vehicles

import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.vehicles.repo.VehiclesRepo
import com.mostafa.starwarsfans.data.vehicles.repo.VehiclesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object VehiclesRepoModule {

    @Provides
    fun provideRepo(apiService: ApiService): VehiclesRepo {
        return VehiclesRepoImpl(apiService)
    }
}