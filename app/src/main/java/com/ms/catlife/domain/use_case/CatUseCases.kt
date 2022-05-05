package com.ms.catlife.domain.use_case

data class CatUseCases(
    val insertCatUseCase: InsertCatUseCase,
    val deleteCatUseCase: DeleteCatUseCase,
    val getAllCatsUseCase: GetAllCatsUseCase
)
