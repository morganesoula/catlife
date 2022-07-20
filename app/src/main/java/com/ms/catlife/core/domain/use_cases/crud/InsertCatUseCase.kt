package com.ms.catlife.core.domain.use_cases.crud

import com.ms.catlife.feature_add_cat.domain.repository.CatRepository
import com.ms.catlife.core.data.entities.Cat

class InsertCatUseCase(private val catRepository: CatRepository) {
    suspend operator fun invoke(cat: Cat) = catRepository.insertCat(cat)
}