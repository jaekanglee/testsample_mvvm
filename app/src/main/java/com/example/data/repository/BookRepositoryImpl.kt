package com.example.data.repository

import com.example.data.datasource.BookRemoteDataSource
import com.example.data.entitiy.BestSellerData
import com.example.data.entitiy.BookSearchResult
import com.example.data.mapper.BestSellerMapper
import com.example.data.mapper.BookSearchResultMapper
import com.example.domain.Result
import com.example.domain.repository.BookRepository
import io.reactivex.Single

class BookRepositoryImpl(private val remoteDataDataSource: BookRemoteDataSource) : BookRepository {

    override fun getBestSeller(categoryId: Int): Single<Result<BestSellerData>> {
        return remoteDataDataSource.getBestSeller(categoryId).map(BestSellerMapper::map)
    }

    override fun searchBook(
        page:Int,
        categoryId: Int,
        query: String
    ): Single<Result<BookSearchResult>> {
        return remoteDataDataSource.searchBook(page,categoryId, query).map(BookSearchResultMapper::map)
    }
}