package io.felipeandrade.reddit.data

import io.felipeandrade.reddit.data.api.RedditApi
import io.felipeandrade.reddit.data.model.RedditPost


class RedditRepository(private val redditApi: RedditApi) {

   suspend fun loadTopPosts(count: Int, before: String? = null, after: String? = null): List<RedditPost> {
        val response = redditApi.getTopPosts(count, before, after)
        return response.data.children.map { RedditPost(it.data) }
    }
}