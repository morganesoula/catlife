package com.ms.catlife.feature_add_cat.domain.use_cases.crud

import com.google.common.truth.Truth.assertThat
import com.ms.catlife.feature_add_cat.data.repository.FakeCatRepository
import com.ms.catlife.feature_main.domain.model.Cat
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
            gender = "Male",
            birthdate = 1529602002000,
            weight = 3.4f,
            race = "Européen",
            coat = "Noir et blanc"
        )

        catsToInsertTwo = Cat(
            id = 2,
            name = "Douffy",
            gender = "Femelle",
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

        assertThat(catTwo?.gender).isEqualTo("Femelle")
    }
}