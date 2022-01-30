package io.felipeandrade.reddit.domain.usecase

import io.felipeandrade.reddit.data.RedditRepository
import io.felipeandrade.reddit.data.model.RedditPost

class LoadTop50PostsUseCase (private val repository: RedditRepository) {
    suspend operator fun invoke(): List<RedditPost> = repository.loadTopPosts(50)
}