package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entitiy.SearchWord
import io.reactivex.Single

@Dao
interface SearchWordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchWord: SearchWord) : Single<Long>

    @Query("Select * From searchword")
    fun getAllSearchWord() : Single<List<SearchWord>>
}