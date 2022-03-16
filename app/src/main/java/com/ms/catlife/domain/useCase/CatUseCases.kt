package com.ms.catlife.domain.useCase

data class CatUseCases(
    val insertCatUseCase: InsertCatUseCase,
    val deleteCatUseCase: DeleteCatUseCase,
    val getAllCatsUseCase: GetAllCatsUseCase
)
