package io.felipeandrade.reddit.data.api

import io.felipeandrade.reddit.data.model.RedditResponse
import retrofit2.http.GET

interface RedditApi {

    @GET("www.reddit.com/top")
    suspend fun getTopPosts(count: Int, before: String? = null, after: String? = null): RedditResponse
}