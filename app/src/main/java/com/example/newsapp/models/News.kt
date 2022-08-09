package com.example.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity
data class News(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title: String,
    val createdAr: Long
) : Serializable
