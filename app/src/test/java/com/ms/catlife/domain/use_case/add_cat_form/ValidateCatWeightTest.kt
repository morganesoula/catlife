package com.ms.catlife.domain.use_case.add_cat_form

import com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics.ValidateCatWeight
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidateCatWeightTest {

    private lateinit var validateCatWeight: ValidateCatWeight

    @Before
    fun setUp() {
        validateCatWeight = ValidateCatWeight()
    }

    @Test
    fun `Weight is inferior to 0,1 , returns error`() {
        val result = validateCatWeight.execute("0.0")

        assertEquals(result.successful, false)
    }

    @Test
    fun `Weight is superior to 22, returns error`() {
        val result = validateCatWeight.execute("23.0")

        assertEquals(result.successful, false)
    }

    @Test
    fun `Weight is between 0,1 and 22, returns no error`() {
        val result = validateCatWeight.execute("6.0")

        assertEquals(result.successful, true)
    }
}