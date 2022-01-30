package io.felipeandrade.reddit.domain.di

import io.felipeandrade.reddit.BuildConfig
import io.felipeandrade.reddit.data.RedditRepository
import io.felipeandrade.reddit.data.api.MockedApi
import io.felipeandrade.reddit.data.api.RedditApi
import io.felipeandrade.reddit.domain.usecase.LoadTop50PostsUseCase
import io.felipeandrade.reddit.ui.topposts.TopPostsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val coreModule = module(override = true) {
    single { createOkHttpClient() }
    single { provideCharacterApi(get()) }
    single { provideRetrofit(get()) }

    viewModel { TopPostsViewModel(get()) }
    single { LoadTop50PostsUseCase(get()) }
    single { RedditRepository(get()) }

    // Since https://apigee.com/console/reddit is returning "Error 410: The API Console Service is no longer available."
    // I am using the json file. Send me the correct API url and information so I can convert it to use live data.
    single<RedditApi> { MockedApi(androidApplication()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun provideCharacterApi(retrofit: Retrofit): RedditApi =
    retrofit.create(RedditApi::class.java)