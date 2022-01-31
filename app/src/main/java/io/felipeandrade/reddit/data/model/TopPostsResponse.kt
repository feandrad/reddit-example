package io.felipeandrade.reddit.data.model

data class TopPostsResponse(
    val subreddit: String,
    val posts: List<RedditPost>,
    val after : String? = null,
    val before : String? = null,
)