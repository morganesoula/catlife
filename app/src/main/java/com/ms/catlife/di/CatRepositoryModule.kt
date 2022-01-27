package com.ms.catlife.di

import com.ms.catlife.main.data.repository.CatRepository
import com.ms.catlife.main.data.repository.ICatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class CatRepositoryModule {

    @Binds
    abstract fun providesCatRepository(impl: CatRepository): ICatRepository
}