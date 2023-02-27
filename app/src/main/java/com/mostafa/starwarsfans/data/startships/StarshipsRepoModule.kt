package com.mostafa.starwarsfans.data.startships

import com.mostafa.starwarsfans.data.network.ApiService
import com.mostafa.starwarsfans.data.startships.repo.StarshipsRepo
import com.mostafa.starwarsfans.data.startships.repo.StarshipsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StarshipsRepoModule {

    @Provides
    fun provideRepo(apiService: ApiService): StarshipsRepo {
        return StarshipsRepoImpl(apiService)
    }
}

