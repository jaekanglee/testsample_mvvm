package com.example.presentation.ui.bookDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.presentation.BaseViewModel
import com.example.presentation.model.Book
import com.example.presentation.utils.BookDetail
import com.example.presentation.utils.Event

class BookDetailViewModel : BaseViewModel() {

    private val _bookDetail = MutableLiveData<Book>()
    val bookDetail : LiveData<Book>
        get() = _bookDetail

    private val _openChrome = MutableLiveData<Event<String>>()
    val openChrome : LiveData<Event<String>>
        get() = _openChrome

    init {
        BookDetail.book?.let {
            _bookDetail.value = it
        } ?: run  {
            _error.value = Event("No book")
        }
    }

    fun executeOpenChrome(link: String) {
        _openChrome.value = Event(link)
    }
}