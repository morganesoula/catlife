package com.ms.catlife.feature_add_cat.domain.use_cases.crud

import com.ms.catlife.feature_add_cat.domain.repository.CatRepository
import com.ms.catlife.feature_main.domain.model.Cat

class GetCatUseCase(private val catRepository: CatRepository) {

    suspend operator fun invoke(id: Int): Cat? = catRepository.getCatById(id)
}