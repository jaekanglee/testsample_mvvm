package com.example.data.datasource

import com.example.data.api.API_KEY
import com.example.data.api.BookApi
import com.example.data.entitiy.BestSellerData
import com.example.data.entitiy.BookSearchResult
import io.reactivex.Single
import retrofit2.Response

class BookRemoteDataSource(private val api: BookApi) {
     fun getBestSeller(categoryId: Int): Single<Response<BestSellerData>> {
        return api.getBestSeller(API_KEY, categoryId, "json")
    }

     fun searchBook(
        page:Int,
        categoryId: Int,
        query: String
    ): Single<Response<BookSearchResult>> {
        return api.searchBook(API_KEY, page,categoryId, query, "json")
    }

}