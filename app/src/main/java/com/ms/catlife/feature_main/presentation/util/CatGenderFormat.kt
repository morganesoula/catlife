package com.ms.catlife.feature_main.presentation.util

import android.content.Context
import com.ms.catlife.R

object CatGenderFormat {
    fun isMaleFromString(gender: String, context: Context): Boolean = gender == context.getString(R.string.male)
}