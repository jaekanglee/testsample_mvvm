package com.example.presentation.ui.search

import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.usecase.GetSearchWordUseCase
import com.example.domain.usecase.SaveSearchWordUseCase
import com.example.presentation.BaseViewModel
import com.example.presentation.mapper.SearchWordMapper
import com.example.presentation.model.SearchWord
import com.example.presentation.utils.Event

class SearchViewModel(
    private val saveSearchWordUseCase: SaveSearchWordUseCase,
    getSearchWordUseCase: GetSearchWordUseCase
) : BaseViewModel() {

    val query = ObservableField<String>()

    private val _searchQuery = MediatorLiveData<Event<String>>()
    val searchQuery: LiveData<Event<String>>
        get() = _searchQuery

    private val _searchWord = MediatorLiveData<ArrayList<SearchWord>>()
    val searchWord: LiveData<ArrayList<SearchWord>>
        get() = _searchWord

    private val saveSearchWordResult = saveSearchWordUseCase.observe()
    private val getSearchWordResult = getSearchWordUseCase.observe()

    init {
        saveSearchWordResult.onSuccess(_searchQuery) {
            Log.e("Value", it.data.toString())
            if (it.data.toInt() != -1)
                _searchQuery.value = Event(query.get()!!)
            else
                _searchQuery.value = Event("")
        }

        getSearchWordUseCase(Unit)

        getSearchWordResult.onSuccess(_searchQuery) {
            val query = SearchWordMapper.searchWordMapper(it.data)
            _searchWord.value = query
        }
    }

    fun executeSearch(text: String?) {
        if (text != null)
            query.set(text)
        query.get()?.let {
            if (it.isNotBlank())
                saveSearchWordUseCase(it)
            else
                _searchQuery.value = Event("")
        } ?: run {
            _searchQuery.value = Event("")
        }
    }

    fun clearQuery() {
        query.set("")
    }

}