package io.felipeandrade.reddit.data.model

import com.google.gson.annotations.SerializedName

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
    @SerializedName("thumbnail")
    val imageUrl: String? = null,
    @SerializedName("num_comments")
    val comments: Int = 0,
    val created: Long,
)

