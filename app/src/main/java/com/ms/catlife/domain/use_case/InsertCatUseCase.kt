package com.ms.catlife.domain.use_case

import com.ms.catlife.domain.model.Cat
import com.ms.catlife.domain.repository.CatRepository

class InsertCatUseCase(private val catRepository: CatRepository) {
    suspend operator fun invoke(cat: Cat) = catRepository.insertCat(cat)
}