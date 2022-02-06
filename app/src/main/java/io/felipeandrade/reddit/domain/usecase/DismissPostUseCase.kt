package io.felipeandrade.reddit.domain.usecase

import io.felipeandrade.reddit.data.repository.RedditRepository

/**
 * Marks a specific post as hidden.
 *
 * @property repository the repository responsible for making each calls
 */
class DismissPostUseCase(private val repository: RedditRepository)