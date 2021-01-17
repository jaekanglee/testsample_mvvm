package com.example.domain.usecase

import com.example.data.entitiy.BookSearchResult
import com.example.domain.Result
import com.example.domain.repository.BookRepository
import io.reactivex.Single

class SearchBookUseCase(private val repository: BookRepository) : SingleUseCase<Triple<Int,Int, String>, BookSearchResult>() {
    override fun execute(parameter: Triple<Int,Int, String>): Single<Result<BookSearchResult>> {
        return repository.searchBook(parameter.first, parameter.second, parameter.third)
    }
}