package io.felipeandrade.reddit.ui.topposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import io.felipeandrade.reddit.databinding.FragmentPostsBinding

class TopPostsFragment: Fragment() {

    private val sharedViewModel: TopPostsViewModel by activityViewModels()
    private val binding by lazy { FragmentPostsBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = sharedViewModel
        return binding.root
    }
}