package com.ms.catlife.domain.use_case

import com.ms.catlife.domain.repository.CatRepository

class GetAllCatsUseCase(private val catRepository: CatRepository) {
    suspend operator fun invoke() = catRepository.getAllCats()
}