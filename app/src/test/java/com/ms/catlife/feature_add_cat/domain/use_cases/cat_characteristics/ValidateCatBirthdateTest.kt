package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidateCatBirthdateTest {

    private lateinit var validateBirthdate: ValidateCatBirthdate

    @Before
    fun setUp() {
        validateBirthdate = ValidateCatBirthdate()
    }

    @Test
    fun `birthdate is 0, returns error`() {
        val result = validateBirthdate.execute(0)
        assertEquals(result.successful, false)
    }

    @Test
    fun `birthdate is superior to current date, returns error`() {
        val result = validateBirthdate.execute(1686419817000)
        assertEquals(result.successful, false)
    }

    @Test
    fun `birthdate is inferior of current date by 34 years, returns error`() {
        val result = validateBirthdate.execute(581969678000)
        assertEquals(result.successful, false)
    }

    @Test
    fun `cat is 4 years old, returns no error`() {
        val result = validateBirthdate.execute(1528654478000)
        assertEquals(result.successful, true)
    }
}