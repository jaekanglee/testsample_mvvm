package com.example.presentation.ui.bookDetail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.presentation.R
import com.example.presentation.databinding.ActivityBookDetailBinding
import com.example.presentation.utils.EventObserver
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityBookDetailBinding
    private val viewModel: BookDetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityBookDetailBinding>(
            this, R.layout.activity_book_detail
        ).apply {
            viewModel = this@BookDetailActivity.viewModel
        }

        binding.imageViewBack.setOnClickListener {
            onBackPressed()
        }

        initObserver()
    }

    private fun initObserver() {
        viewModel.error.observe(this, EventObserver {
            if (it == "No book") {
                Snackbar.make(binding.motionLayout, "알 수 없는 오류 발생", Snackbar.LENGTH_LONG).show()
                onBackPressed()
            }
        })

        viewModel.openChrome.observe(this, EventObserver {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.setPackage("com.android.chrome")
            try {
                startActivity(i)
            } catch (e: ActivityNotFoundException) {
                Snackbar.make(binding.motionLayout, "링크를 열 수 없습니다.", Snackbar.LENGTH_LONG).show()
            }
        })
    }
}
