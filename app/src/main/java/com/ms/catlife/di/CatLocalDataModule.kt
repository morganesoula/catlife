package com.ms.catlife.di

import com.ms.catlife.data.db.CatDAO
import com.ms.catlife.data.repository.dataSource.CatLocalDataSource
import com.ms.catlife.data.repository.dataSourceImpl.CatLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CatLocalDataModule {

    @Provides
    fun provideCatLocalDataModule(catDAO: CatDAO): CatLocalDataSource = CatLocalDataSourceImpl(catDAO)
}