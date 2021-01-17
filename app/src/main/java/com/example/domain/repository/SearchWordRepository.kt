package com.example.domain.repository

import com.example.data.entitiy.SearchWord
import io.reactivex.Single
import com.example.domain.Result
interface SearchWordRepository {
    fun saveSearchWord(searchWord: String) : Single<Result<Long>>
    fun getAllSearchWord() : Single<Result<List<SearchWord>>>
}