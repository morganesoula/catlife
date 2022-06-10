package com.ms.catlife.domain.use_case.add_cat_form

import com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics.ValidateCatName
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidateCatNameTest {

    private lateinit var validateCatName: ValidateCatName

    @Before
    fun setUp() {
        validateCatName = ValidateCatName()
    }

    @Test
    fun `Cat name is digit-only, returns error`() {
        val result = validateCatName.execute("123")

        assertEquals(result.successful, false)
    }

    @Test
    fun `Cat name is blank, returns error`() {
        val result = validateCatName.execute("")

        assertEquals(result.successful, false)
    }

    @Test
    fun `Cat name is good, returns no error`() {
        val result = validateCatName.execute("Oreo")

        assertEquals(result.successful, true)
    }
}