package io.felipeandrade.reddit.domain.usecase

import io.felipeandrade.reddit.data.RedditRepository
import io.felipeandrade.reddit.data.model.RedditPost
import io.felipeandrade.reddit.data.model.TopPostsResponse

/**
 * Retrieves the top reddit posts from a specific subreddit.
 *
 * @property repository the repository responsible for making each calls
 */
class LoadTopPostsUseCase(private val repository: RedditRepository) {

    /**
     * Retrieves X top reddit posts from a specific subreddit.
     *
     * @param subReddit name of the subreddit.
     * @param count number of posts being retrieved.
     * @return Flow of [RedditPost]
     */
    suspend operator fun invoke(subReddit:String, count: Int): TopPostsResponse =
        repository.loadTopPosts(subReddit, count)
}