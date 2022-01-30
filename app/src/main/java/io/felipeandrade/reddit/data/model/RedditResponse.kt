package io.felipeandrade.reddit.data.model

data class RedditResponse(
    val kind: String,
    val data: ListRedditData,
)

data class ListRedditData(
    val modhash: String,
    val children: List<T3DataResponse>,
    val after: String? = null,
    val before: String? = null,
)

data class T3DataResponse(
    val kind: String,
    val data: PostData,
)

data class PostData(
    val title: String,
    val author: String,
    val thumbnail: String? = null,
    val num_comments: Int = 0,
)

