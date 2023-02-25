package com.mostafa.starwarsfans.data.people

import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.people.repo.PeopleRepo
import com.mostafa.starwarsfans.data.people.repo.PeopleRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PeopleRepoModule {

    @Provides
    fun provideRepo(apiService: ApiService):PeopleRepo{
        return PeopleRepoImpl(apiService)
    }
}