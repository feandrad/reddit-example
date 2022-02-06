package io.felipeandrade.reddit.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
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
     * Searches for the top posts in a specific subreddit (max 25 results per page).
     *
     * @param subreddit name of the subreddit being searched.
     * @return Flow of the Pager
     */
    fun loadTopPosts(
        subreddit: String,
    ) = Pager(PagingConfig(25)) {
        RedditPostSource(redditApi, subreddit)
    }.flow
}