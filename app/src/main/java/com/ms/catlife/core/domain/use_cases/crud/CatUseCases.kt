package com.ms.catlife.core.domain.use_cases.crud

data class CatUseCases(
    val insertCatUseCase: InsertCatUseCase,
    val deleteCatUseCase: DeleteCatUseCase,
    val getAllCatsUseCase: GetAllCatsUseCase,
    val getCatUseCase: GetCatUseCase
)
