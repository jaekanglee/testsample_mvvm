package com.example.data.repository

import com.example.data.datasource.SearchWordLocalDataSource
import com.example.data.entitiy.SearchWord
import com.example.data.mapper.SearchWordMapper
import com.example.domain.repository.SearchWordRepository
import io.reactivex.Single
import com.example.domain.Result
class SearchWordRepositoryImpl(private val searchWordLocalDataSource: SearchWordLocalDataSource) : SearchWordRepository {

    override fun saveSearchWord(searchWord: String): Single<Result<Long>> {
        return searchWordLocalDataSource.saveSearchWord(SearchWord(searchWord)).map(SearchWordMapper::mapInsertToResult)
    }

    override fun getAllSearchWord(): Single<Result<List<SearchWord>>> {
        return searchWordLocalDataSource.getAllSearchWord().map(SearchWordMapper::mapSearchWordToResult)
    }
}