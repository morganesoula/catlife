package com.ms.catlife.domain.useCase

import com.ms.catlife.domain.repository.CatRepository

class DeleteCatUseCase(private val catRepository: CatRepository) {
    suspend operator fun invoke(name: String) = catRepository.deleteCat(name)
}