package io.felipeandrade.reddit.ui.topposts

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.felipeandrade.reddit.R
import io.felipeandrade.reddit.data.model.RedditPost
import io.felipeandrade.reddit.databinding.ViewPostBinding

class TopPostsAdapter(
    diffCallback: DiffUtil.ItemCallback<RedditPost>,
    private val onItemClicked: (RedditPost) -> Unit,
) : PagingDataAdapter<RedditPost, TopPostsAdapter.PostVH>(diffCallback) {


    override fun getItemViewType(position: Int) = R.layout.view_post

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val binding: ViewPostBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), viewType, parent, false
        )
        return PostVH(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        holder.bind(getItem(position))
    }


    class PostVH(
        private val binding: ViewPostBinding,
        private val onItemClicked: (RedditPost) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: RedditPost?) {
            binding.post = PostBindingAdapter(post)
            binding.executePendingBindings()

            post?.let {
                Glide.with(itemView.context)
                    .load(post.imageUrl)
                    .placeholder(R.drawable.ic_image)
                    .into(binding.thumbnail)

                itemView.setOnClickListener { onItemClicked(post) }
            }
        }
    }
}


class PostBindingAdapter(val post: RedditPost?) {
    val comments = post?.let { "${post.comments} Comments" } ?: ""
    val author = post?.author
    val title = post?.title
    val since = post?.let {
        DateUtils.getRelativeTimeSpanString(post.created * 1000L)
    } ?: ""
}
