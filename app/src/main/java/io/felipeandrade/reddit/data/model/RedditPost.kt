package io.felipeandrade.reddit.data.model

data class RedditPost(
    val name: String,
    val title: String,
    val author: String,
    val created: Long,
    val imageUrl: String? = null,
    val comments: Int = 0,
    var read: Boolean = false,
    var dismissed: Boolean = false,
) {
    constructor(data: PostDataRaw) : this(
        name = data.name,
        title = data.title,
        author = data.author,
        created = data.created,
        imageUrl = data.thumbnail,
        comments = data.comments,
    )
}