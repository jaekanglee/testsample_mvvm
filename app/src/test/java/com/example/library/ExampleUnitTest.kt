package com.example.library

import android.app.Application
import com.example.data.dao.SearchWordDao
import com.example.data.db.SearchWordDatabase
import com.example.data.entitiy.SearchWord
import com.example.domain.repository.SearchWordRepository
import com.example.domain.usecase.SaveSearchWordUseCase
import com.example.presentation.di.moduleList
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class ExampleUnitTest : KoinTest, Application() {

    private val saveSearchWordUseCase: SaveSearchWordUseCase by inject()
    lateinit var dao: SearchWordDao
    private val db: SearchWordDatabase by inject()
    private val repository: SearchWordRepository by inject()
    private val r: BookRemoteDataSource by inject()
    private val d : SearchWordCacheDataSource by inject()
    @Before
    fun before() {
        startKoin {
            androidContext(this@ExampleUnitTest)
            modules(moduleList)
        }
        dao = db.searchWordDao()
    }

    @After
    fun after() {
        db.close()
        stopKoin()
    }

    @Test
    fun test() {
        val entity = SearchWord(0, "test")
        val data = d.saveSearchWord(entity).blockingGet()
        assertEquals(data, 1)
//        val data = r.getBestSeller(1000).blockingGet()
//        val map = BestSellerMapper.map(data)
//        assertThat(map, instanceOf(Result.Success::class.java))
    }
}
