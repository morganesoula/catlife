package com.ms.catlife.core.domain.use_cases.crud

import com.ms.catlife.feature_add_cat.domain.repository.CatRepository

class DeleteCatUseCase(private val catRepository: CatRepository) {
    suspend operator fun invoke(catId: Int) {
        catRepository.deleteCat(catId)
    }
}