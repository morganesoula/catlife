package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class ValidateFleaDateTest {

    private lateinit var validateFleaDate: ValidateCatFleaDate

    @Before
    fun setUp() {
        validateFleaDate = ValidateCatFleaDate()
    }

    @Test
    fun `flea date value is empty, returns no error`() {
        val result = validateFleaDate.execute(0)
        TestCase.assertEquals(result.successful, true)
    }

    @Test
    fun `flea date value is not 0 and ulterior to current date, returns error`() {
        val result = validateFleaDate.execute(1718043278000)
        TestCase.assertEquals(result.successful, false)
    }

    @Test
    fun `flea date value is inferior by 34 years from now on, returns error`() {
        val result = validateFleaDate.execute(581969678000)
        TestCase.assertEquals(result.successful, false)
    }

    @Test
    fun `flea date value is set 4 years from now on, returns true`() {
        val result = validateFleaDate.execute(1528654478000)
        TestCase.assertEquals(result.successful, true)
    }
}