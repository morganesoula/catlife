package com.ms.catlife.feature_add_cat.data.data_source

import android.net.Uri
import androidx.room.TypeConverter

class Converters {

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromString(value: String?): Uri? = value?.let { Uri.parse(value) }

        @TypeConverter
        @JvmStatic
        fun toString(uri: Uri?): String? = uri?.toString()
    }
}