package com.mostafa.starwarsfans.data.plantes

import com.mostafa.starwarsfans.data.people.paging.PeoplePagingDS
import com.mostafa.starwarsfans.data.plantes.paging.PlantesPagingDS
import com.mostafa.starwarsfans.domain.GetAllPeopleUseCase
import com.mostafa.starwarsfans.domain.GetAllPlanetsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PlanetsUseCaseModule {

    @Provides
    fun providePlanetsUseCase(planetsPagingDS: PlantesPagingDS): GetAllPlanetsUseCase {
        return GetAllPlanetsUseCase(planetsPagingDS)
    }
}