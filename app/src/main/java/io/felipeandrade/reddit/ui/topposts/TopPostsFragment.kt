package io.felipeandrade.reddit.ui.topposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.felipeandrade.reddit.databinding.FragmentPostsBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TopPostsFragment : Fragment() {

    private val sharedViewModel: TopPostsViewModel by sharedViewModel()
    private val binding by lazy { FragmentPostsBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = sharedViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.posts.observe(this) { list ->
            binding.postsList.apply {
                layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                adapter = TopPostsAdapter(list) { sharedViewModel.readPost(it) }
            }
        }
    }

}