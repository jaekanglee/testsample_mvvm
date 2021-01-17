package com.example.data.api

import com.example.data.entitiy.BestSellerData
import com.example.data.entitiy.BookSearchResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET("bestSeller.api")
    fun getBestSeller(
        @Query("key") apiKey: String,
        @Query("categoryId") categoryId: Int,
        @Query("output") output: String
    ): Single<retrofit2.Response<BestSellerData>>

    @GET("search.api")
    fun searchBook(
        @Query("key") apiKey: String,
        @Query("start") page : Int,
        @Query("categoryId") categoryId: Int,
        @Query("query") query: String,
        @Query("output") output: String
    ) : Single<retrofit2.Response<BookSearchResult>>
}