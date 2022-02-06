package io.felipeandrade.reddit.ui.topposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.felipeandrade.reddit.databinding.FragmentPostsBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TopPostsFragment : Fragment() {

    private val sharedViewModel: TopPostsViewModel by sharedViewModel()
    private val binding by lazy { FragmentPostsBinding.inflate(layoutInflater) }
    private val postsAdapter by lazy { TopPostsAdapter { sharedViewModel.openPost(it) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = sharedViewModel
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.posts.observe(viewLifecycleOwner) { list ->
            postsAdapter.resetItems(list)
            binding.refreshLayout.isRefreshing = false
        }
    }


    private fun initRecyclerView() {
        binding.refreshLayout.setOnRefreshListener { sharedViewModel.loadTop50Posts() }

        binding.postsList.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = postsAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) { //check for scroll down
                        sharedViewModel.reloadPosts()
                    }
                }
            })
        }
    }

}