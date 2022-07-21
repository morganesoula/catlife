package com.ms.catlife.feature_add_cat.domain.use_cases.crud

import com.google.common.truth.Truth.assertThat
import com.ms.catlife.core.data.entities.Cat
import com.ms.catlife.core.domain.use_cases.crud.GetCatUseCase
import com.ms.catlife.feature_add_cat.data.repository.FakeCatRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCatByIdTest {

    private lateinit var getCatUseCases: GetCatUseCase
    private lateinit var fakeCatRepository: FakeCatRepository
    private lateinit var catsToInsertOne: Cat
    private lateinit var catsToInsertTwo: Cat

    @Before
    fun setUp() {
        fakeCatRepository = FakeCatRepository()
        getCatUseCases = GetCatUseCase(fakeCatRepository)

        catsToInsertOne = Cat(
            id = 1,
            name = "Oreo",
            gender = true,
            neutered = true,
            birthdate = 1529602002000,
            weight = 3.4f,
            race = "Européen",
            coat = "Noir et blanc"
        )

        catsToInsertTwo = Cat(
            id = 2,
            name = "Douffy",
            gender = false,
            neutered = true,
            birthdate = 1403371602000,
            weight = 2.8f,
            race = "Siamoise",
            coat = "Seal point"
        )

        runBlocking {
            fakeCatRepository.insertCat(catsToInsertOne)
            fakeCatRepository.insertCat(catsToInsertTwo)
        }
    }

    @Test
    fun `Fetch cats by ID, known cats`() = runBlocking {
        val cat = getCatUseCases.invoke(1)

        assertThat(cat).isNotNull()
        assertThat(cat?.name).isEqualTo("Oreo")

        val catTwo = getCatUseCases.invoke(2)

        assertThat(catTwo?.gender).isFalse()
    }
}