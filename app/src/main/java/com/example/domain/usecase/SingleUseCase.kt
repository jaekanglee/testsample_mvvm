package com.example.domain.usecase

import android.annotation.SuppressLint
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import com.example.domain.Result
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io

abstract class SingleUseCase<T, R>() {
    private val result = MutableLiveData<Result<R>>()
    fun observe() = result

    @SuppressLint("CheckResult")
    operator fun invoke(parameter: T): Disposable {
        return execute(parameter).subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                result.value = it
            }, {
                result.value = Result.Error("server")
            })
    }

    abstract fun execute(parameter: T): Single<Result<R>>
}
