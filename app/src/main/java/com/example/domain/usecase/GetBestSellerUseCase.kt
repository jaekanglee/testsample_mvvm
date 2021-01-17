package com.example.domain.usecase

import com.example.data.entitiy.BestSellerData
import com.example.domain.Result
import com.example.domain.repository.BookRepository
import io.reactivex.Single

class GetBestSellerUseCase(private val repository: BookRepository) : SingleUseCase<Int, BestSellerData>() {
    override fun execute(parameter: Int): Single<Result<BestSellerData>> {
        return repository.getBestSeller(parameter)
    }
}