package io.felipeandrade.reddit.data.api

import io.felipeandrade.reddit.data.model.RedditResponseRaw
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditApi {

    @GET("r/{subreddit}/top.json")
    suspend fun getTopPosts(
        @Path("subreddit") subreddit: String,
        @Query("before")before: String? = null,
        @Query("after")after: String? = null,
    ): RedditResponseRaw
}