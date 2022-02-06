package io.felipeandrade.reddit.data.model

import com.google.gson.annotations.SerializedName

data class RedditResponseRaw(
    val kind: String,
    val data: ListRedditDataRaw,
)

data class ListRedditDataRaw(
    val modhash: String,
    val children: List<T3DataResponseRaw>,
    val after: String? = null,
    val before: String? = null,
)

data class T3DataResponseRaw(
    val kind: String,
    val data: PostDataRaw,
)

data class PostDataRaw(
    val name: String,
    val title: String,
    val author: String,
    val thumbnail: String? = null,
    @SerializedName("num_comments")
    val comments: Int = 0,
    val created: Long,
)

