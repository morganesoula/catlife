package com.ms.catlife.domain.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cat(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val gender: String,
    val name: String,
    val profilePicture: Uri? = null,
    val birthdate: String,
    val weight: Int,
    val race: String,
    val coat: String,
    val diseases: String? = null,
    val vaccineDate: String? = null,
    val dewormingDate: String? = null,
    val fleaDate: String? = null
) : java.io.Serializable
