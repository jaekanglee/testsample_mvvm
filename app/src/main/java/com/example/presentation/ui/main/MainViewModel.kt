package com.example.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.usecase.GetBestSellerUseCase
import com.example.domain.usecase.SearchBookUseCase
import com.example.presentation.BaseViewModel
import com.example.presentation.utils.Event
import com.example.presentation.mapper.BookMapper
import com.example.presentation.model.Book
import com.example.presentation.model.BookCategory
import com.example.presentation.model.BookList
import com.example.presentation.utils.BookDetail

class MainViewModel(
    private val getBestSellerUseCase: GetBestSellerUseCase,
    private val searchBookUseCase: SearchBookUseCase ) : BaseViewModel() {

    //true : expanded / false : hide
    private var isBackDropExpanded = false

    //전체 book list가 이곳에 저장됨
    private val allBookList = ArrayList<Book>()

    val bookCategoryList = BookCategory.values().toCollection(ArrayList())

    private val _backDropState = MutableLiveData<Boolean>()
    val backDropState: LiveData<Boolean>
        get() = _backDropState

    private val getBestSellerListResult = getBestSellerUseCase.observe()
    private val searchBookListResult = searchBookUseCase.observe()

    private val _bookList = MediatorLiveData<BookList>()
    val bookList: LiveData<BookList>
        get() = _bookList

    private val _searchResultCount = MutableLiveData<String>()
    val searchResultCount: LiveData<String>
        get() = _searchResultCount

    //executeSearch에서 query를 이곳에 저장해야함
    private val _searchQuery = MutableLiveData<String>("베스트셀러 목록")
    val searchQuery: LiveData<String>
        get() = _searchQuery

    private val _selectedBookCategory = MutableLiveData<BookCategory>()
    val selectedBookCategory: LiveData<BookCategory>
        get() = _selectedBookCategory

    init {

        _selectedBookCategory.value = bookCategoryList.first()

        getBestSellerListResult.onSuccess(_bookList) { result ->
            val bookList = BookMapper.bestSellerToBook(result.data)
            _bookList.value = bookList
            _searchResultCount.value = "총 ${bookList.totalResult}개"
        }
        
        searchBookListResult.onSuccess(_bookList) { result ->
            val bookList = BookMapper.searchBokResultToBook(result.data)
            _bookList.value = bookList.apply {
                allBookList.addAll(this.item)
                this.item.clear()
                this.item.addAll(allBookList)
            }
            _searchResultCount.value = "총 ${bookList.totalResult}개"
        }

        getBestSellerListResult.onError(_error) {
            callNetworkError()
        }

        searchBookListResult.onError(_error) {
            callNetworkError()
        }


        executeSearch(1, null)
    }

    fun setBookDetailData(book: Book) {
        BookDetail.book = book
    }

    private fun callNetworkError() {
        _error.value = Event("network")
    }

    fun toggleBackDropState() {
        isBackDropExpanded = !isBackDropExpanded
        _backDropState.value = isBackDropExpanded
    }

    fun setSelectedCategoryItem(item: BookCategory) {
        _selectedBookCategory.value = item
        executeSearch(1, searchQuery.value)
        toggleBackDropState()
    }

    //키보드 입력으로 함수를 실행할 때 실행 전 searchQuery와 입력된 값을 비교 후 다를 때만 실행해야함
    fun executeSearch(page: Int, query: String?) {
        if (page == 1) allBookList.clear()
        selectedBookCategory.value?.let { category ->
            query.doOnNotNullNotEmpty({ query ->
                _searchQuery.value = query
                searchBookUseCase(Triple(page, category.id, query))
            }, {
                _searchQuery.value = "베스트셀러 목록"
                this(getBestSellerUseCase(category.id))
            })
        }

    }

    private fun String?.doOnNotNullNotEmpty(
        doOnSuccess: (str: String) -> Unit,
        doOnBlankOrNull: () -> Unit
    ) {
        this?.let {
            if (it == "베스트셀러 목록" || it.isBlank())
                doOnBlankOrNull()
            else
                doOnSuccess(it)
        } ?: run {
            doOnBlankOrNull()
        }

    }

}