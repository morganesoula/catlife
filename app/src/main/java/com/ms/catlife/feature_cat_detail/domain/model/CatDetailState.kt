package com.ms.catlife.feature_cat_detail.domain.model

data class CatDetailState(
    val catId: Int = 0,
    val catName: String = "",
    val catProfilePath: String? = null,
    val catBirthdate: Long = 0L
)
