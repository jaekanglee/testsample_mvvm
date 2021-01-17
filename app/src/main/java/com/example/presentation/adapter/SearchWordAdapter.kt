package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.SearchWordItemBinding
import com.example.presentation.model.SearchWord
import com.example.presentation.ui.search.SearchViewModel

class SearchWordAdapter(private val viewModel: SearchViewModel) :
    RecyclerView.Adapter<SearchWordAdapter.SearchWordHolder>() {

    var searchWordList = ArrayList<SearchWord>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchWordHolder(SearchWordItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            viewModel = this@SearchWordAdapter.viewModel
        })


    override fun getItemCount() = searchWordList.size

    override fun onBindViewHolder(holder: SearchWordHolder, position: Int) {
        holder.bind(searchWordList[position])
    }


    class SearchWordHolder(private val binding: SearchWordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchWord) {
            binding.item = item
        }
    }

}