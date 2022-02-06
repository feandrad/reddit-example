package io.felipeandrade.reddit.domain.di

import io.felipeandrade.reddit.BuildConfig
import io.felipeandrade.reddit.data.api.RedditApi
import io.felipeandrade.reddit.data.repository.RedditRepository
import io.felipeandrade.reddit.domain.usecase.LoadTopPostsUseCase
import io.felipeandrade.reddit.ui.topposts.TopPostsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Initialize and prepare dependency injection for the main elements of the application.
 */
val domainModule = module(override = true) {
    single { LoadTopPostsUseCase(get()) }
}

/**
 * Initialize and provides dependency injection all ui components.
 */
val uiModule = module(override = true) {
    viewModel { TopPostsViewModel(get()) }
}

/**
 * Initialize and provides dependency injection all network components.
 */
val dataModule = module(override = true) {
    single { createOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideRedditApi(get()) }
    single { RedditRepository(get()) }
}

/**
 * Provides the Api for Reddit calls.
 *
 * @param retrofit The retrofit instance.
 * @return [RedditApi]
 */
fun provideRedditApi(retrofit: Retrofit): RedditApi = retrofit.create(RedditApi::class.java)

/**
 * Provide OkHttpClient with Logging interceptor and configure connection timeouts.
 *
 * @return [OkHttpClient]
 */
fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

/**
 * Initializes the retrofit instance for Network calls.
 *
 * @param okHttpClient
 * @return [Retrofit]
 */
fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}