package com.mostafa.starwarsfans.data.people

import com.mostafa.starwarsfans.data.people.paging.PeoplePagingDS
import com.mostafa.starwarsfans.domain.GetAllPeopleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PeopleUseCaseModule {

    @Provides
    fun providePeopleUseCase(peoplePagingDS: PeoplePagingDS):GetAllPeopleUseCase{
        return GetAllPeopleUseCase(peoplePagingDS)
    }
}