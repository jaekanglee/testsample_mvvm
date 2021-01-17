package com.example.data.entitiy

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("itemId")
    val itemId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("putData")
    val putData: String,
    @SerializedName("priceStandard")
    val priceStandard: Int,
    @SerializedName("priceSales")
    val priceSales: Int,
    @SerializedName("discountRate")
    val discountRate: String,
    @SerializedName("saleStatus")
    val saleStatus: String,
    @SerializedName("mileage")
    val mileage: String,
    @SerializedName("mileageRate")
    val mileageRate: String,
    @SerializedName("coverSmallUrl")
    val coverSmallUrl: String,
    @SerializedName("coverLargeUrl")
    val coverLargeUrl: String,
    @SerializedName("categoryId")
    val categoryId: String,
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("customerReviewRank")
    val customerReviewRank: Float,
    @SerializedName("author")
    val author: String,
    @SerializedName("translator")
    val translator: String,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("mobileLink")
    val mobileLink: String,
    @SerializedName("additionalLink")
    val additionalLink: String,
    @SerializedName("reviewCount")
    val reviewCount: String,
    @SerializedName("rank")
    val rank: Int
)