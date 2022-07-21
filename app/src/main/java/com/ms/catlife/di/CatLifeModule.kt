package com.ms.catlife.di

import android.app.Application
import androidx.room.Room
import com.ms.catlife.core.data.data_source.CatDatabase
import com.ms.catlife.feature_add_cat.data.repository.CatRepositoryImpl
import com.ms.catlife.feature_add_cat.domain.repository.CatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CatLifeModule {

    @Provides
    @Singleton
    fun provideCatLifeDatabase(app: Application): CatDatabase =
        Room.databaseBuilder(
            app,
            CatDatabase::class.java,
            CatDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideCatRepository(catDatabase: CatDatabase): CatRepository {
        return CatRepositoryImpl(catDatabase.catDAO)
    }

}