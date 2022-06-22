package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

class ValidateCatCoat {

    fun execute(catCoat: String): ValidationResult {

        if (catCoat.isBlank()) {
            return ValidationResult(
                false, "blankError"
            )
        }

        val numericRegex = "-?\\d+(\\.\\d+)?".toRegex()

        if (catCoat.contains(numericRegex)) {
            return ValidationResult(
                false, "no numbers allowed"
            )
        }

        return ValidationResult(true)
    }
}