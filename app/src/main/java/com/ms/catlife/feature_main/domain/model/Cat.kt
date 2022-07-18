package com.ms.catlife.feature_main.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cat(
    @PrimaryKey val id: Int? = null,
    val gender: Boolean,
    val neutered: Boolean,
    val name: String,
    val profilePicturePath: String? = null,
    val birthdate: Long,
    val weight: Float,
    val race: String,
    val coat: String,
    val diseases: String? = null,
    val vaccineDate: Long? = null,
    val dewormingDate: Long? = null,
    val fleaDate: Long? = null
) : java.io.Serializable
