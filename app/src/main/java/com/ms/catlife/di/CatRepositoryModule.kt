package com.ms.catlife.di

import com.ms.catlife.data.repository.CatRepositoryImpl
import com.ms.catlife.data.repository.dataSource.CatLocalDataSource
import com.ms.catlife.domain.repository.CatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object CatRepositoryModule {

    @Provides
    fun providesCatRepository(
        catLocalDataSource: CatLocalDataSource
    ): CatRepository = CatRepositoryImpl(catLocalDataSource)
}