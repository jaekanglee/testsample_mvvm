package com.example.presentation.mapper

import com.example.data.entitiy.SearchWord

object SearchWordMapper {
    fun searchWordMapper(queryList: List<SearchWord>) : ArrayList<com.example.presentation.model.SearchWord> {
        return ArrayList(
            queryList.map { com.example.presentation.model.SearchWord(it.query) }
        )
    }
}