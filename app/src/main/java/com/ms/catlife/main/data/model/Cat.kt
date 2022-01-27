package com.ms.catlife.main.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cat(
    @PrimaryKey(autoGenerate = true) val id: Int, val name: String, val profilePicture: Int, val age: Int
)
