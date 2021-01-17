package com.example.data.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchWord(
    @PrimaryKey
    @ColumnInfo(name = "query")
    val query: String
)