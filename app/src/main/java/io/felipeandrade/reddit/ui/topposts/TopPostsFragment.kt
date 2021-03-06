package io.felipeandrade.reddit.ui.topposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import io.felipeandrade.reddit.databinding.FragmentPostsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TopPostsFragment : Fragment() {

    private val sharedViewModel: TopPostsViewModel by sharedViewModel()
    private val binding by lazy { FragmentPostsBinding.inflate(layoutInflater) }
    private val postsAdapter: TopPostsAdapter by lazy {
        TopPostsAdapter(sharedViewModel.pagingData) { sharedViewModel.openPost(it) }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = sharedViewModel
        initRecyclerView()
        initDismissAll()

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            sharedViewModel.posts.collectLatest { postsAdapter.submitData(it) }
        }
        return binding.root
    }


    private fun initRecyclerView() {
        binding.refreshLayout.setOnRefreshListener {
            viewLifecycleOwner.lifecycleScope.launch {
                sharedViewModel.posts.collectLatest { postsAdapter.submitData(it) }
            }
        }

        binding.postsList.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = postsAdapter.withLoadStateHeaderAndFooter(
                header = TopPostsStateAdapter(postsAdapter),
                footer = TopPostsStateAdapter(postsAdapter),
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            postsAdapter.onPagesUpdatedFlow.collectLatest {
                binding.refreshLayout.isRefreshing = false
            }
        }
    }

    private fun initDismissAll() {
        binding.dismissAll.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                postsAdapter.submitData(PagingData.empty())
            }
        }
    }

}