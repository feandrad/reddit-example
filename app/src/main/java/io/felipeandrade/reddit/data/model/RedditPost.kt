package io.felipeandrade.reddit.data.model

data class RedditPost(
    val id: String,
    val title: String,
    val author: String,
    val imageUrl: String? = null,
    val comments: Int = 0,
    val created: Long,
    val read: Boolean = false,
) {
    constructor(data: PostDataRaw, read: Boolean = false) : this(
        id = data.name,
        title = data.title,
        author = data.author,
        imageUrl = data.thumbnail,
        comments = data.comments,
        created = data.created,
        read = read,
    )
}