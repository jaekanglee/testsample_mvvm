package com.example.presentation.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.BookCategoryItemBinding
import com.example.presentation.model.BookCategory
import com.example.presentation.ui.main.MainViewModel

class BookCategoryAdapter(val viewModel: MainViewModel, private val bookCategoryList: ArrayList<BookCategory>) :
    RecyclerView.Adapter<BookCategoryAdapter.BookCategoryHolder>() {

    //var bookCategoryList = ArrayList<BookCategory>()
    var selectedBookCategory: BookCategory? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BookCategoryHolder(
        BookCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).apply {
            viewModel = this@BookCategoryAdapter.viewModel
        }
    )

    override fun getItemCount() = bookCategoryList.size

    override fun onBindViewHolder(holder: BookCategoryHolder, position: Int) {

        selectedBookCategory?.let {
            if (bookCategoryList[position] == it)
                holder.holderLayout.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FF7CE4BE"))
            else
                holder.holderLayout.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1EB980"))
        }

        holder.bind(bookCategoryList[position])
    }

    class BookCategoryHolder(private val binding: BookCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val holderLayout = binding.holderLayout

        fun bind(item: BookCategory) {
            binding.item = item
        }
    }

}