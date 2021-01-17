package com.example.domain.usecase

import com.example.data.entitiy.SearchWord
import com.example.domain.Result
import com.example.domain.repository.SearchWordRepository
import io.reactivex.Single

class GetSearchWordUseCase(private val searchWordRepository: SearchWordRepository): SingleUseCase<Unit, List<SearchWord>>() {
    override fun execute(parameter: Unit): Single<Result<List<SearchWord>>> {
        return searchWordRepository.getAllSearchWord()
    }
}