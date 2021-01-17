package com.example.domain.repository

import com.example.data.entitiy.BestSellerData
import com.example.data.entitiy.BookSearchResult
import com.example.domain.Result
import io.reactivex.Single

interface BookRepository {
    fun getBestSeller(categoryId: Int) : Single<Result<BestSellerData>>
    fun searchBook(page:Int, categoryId: Int, query: String) : Single<Result<BookSearchResult>>
}