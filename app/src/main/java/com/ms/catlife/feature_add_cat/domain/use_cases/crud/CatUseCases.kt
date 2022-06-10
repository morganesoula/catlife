package com.ms.catlife.feature_add_cat.domain.use_cases.crud

data class CatUseCases(
    val insertCatUseCase: InsertCatUseCase,
    val deleteCatUseCase: DeleteCatUseCase,
    val getAllCatsUseCase: GetAllCatsUseCase,
    val getCatUseCase: GetCatUseCase
)
