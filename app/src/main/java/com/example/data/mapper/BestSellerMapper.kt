package com.example.data.mapper

import com.example.data.entitiy.BestSellerData
import com.example.domain.Result


object BestSellerMapper : NetworkMapper<BestSellerData>() {
    override fun mapTo(data: BestSellerData): Result<BestSellerData> {
        return if (data.returnCode == "000") {
            Result.Success(data)
        } else
            Result.Error("server")
    }
}