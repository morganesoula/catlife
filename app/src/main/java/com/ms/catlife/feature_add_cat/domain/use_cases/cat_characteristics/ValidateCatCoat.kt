package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

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