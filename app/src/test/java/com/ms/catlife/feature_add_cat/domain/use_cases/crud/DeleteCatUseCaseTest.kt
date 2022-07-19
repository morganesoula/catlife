package com.ms.catlife.feature_add_cat.domain.use_cases.crud

import com.google.common.truth.Truth.assertThat
import com.ms.catlife.feature_add_cat.data.repository.FakeCatRepository
import com.ms.catlife.feature_main.domain.model.Cat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteCatUseCaseTest {

    private lateinit var deleteCatUseCase: DeleteCatUseCase
    private lateinit var getCatUseCase: GetCatUseCase
    private lateinit var insertCatUseCase: InsertCatUseCase
    private lateinit var fakeCatRepository: FakeCatRepository

    @Before
    fun setUp() {
        fakeCatRepository = FakeCatRepository()
        insertCatUseCase = InsertCatUseCase(fakeCatRepository)
        getCatUseCase = GetCatUseCase(fakeCatRepository)
        deleteCatUseCase = DeleteCatUseCase(fakeCatRepository)
    }

    @Test
    fun `Insert, fetch, delete, fetch, is deleted`() = runBlocking {
        val catToDelete = Cat(
            id = 1,
            name = "Oreo",
            neutered = true,
            gender = true,
            birthdate = 1529602002000,
            weight = 3.4f,
            race = "Européen",
            coat = "Noir et blanc"
        )

        insertCatUseCase.invoke(catToDelete)
        val catFetched = getCatUseCase.invoke(1)

        assertThat(catFetched).isNotNull()
        assertThat(catFetched?.name).isEqualTo("Oreo")

        deleteCatUseCase.invoke(catToDelete)
        val catFetchedAgain = getCatUseCase.invoke(1)

        assertThat(catFetchedAgain).isNull()
    }
}