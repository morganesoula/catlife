package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class ValidateCatCoatTest {

    private lateinit var validateCoat: ValidateCatCoat

    @Before
    fun setUp() {
        validateCoat = ValidateCatCoat()
    }

    @Test
    fun `coat value is blank, returns error`() {
        val result = validateCoat.execute("")
        assertEquals(result.successful, false)
    }

    @Test
    fun `coat value only contains numbers, returns error`() {
        val result = validateCoat.execute("123")
        assertEquals(result.successful, false)
    }

    @Test
    fun `coat value contains numbers, returns error`() {
        val result = validateCoat.execute("Blanc123")
        assertEquals(result.successful, false)
    }

    @Test
    fun `coat value is adequate, returns no error`() {
        val result = validateCoat.execute("White and black")
        assertEquals(result.successful, true)
    }
}