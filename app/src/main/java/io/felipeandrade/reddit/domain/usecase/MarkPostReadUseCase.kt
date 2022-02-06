package io.felipeandrade.reddit.domain.usecase

import io.felipeandrade.reddit.data.repository.RedditRepository

/**
 * Marks a specific post as read.
 *
 * @property repository the repository responsible for making each calls
 */
class MarkPostReadUseCase(private val repository: RedditRepository)