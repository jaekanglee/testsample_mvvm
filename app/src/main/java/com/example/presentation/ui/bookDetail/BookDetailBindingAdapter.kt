package com.example.presentation.ui.bookDetail

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun setImage(imageView: ImageView, url: String) {
    imageView.run {
        Glide.with(context).load(url).into(this)
    }
}

@BindingAdapter("viewModel", "link")
fun openLink(textView: TextView, viewModel: BookDetailViewModel, link: String) {
    textView.setOnClickListener {
        viewModel.executeOpenChrome(link)
    }
}