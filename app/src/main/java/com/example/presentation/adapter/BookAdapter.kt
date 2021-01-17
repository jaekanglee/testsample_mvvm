package com.example.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.presentation.databinding.BookItemBinding
import com.example.presentation.model.Book
import com.example.presentation.ui.main.MainViewModel

class BookAdapter(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<BookAdapter.BookHolder>() {

    var bookList = ArrayList<Book>()
    var nextPage = 0
    var query = ""
    var canLoadMore = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BookHolder(
        BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = bookList.size

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        //밑에서 4번째 아이템(총 아이템 개수 -4)에 도달하면 더 많은 아이템을 로드. currentPage가 -1이면 마지막 페이지.
        if (position > bookList.size - 4 && nextPage != -1 && canLoadMore && bookList.size >= 10) {
            // Log.e("Notify", "load page $nextPage $query")
            canLoadMore = false
            viewModel.executeSearch(nextPage, query)
        }

        onItemClickListener?.let {
            holder.holderLayout.setOnClickListener { v ->
                holder.imageView.transitionName = "image"
                viewModel.setBookDetailData(bookList[position])
                onItemClickListener?.onClick(v, position, holder)
            }
        }

        holder.bind(bookList[position])
    }

    class BookHolder(private val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val imageView = binding.imageViewBookCover
        val holderLayout = binding.holderLayout

        fun bind(item: Book) {
            imageView.run {
                Glide.with(context).load(item.cover).override(SIZE_ORIGINAL).into(this)
            }
            binding.item = item
        }
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(
            view: View,
            position: Int,
            holder: BookHolder
        )
    }

    fun onItemClick(listener: (v: View, position: Int, holder: BookHolder) -> Unit) {
        onItemClickListener = object : OnItemClickListener {
            override fun onClick(view: View, position: Int, holder: BookHolder) {
                listener(view, position, holder)
            }
        }
    }

}