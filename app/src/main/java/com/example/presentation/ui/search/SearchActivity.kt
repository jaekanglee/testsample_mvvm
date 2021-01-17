package com.example.presentation.ui.search

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.presentation.R
import com.example.presentation.adapter.SearchWordAdapter
import com.example.presentation.databinding.ActivitySearchBinding
import com.example.presentation.utils.EventObserver
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivitySearchBinding>(this, R.layout.activity_search)
                .apply {
                    lifecycleOwner = this@SearchActivity
                    viewModel = this@SearchActivity.viewModel
                }


        binding.imageViewBack.setOnClickListener {
            hideKeyboard()
            onBackPressed()
        }

        binding.recyclerViewSearchWord.apply {
            setHasFixedSize(true)
            adapter = SearchWordAdapter(viewModel)
        }

        initObserver()
    }

    private fun initObserver() {
        viewModel.searchQuery.observe(this, EventObserver {
            intent.putExtra("query", it)
            setResult(Activity.RESULT_OK, intent)
            hideKeyboard()
            onBackPressed()
        })

    }


    private fun Activity.hideKeyboard() {
        hideKeyboard(if (currentFocus == null) View(this) else currentFocus!!)
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
