package io.felipeandrade.reddit.ui.topposts

import android.text.format.DateUtils
import android.view.View
import io.felipeandrade.reddit.data.model.RedditPost

class PostBindingAdapter(val post: RedditPost?) {
    val dismissed = post?.dismissed == true
    val read = if (post?.read == false) View.VISIBLE else View.GONE
    val comments = post?.let { "${post.comments} Comments" } ?: ""
    val author = post?.author
    val title = post?.title
    val since = post?.let {
        DateUtils.getRelativeTimeSpanString(post.created * 1000L)
    } ?: ""
}