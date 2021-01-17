package com.example.presentation.mapper

import com.example.data.entitiy.BestSellerData
import com.example.data.entitiy.BookSearchResult
import com.example.presentation.model.Book
import com.example.presentation.model.BookList

object BookMapper {
    fun bestSellerToBook(bestSellerData: BestSellerData): BookList {
        return with(bestSellerData) {
            BookList(
                this.totalResults,
                this.startIndex,
                this.title,
                String(),
                this.searchCategoryId,
                ArrayList(this.item.map {
                    Book(
                        it.itemId,
                        it.title,
                        it.description,
                        it.coverLargeUrl,
                        it.author,
                        it.link,
                        it.customerReviewRank
                    )
                })
            )
        }
    }

    fun searchBokResultToBook(bookSearchResult: BookSearchResult): BookList {
        return with(bookSearchResult) {
            BookList(
                this.totalResults,
                this.startIndex,
                this.title,
                this.query,
                this.searchCategoryId,
                ArrayList(this.item.map {
                    Book(
                        it.itemId,
                        it.title,
                        it.description,
                        it.coverLargeUrl,
                        it.author,
                        it.link,
                        it.customerReviewRank
                    )
                })
            )
        }
    }
}