package io.felipeandrade.reddit.data.model

data class RedditPost(
    val title: String,
    val author: String,
    val imageUrl: String? = null,
    val comments: Int = 0,
    val created: Long,
    val read: Boolean = false,
) {
    constructor(data: PostDataRaw, read: Boolean = false) : this(
        title = data.title,
        author = data.author,
        imageUrl = data.imageUrl,
        comments = data.comments,
        created = data.created,
        read = read,
    )
}