package com.example.presentation.di

import androidx.room.Room
import com.example.data.api.BookApi
import com.example.data.datasource.BookRemoteDataSource
import com.example.data.datasource.SearchWordLocalDataSource
import com.example.data.db.SearchWordDatabase
import com.example.data.repository.BookRepositoryImpl
import com.example.data.repository.SearchWordRepositoryImpl
import com.example.domain.repository.BookRepository
import com.example.domain.repository.SearchWordRepository
import com.example.domain.usecase.GetBestSellerUseCase
import com.example.domain.usecase.GetSearchWordUseCase
import com.example.domain.usecase.SaveSearchWordUseCase
import com.example.domain.usecase.SearchBookUseCase
import com.example.presentation.ui.bookDetail.BookDetailViewModel
import com.example.presentation.utils.BASE_URL
import com.example.presentation.ui.main.MainViewModel
import com.example.presentation.ui.search.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val interceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
val client = OkHttpClient.Builder().addInterceptor(interceptor).build();

val retrofit: Retrofit = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

private val bookApi: BookApi = retrofit.create(BookApi::class.java)

val networkModule = module {
    single { bookApi }
}

val repositoryModule = module {
    factory<BookRepository> { BookRepositoryImpl(get()) }
    factory<SearchWordRepository> { SearchWordRepositoryImpl(get()) }
}

val dataSourceModule = module {
    factory { BookRemoteDataSource(get()) }
    factory { SearchWordLocalDataSource(get()) }
}

val adapterModule = module {

}

val useCaseModule = module {
    factory { GetBestSellerUseCase(get()) }
    factory { SearchBookUseCase(get()) }
    factory { GetSearchWordUseCase(get()) }
    factory { SaveSearchWordUseCase(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { BookDetailViewModel() }
}

val dbModule = module {
    single { Room.databaseBuilder(androidApplication(), SearchWordDatabase::class.java, "search_word_db").build() }
}

val moduleList = listOf(
    viewModelModule, dataSourceModule, useCaseModule, repositoryModule, networkModule, adapterModule, dbModule
)

//test
val roomTestModule = module {
    single {
        // In-Memory database config
        Room.inMemoryDatabaseBuilder(androidApplication(), SearchWordDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}