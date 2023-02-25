package com.mostafa.starwarsfans.data.films


import com.mostafa.starwarsfans.data.films.paging.FilmsPagingDS
import com.mostafa.starwarsfans.data.people.paging.PeoplePagingDS
import com.mostafa.starwarsfans.domain.GetAllFilmsUseCase
import com.mostafa.starwarsfans.domain.GetAllPeopleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FilmsUseCaseModule {

    @Provides
    fun provideFilmsUseCase(filmsPagingDS: FilmsPagingDS): GetAllFilmsUseCase {
        return GetAllFilmsUseCase(filmsPagingDS)
    }
}