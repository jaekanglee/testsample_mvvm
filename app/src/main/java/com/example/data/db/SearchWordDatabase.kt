package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.SearchWordDao
import com.example.data.entitiy.SearchWord

@Database(entities = [(SearchWord::class)], version = 3)
abstract class SearchWordDatabase: RoomDatabase() {
    abstract fun searchWordDao() : SearchWordDao
}