package io.felipeandrade.reddit.data

import io.felipeandrade.reddit.data.api.RedditApi
import io.felipeandrade.reddit.data.model.RedditPost
import io.felipeandrade.reddit.data.model.TopPostsResponse

/**
 * Handles Reddit calls and persistence.
 *
 * @property redditApi retrofit Api for reddit calls.
 */
class RedditRepository(private val redditApi: RedditApi) {

    /**
     * Searches for the top posts in a specific subreddit.
     *
     * @param subreddit name of the subreddit being searched.
     * @param count Number of entries (max 25 per page).
     * @param before code for the next page (if more then 25 results).
     * @param after code for previous page if it exists.
     * @return List of [RedditPost]
     */
    suspend fun loadTopPosts(
        subreddit: String,
        count: Int,
        before: String? = null,
        after: String? = null
    ): TopPostsResponse {
        val response = redditApi.getTopPosts(subreddit, count, before, after)
        val postList =  response.data.children.map { RedditPost(it.data) }
        return TopPostsResponse(
            subreddit = subreddit,
            posts = postList,
            after = response.data.after,
            before = response.data.before,
        )
    }
}