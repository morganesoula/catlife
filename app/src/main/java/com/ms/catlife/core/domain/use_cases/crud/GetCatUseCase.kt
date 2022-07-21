package com.ms.catlife.core.domain.use_cases.crud

import com.ms.catlife.core.data.entities.Cat
import com.ms.catlife.feature_add_cat.domain.repository.CatRepository

class GetCatUseCase(private val catRepository: CatRepository) {
    suspend operator fun invoke(id: Int): Cat? = catRepository.getCatById(id)
}