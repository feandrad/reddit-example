package io.felipeandrade.reddit.ui.topposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import io.felipeandrade.reddit.databinding.FragmentPostsBinding
import io.felipeandrade.reddit.databinding.FragmentReadPostBinding

class ReadPostFragment : Fragment() {

    private val sharedViewModel: TopPostsViewModel by activityViewModels()
    private val binding by lazy { FragmentReadPostBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = sharedViewModel
        return binding.root
    }

}