package io.felipeandrade.reddit.ui.topposts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.Pager
import androidx.paging.PagingDataAdapter
import androidx.paging.liveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.felipeandrade.reddit.R
import io.felipeandrade.reddit.data.model.RedditPost
import io.felipeandrade.reddit.databinding.ViewPostBinding

class TopPostsAdapter(
    private val pager: Pager<String, RedditPost>,
    private val onItemClick: (RedditPost) -> Unit,
) : PagingDataAdapter<RedditPost, PostVH>(diffCallback) {

    override fun getItemViewType(position: Int) = R.layout.view_post

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val binding: ViewPostBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), viewType, parent, false
        )
        return PostVH(binding, onItemClick) {

        }
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        holder.bind(getItem(position))
    }
}


class PostVH(
    private val binding: ViewPostBinding,
    private val onItemClick: (RedditPost) -> Unit,
    private val onItemDismissed: (RedditPost) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: RedditPost?) {
        reloadBindings(post)

        post?.let {
            Glide.with(itemView.context)
                .load(post.imageUrl)
                .placeholder(R.drawable.ic_image)
                .into(binding.thumbnail)

            binding.closeIc.setOnClickListener {
                post.dismissed = true
                reloadBindings(post)
                onItemDismissed(post)
            }

            itemView.setOnClickListener {
                post.read = true
                reloadBindings(post)
                onItemClick(post)
            }
        }
    }

    private fun reloadBindings(post: RedditPost?) {
        binding.post = PostBindingAdapter(post)
        binding.executePendingBindings()

    }
}


private val diffCallback = object : DiffUtil.ItemCallback<RedditPost>() {
    override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
        oldItem == newItem
}
