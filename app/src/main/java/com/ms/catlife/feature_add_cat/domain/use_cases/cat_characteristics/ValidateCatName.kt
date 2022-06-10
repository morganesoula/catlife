package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

class ValidateCatName {

    fun execute(catName: String): ValidationResult {
        if (catName.isBlank()) {
            return ValidationResult(
                false, "blankError"
            )
        }

        val numericRegex = "-?[0-9]+(\\.[0-9]+)?".toRegex()

        if (catName.matches(numericRegex)) {
            return ValidationResult(
                false, "noNumberError"
            )
        }

        return ValidationResult(true)
    }
}