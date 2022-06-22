package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

class ValidateCatName {

    fun execute(catName: String): ValidationResult {
        if (catName.isBlank()) {
            return ValidationResult(
                false, "blankError"
            )
        }

        val numericRegex = "-?\\d+(\\.\\d+)?".toRegex()

        if (catName.contains(numericRegex)) {
            return ValidationResult(
                false, "no numbers allowed"
            )
        }

        return ValidationResult(true)
    }
}