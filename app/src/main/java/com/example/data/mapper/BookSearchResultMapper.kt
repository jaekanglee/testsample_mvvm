package com.example.data.mapper

import com.example.data.entitiy.BookSearchResult
import com.example.domain.Result

object BookSearchResultMapper: NetworkMapper<BookSearchResult>() {
    override fun mapTo(data: BookSearchResult): Result<BookSearchResult> {
        return if (data.returnCode == "000") {
            Result.Success(data)
        } else
            Result.Error("server")
    }
}