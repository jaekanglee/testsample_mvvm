package com.example.data.entitiy

import com.google.gson.annotations.SerializedName

data class BestSellerData(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link:String,
    @SerializedName("language")
    val language: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("putData")
    val putData: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("startIndex")
    val startIndex: Int,
    @SerializedName("itemPerPage")
    val itemPerPage: Int,
    @SerializedName("maxResults")
    val maxResults: Int,
    @SerializedName("queryType")
    val queryType: String,
    @SerializedName("searchCategoryId")
    val searchCategoryId: String,
    @SerializedName("searchCategoryName")
    val searchCategoryName: String,
    @SerializedName("returnCode")
    val returnCode: String,
    @SerializedName("returnMessage")
    val returnMessage: String,
    @SerializedName("item")
    val item: ArrayList<BestSeller>
)