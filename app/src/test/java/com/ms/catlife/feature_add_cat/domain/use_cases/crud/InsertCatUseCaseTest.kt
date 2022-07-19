package com.ms.catlife.feature_add_cat.domain.use_cases.crud

import com.google.common.truth.Truth.assertThat
import com.ms.catlife.feature_add_cat.data.repository.FakeCatRepository
import com.ms.catlife.feature_main.domain.model.Cat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class InsertCatUseCaseTest {

    private lateinit var insertCatUseCase: InsertCatUseCase
    private lateinit var getAllCatsUseCase: GetAllCatsUseCase
    private lateinit var fakeCatRepository: FakeCatRepository

    @Before
    fun setUp() {
        fakeCatRepository = FakeCatRepository()
        getAllCatsUseCase = GetAllCatsUseCase(fakeCatRepository)
        insertCatUseCase = InsertCatUseCase(fakeCatRepository)
    }

    @Test
    fun `insert cat, retrieve it`() = runBlocking {
        val catsToInsert = Cat(
            name = "Oreo",
            gender = true,
            neutered = true,
            birthdate = 1529602002000,
            weight = 3.4f,
            race = "Européen",
            coat = "Noir et blanc"
        )

        insertCatUseCase.invoke(catsToInsert)

        assertThat("Oreo").isEqualTo(getAllCatsUseCase.invoke().first()[0].name)
    }
}