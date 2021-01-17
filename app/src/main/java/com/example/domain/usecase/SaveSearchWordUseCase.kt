package com.example.domain.usecase

import com.example.data.entitiy.SearchWord
import com.example.domain.Result
import com.example.domain.repository.SearchWordRepository
import io.reactivex.Single
import java.lang.StringBuilder

class SaveSearchWordUseCase(private val searchWordRepository: SearchWordRepository) : SingleUseCase<String, Long>() {
    override fun execute(parameter: String): Single<Result<Long>> {
        return searchWordRepository.saveSearchWord(parameter)
    }
}