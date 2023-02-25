package com.mostafa.starwarsfans.data.films

import com.mostafa.starwarsfans.data.films.repo.FilmsRepo
import com.mostafa.starwarsfans.data.films.repo.FilmsRepoImpl
import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.people.repo.PeopleRepo
import com.mostafa.starwarsfans.data.people.repo.PeopleRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FilmsRepoModule {

    @Provides
    fun provideRepo(apiService: ApiService): FilmsRepo {
        return FilmsRepoImpl(apiService)
    }
}