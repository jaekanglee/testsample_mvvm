package com.example.presentation.model

data class Book(
    val itemId: Int,
    val title: String,
    val description: String,
    val cover: String,
    val author: String,
    val link: String,
    val score: Float
)