package io.felipeandrade.reddit.ui.topposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.felipeandrade.reddit.data.model.RedditPost
import io.felipeandrade.reddit.databinding.FragmentPostsBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TopPostsFragment : Fragment() {

    private val sharedViewModel: TopPostsViewModel by sharedViewModel()
    private val binding by lazy { FragmentPostsBinding.inflate(layoutInflater) }
    private val postsAdapter: TopPostsAdapter by lazy { TopPostsAdapter(diffCallback, onItemClick) }

    private val onItemClick : (RedditPost) -> Unit = { sharedViewModel.openPost(it) }

    private val diffCallback = object : DiffUtil.ItemCallback<RedditPost>() {
        override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
            oldItem == newItem
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = sharedViewModel
        initRecyclerView()

        lifecycleScope.launchWhenCreated {
            sharedViewModel.posts.collectLatest { postsAdapter.submitData(it) }
        }
        return binding.root
    }


    private fun initRecyclerView() {
        binding.refreshLayout.setOnRefreshListener { postsAdapter.refresh() }

        binding.postsList.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = postsAdapter.withLoadStateHeaderAndFooter(
                header = TopPostsStateAdapter(postsAdapter),
                footer = TopPostsStateAdapter(postsAdapter),
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) { //check for scroll down
                        postsAdapter.retry()
                    }
                }
            })
        }
    }

}