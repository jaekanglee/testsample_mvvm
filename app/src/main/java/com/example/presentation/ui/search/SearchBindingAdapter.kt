package com.example.presentation.ui.search

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.adapter.SearchWordAdapter
import com.example.presentation.model.SearchWord

@BindingAdapter("viewModel")
fun onEditTextInputDone(editText: EditText, viewModel: SearchViewModel) {
    editText.setOnEditorActionListener { _, actionId, _ ->
        when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> {
                viewModel.executeSearch(null)
                true
            }
            else -> false
        }
    }
}

@BindingAdapter("item")
fun setItem(recyclerView: RecyclerView, searchWordList: ArrayList<SearchWord>?) {
    val searchWordAdapter: SearchWordAdapter

    if (recyclerView.adapter == null)
        return
    else
        searchWordAdapter = recyclerView.adapter as SearchWordAdapter

    searchWordList?.let {
        searchWordAdapter.searchWordList = it.apply { reverse() }
        searchWordAdapter.notifyDataSetChanged()
    }
}