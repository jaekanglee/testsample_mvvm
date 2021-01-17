package com.example.data.datasource

import com.example.data.dao.SearchWordDao
import com.example.data.db.SearchWordDatabase
import com.example.data.entitiy.SearchWord
import io.reactivex.Single

class SearchWordLocalDataSource(private val searchWordDatabase: SearchWordDatabase) {

    private val searchWordDao = searchWordDatabase.searchWordDao()

     fun saveSearchWord(searchWord: SearchWord): Single<Long> {
        return searchWordDao.insert(searchWord)
    }

     fun getAllSearchWord(): Single<List<SearchWord>> {
        return searchWordDao.getAllSearchWord()
    }

}