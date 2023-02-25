package com.mostafa.starwarsfans.data.species

import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.people.repo.PeopleRepo
import com.mostafa.starwarsfans.data.people.repo.PeopleRepoImpl
import com.mostafa.starwarsfans.data.plantes.repo.PlantesRepo
import com.mostafa.starwarsfans.data.plantes.repo.PlantesRepoImpl
import com.mostafa.starwarsfans.data.species.repo.SpeciesRepo
import com.mostafa.starwarsfans.data.species.repo.SpeciesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SpeciesRepoModule {

    @Provides
    fun provideRepo(apiService: ApiService): SpeciesRepo {
        return SpeciesRepoImpl(apiService)
    }
}