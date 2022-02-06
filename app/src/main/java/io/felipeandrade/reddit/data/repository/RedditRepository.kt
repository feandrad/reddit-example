package io.felipeandrade.reddit.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import io.felipeandrade.reddit.data.api.RedditApi

/**
 * Handles Reddit calls and persistence.
 *
 * @property redditApi retrofit Api for reddit calls.
 */
class RedditRepository(private val redditApi: RedditApi) {

    /**
     * Searches for the top posts in a specific subreddit (max 25 results per page).
     *
     * @param subreddit name of the subreddit being searched.
     * @return the Pager
     */
    fun loadTopPosts(
        subreddit: String,
        count: Int,
    ) = Pager(PagingConfig(count)) {
        RedditPostSource(redditApi, subreddit)
    }
}