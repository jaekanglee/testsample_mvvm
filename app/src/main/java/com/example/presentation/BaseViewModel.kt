package com.example.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import com.example.domain.Result
import com.example.presentation.utils.Event
import org.koin.core.KoinComponent

abstract class BaseViewModel() : ViewModel() {
    private val disposable = CompositeDisposable()
    protected val _error = MediatorLiveData<Event<String>>()
    val error: LiveData<Event<String>>
        get() = _error

    operator fun invoke(disposable: Disposable) {
        this.disposable.add(disposable)
    }

    fun <T, R> MutableLiveData<Result<R>>.onSuccess(observer: MediatorLiveData<T>, onSuccess: (result: Result.Success<R>) -> Unit) {
        observer.addSource(this) {
            if (it is Result.Success) {
                onSuccess(it)
            }
        }
        observer.addSource(this) {
            if (it is Result.Success) {
                onSuccess(it)
            }
        }
    }

    fun <T, R> MutableLiveData<Result<R>>.onError(observer: MediatorLiveData<Event<T>>, onError: (result: String) -> Unit) {
        observer.addSource(this) {
            if (it is Result.Error) {
                checkErrorType(it.errorMsg, onError)
            }
        }
    }

    private fun checkErrorType(errorMsg: String, onError: (result: String) -> Unit) {
        Log.e("Error Type", errorMsg)
        when (errorMsg) {
            "token" -> {
            } //토큰 처리
            else -> onError(errorMsg)
        }
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}