package com.example.presentation.model

data class BookList(
    val totalResult: Int,
    val startIndex: Int,
    val searchType: String,
    val query: String,
    val categoryId: String,
    val item: ArrayList<Book>
)