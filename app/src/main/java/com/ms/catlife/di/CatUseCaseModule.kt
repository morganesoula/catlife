package com.ms.catlife.di

import com.ms.catlife.domain.repository.CatRepository
import com.ms.catlife.domain.use_case.CatUseCases
import com.ms.catlife.domain.use_case.DeleteCatUseCase
import com.ms.catlife.domain.use_case.GetAllCatsUseCase
import com.ms.catlife.domain.use_case.InsertCatUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CatUseCaseModule {

    @Provides
    fun provideCatUseCases(catRepository: CatRepository) = CatUseCases(
        insertCatUseCase = InsertCatUseCase(catRepository),
        deleteCatUseCase = DeleteCatUseCase(catRepository),
        getAllCatsUseCase = GetAllCatsUseCase(catRepository)
    )
}

