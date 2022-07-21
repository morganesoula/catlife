package com.ms.catlife.feature_add_cat.di

import android.app.Application
import androidx.room.Room
import com.ms.catlife.core.data.data_source.CatDatabase
import com.ms.catlife.core.domain.use_cases.crud.CatUseCases
import com.ms.catlife.core.domain.use_cases.crud.DeleteCatUseCase
import com.ms.catlife.core.domain.use_cases.crud.GetAllCatsUseCase
import com.ms.catlife.core.domain.use_cases.crud.GetCatUseCase
import com.ms.catlife.core.domain.use_cases.crud.InsertCatUseCase
import com.ms.catlife.feature_add_cat.data.repository.CatRepositoryImpl
import com.ms.catlife.feature_add_cat.domain.repository.CatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestCatLifeModule {

    @Provides
    @Singleton
    fun provideCatLifeDatabase(app: Application): CatDatabase =
        Room.inMemoryDatabaseBuilder(
            app,
            CatDatabase::class.java
        ).build()

    @Provides
    @Singleton
    fun provideCatRepository(catDatabase: CatDatabase): CatRepository {
        return CatRepositoryImpl(catDatabase.catDAO)
    }

    @Provides
    @Singleton
    fun provideCatCRUDUseCases(repository: CatRepository): CatUseCases {
        return CatUseCases(
            InsertCatUseCase(repository),
            DeleteCatUseCase(repository),
            GetAllCatsUseCase(repository),
            GetCatUseCase(repository)
        )
    }
}