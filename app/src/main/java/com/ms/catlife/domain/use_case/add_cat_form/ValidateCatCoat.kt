package com.ms.catlife.domain.use_case.add_cat_form

class ValidateCatCoat {

    fun execute(catCoat: String): ValidationResult {

        if (catCoat.isBlank()) {
            return ValidationResult(
                false, "blankError"
            )
        }

        val numericRegex = "-?[0-9]+(\\.[0-9]+)?".toRegex()

        if (catCoat.matches(numericRegex)) {
            return ValidationResult(
                false, "noNumberError"
            )
        }

        return ValidationResult(true)
    }
}