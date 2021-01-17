package com.example.data.mapper

import com.example.data.entitiy.SearchWord
import com.example.domain.Result

object SearchWordMapper {
    fun mapSearchWordToResult(searchWordList : List<SearchWord>) : Result<List<SearchWord>> {
        return if (searchWordList.isEmpty())
            Result.Error("server")
        else
            Result.Success(searchWordList)
    }

    fun  mapInsertToResult(value : Long) : Result<Long> {
        return if (value.toInt() != -1)
            Result.Success(value)
        else
            Result.Error("server")
    }
}