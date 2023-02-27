package com.mostafa.starwarsfans.data.startships

import com.mostafa.starwarsfans.data.startships.paging.StarshipsPagingDS
import com.mostafa.starwarsfans.domain.GetAllStarshipsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StarshipsUseCaseModule {
    @Provides
    fun provideStarshipsUseCase(starshipsPagingDS: StarshipsPagingDS): GetAllStarshipsUseCase {
        return GetAllStarshipsUseCase(starshipsPagingDS)
    }
}