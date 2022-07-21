package com.ms.catlife.core.domain.use_cases.crud

import com.ms.catlife.core.data.entities.Cat
import com.ms.catlife.feature_add_cat.domain.repository.CatRepository
import com.ms.catlife.feature_add_cat.domain.util.CatOrder
import com.ms.catlife.feature_add_cat.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllCatsUseCase(private val catRepository: CatRepository) {

    operator fun invoke(catOrder: CatOrder = CatOrder.Name(OrderType.Ascending)): Flow<List<Cat>> {
        return catRepository.getCats().map { cats ->
            when (catOrder.orderType) {
                is OrderType.Ascending -> {
                    when (catOrder) {
                        is CatOrder.Name -> cats.sortedBy { it.name.lowercase() }
                        is CatOrder.Age -> cats.sortedBy { it.birthdate }
                    }
                }
                is OrderType.Descending -> {
                    when (catOrder) {
                        is CatOrder.Name -> cats.sortedByDescending { it.name.lowercase() }
                        is CatOrder.Age -> cats.sortedByDescending { it.birthdate }
                    }
                }
            }
        }
    }
}