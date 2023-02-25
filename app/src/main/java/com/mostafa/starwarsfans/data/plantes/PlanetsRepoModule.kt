package com.mostafa.starwarsfans.data.plantes

import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.people.repo.PeopleRepo
import com.mostafa.starwarsfans.data.people.repo.PeopleRepoImpl
import com.mostafa.starwarsfans.data.plantes.repo.PlantesRepo
import com.mostafa.starwarsfans.data.plantes.repo.PlantesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PlanetsRepoModule {

    @Provides
    fun provideRepo(apiService: ApiService): PlantesRepo {
        return PlantesRepoImpl(apiService)
    }
}