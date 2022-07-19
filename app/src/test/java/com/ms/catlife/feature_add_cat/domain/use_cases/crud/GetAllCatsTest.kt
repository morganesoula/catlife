package com.ms.catlife.feature_add_cat.domain.use_cases.crud

import com.google.common.truth.Truth.assertThat
import com.ms.catlife.feature_add_cat.data.repository.FakeCatRepository
import com.ms.catlife.feature_add_cat.domain.util.CatOrder
import com.ms.catlife.feature_add_cat.domain.util.OrderType
import com.ms.catlife.feature_main.domain.model.Cat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllCatsTest {

    private lateinit var getCatUseCases: GetAllCatsUseCase
    private lateinit var fakeRepository: FakeCatRepository

    @Before
    fun setUp() {
        fakeRepository = FakeCatRepository()
        getCatUseCases = GetAllCatsUseCase(fakeRepository)

        val catsToInsert = mutableListOf<Cat>()
        ('a'..'z').forEachIndexed { index, c ->
            catsToInsert.add(
                Cat(
                    name = c.toString(),
                    gender = true,
                    neutered = true,
                    birthdate = index.toLong(),
                    weight = index.toFloat(),
                    race = c.toString(),
                    coat = c.toString()
                )
            )
        }

        catsToInsert.shuffle()
        runBlocking {
            catsToInsert.forEach { fakeRepository.insertCat(it) }
        }
    }

    @Test
    fun `Order cats by name ascending, correct order`() = runBlocking {
        val cats = getCatUseCases(CatOrder.Name(OrderType.Ascending)).first()

        for(i in 0..cats.size - 2) {
            assertThat(cats[i].name).isLessThan(cats[i+1].name)
        }
    }

    @Test
    fun `Order cats by name descending, correct order`() = runBlocking {
        val cats = getCatUseCases(CatOrder.Name(OrderType.Descending)).first()

        for(i in 0..cats.size - 2) {
            assertThat(cats[i].name).isGreaterThan(cats[i+1].name)
        }
    }

    @Test
    fun `Order cats by age ascending, correct order`() = runBlocking {
        val cats = getCatUseCases(CatOrder.Age(OrderType.Ascending)).first()

        for(i in 0..cats.size - 2) {
            assertThat(cats[i].birthdate).isLessThan(cats[i+1].birthdate)
        }
    }

    @Test
    fun `Order cats by age descending, correct order`() = runBlocking {
        val cats = getCatUseCases(CatOrder.Age(OrderType.Descending)).first()

        for(i in 0..cats.size - 2) {
            assertThat(cats[i].birthdate).isGreaterThan(cats[i+1].birthdate)
        }
    }
}