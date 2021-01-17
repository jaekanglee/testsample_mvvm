package com.example.presentation.ui.main

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.invoke
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import com.example.presentation.R
import com.example.presentation.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.presentation.utils.EventObserver
import com.example.presentation.adapter.BookAdapter
import com.example.presentation.adapter.BookCategoryAdapter
import com.example.presentation.ui.bookDetail.BookDetailActivity
import com.example.presentation.ui.search.SearchActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    lateinit var binding: ActivityMainBinding

    private val bookAdapter: BookAdapter by lazy {
        BookAdapter(viewModel)
    }

    private val requestActivity: ActivityResultLauncher<Intent> =
        registerForActivityResult(StartActivityForResult()) {
            executeSearchBarMotion(false)
            val query: String = it.data?.getStringExtra("query") ?: return@registerForActivityResult
            if (query != viewModel.searchQuery.value && query.isNotBlank())
                viewModel.executeSearch(1, query)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                viewModel = this@MainActivity.viewModel
                lifecycleOwner = this@MainActivity //BindingAdapter 사용에 필요 ****
            }

        binding.toolbar.apply {
            inflateMenu(R.menu.main_menu)
            menu.findItem(R.id.menu_search).setOnMenuItemClickListener {
                intentToSearch()
                true
            }
        }

        binding.recyclerviewCategory.apply {
            setHasFixedSize(true)
            adapter = BookCategoryAdapter(viewModel, viewModel.bookCategoryList)
            adapter?.notifyDataSetChanged()
        }

        binding.recyclerviewBook.apply {
            setHasFixedSize(true)
            adapter = bookAdapter
        }

        bookAdapter.onItemClick { _, position, holder ->
            val activityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, holder.imageView, "image")
            val intent = Intent(this, BookDetailActivity::class.java)
            startActivity(intent, activityOptionsCompat.toBundle())
        }
        
        initObserver()

    }


    private fun initObserver() {
        viewModel.error.observe(this, EventObserver {
            if (it == "network")
                Snackbar.make(binding.motionLayout, "네트워크 연결을 확인해주세요", Snackbar.LENGTH_LONG).show()
        })


    }

    private fun executeSearchBarMotion(isShow: Boolean) {
        if (isShow) {
            performMotion(binding.searchBar, binding.expandedSearchBar)
        } else {
            performMotion(binding.expandedSearchBar, binding.searchBar)
        }
    }

    private fun performMotion(startView: View, endView: View) {
        val transform = MaterialContainerTransform().apply {
            this.startView = startView
            this.endView = endView
            pathMotion = MaterialArcMotion()
            duration = 500
            scrimColor = Color.TRANSPARENT
        }
        TransitionManager.beginDelayedTransition(binding.searchBarHolder, transform)
        startView.visibility = View.GONE
        endView.visibility = View.VISIBLE
    }

    private fun intentToSearch() {
        executeSearchBarMotion(true)
        val intent = Intent(this, SearchActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            binding.searchBarHolder,
            "search"
        )
        requestActivity(intent, options)
    }


    override fun onBackPressed() {
        if (binding.motionLayout.currentState == binding.motionLayout.startState) //현재 motionLayout의 상태가 시작상태(hide)라면 앱 종료 expanded라면 hide
            super.onBackPressed()
        else
            binding.motionLayout.transitionToStart()
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
    }

}