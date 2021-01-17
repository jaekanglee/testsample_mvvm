package com.example.presentation.ui.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.adapter.BookAdapter
import com.example.presentation.adapter.BookCategoryAdapter
import com.example.presentation.model.BookCategory
import com.example.presentation.model.BookList
import java.net.URLDecoder

@BindingAdapter("viewModel")
fun onNavigationIconClick(toolbar: Toolbar, viewModel: MainViewModel) {
    toolbar.setNavigationOnClickListener {
        viewModel.toggleBackDropState()
    }
}

@BindingAdapter("toolbar", "state") //toggleBackDropState에 의한 backDrop의 변화에 의해 호출됨
fun changeBackDropState(motionLayout: MotionLayout, toolbar: Toolbar, state: Boolean) {
    if (state) {
        motionLayout.transitionToEnd()
        toolbar.setNavigationIcon(R.drawable.ic_close)
    } else {
        motionLayout.transitionToStart()
        toolbar.setNavigationIcon(R.drawable.ic_menu)
    }
}

@BindingAdapter("selectedItem")
fun selectedBookCategory(recyclerView: RecyclerView, item: BookCategory) {
    val bookCategoryAdapter: BookCategoryAdapter

    if (recyclerView.adapter == null) {
        return
    } else {
        bookCategoryAdapter = recyclerView.adapter as BookCategoryAdapter
    }

    bookCategoryAdapter.selectedBookCategory = item
    bookCategoryAdapter.notifyDataSetChanged()
}

@BindingAdapter("clear", "noResult", "item")
fun setBookList(
    recyclerView: RecyclerView,
    imageViewClear: ImageView,
    textViewNoResult: TextView,
    item: BookList?
) {
    val bookAdapter: BookAdapter

    if (recyclerView.adapter == null) {
        return
    } else {
        bookAdapter = recyclerView.adapter as BookAdapter
    }

    item?.let {
        //check if search result is empty
        if (it.startIndex == 1 && it.item.isEmpty())
            textViewNoResult.visibility = View.VISIBLE
        else
            textViewNoResult.visibility = View.GONE

        //check search result type
        if (it.searchType != "인터파크도서검색결과") {
            bookAdapter.nextPage = -1
            recyclerView.scrollToPosition(0)
            imageViewClear.visibility = View.GONE
        } else {
            if (it.item.size == it.totalResult)
                bookAdapter.nextPage = -1
            else
                bookAdapter.nextPage = it.startIndex + 1
            imageViewClear.visibility = View.VISIBLE
        }

        bookAdapter.bookList = it.item
        bookAdapter.query = URLDecoder.decode(it.query, "UTF-8")
        bookAdapter.notifyDataSetChanged()
        bookAdapter.canLoadMore = true
    }

}

