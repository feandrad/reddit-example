package io.felipeandrade.reddit.data.model

data class RedditPost(
    val id: String,
    val title: String,
    val author: String,
    val imageUrl: String? = null,
    val comments: Int = 0,
    val created: Long,
    var read: Boolean = false,
    var dismissed: Boolean = false,
) {
    constructor(data: PostDataRaw) : this(
        id = data.name,
        title = data.title,
        author = data.author,
        imageUrl = data.thumbnail,
        comments = data.comments,
        created = data.created,
    )
}