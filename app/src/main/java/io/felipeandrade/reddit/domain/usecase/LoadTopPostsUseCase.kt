package io.felipeandrade.reddit.domain.usecase

import androidx.paging.PagingData
import io.felipeandrade.reddit.data.repository.RedditRepository
import io.felipeandrade.reddit.data.model.RedditPost
import io.felipeandrade.reddit.data.model.TopPostsResponse
import kotlinx.coroutines.flow.Flow

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
     * @return Flow of [PagingData] with the [RedditPost]
     */
    operator fun invoke(subReddit:String): Flow<PagingData<RedditPost>> =
        repository.loadTopPosts(subReddit)
}