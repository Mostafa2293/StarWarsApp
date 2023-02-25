package com.mostafa.starwarsfans.data.species

import com.mostafa.starwarsfans.data.people.paging.PeoplePagingDS
import com.mostafa.starwarsfans.data.plantes.paging.PlantesPagingDS
import com.mostafa.starwarsfans.data.species.paging.SpeciesPagingDS
import com.mostafa.starwarsfans.domain.GetAllPeopleUseCase
import com.mostafa.starwarsfans.domain.GetAllPlanetsUseCase
import com.mostafa.starwarsfans.domain.GetAllSpeciesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SpeciesUseCaseModule {

    @Provides
    fun provideSpeciesUseCase(speciesPagingDS: SpeciesPagingDS): GetAllSpeciesUseCase {
        return GetAllSpeciesUseCase(speciesPagingDS)
    }
}