package io.felipeandrade.reddit.ui.topposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import io.felipeandrade.reddit.R
import io.felipeandrade.reddit.databinding.FragmentReadPostBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ReadPostFragment : Fragment() {

    private val sharedViewModel: TopPostsViewModel by sharedViewModel()
    private val binding by lazy { FragmentReadPostBinding.inflate(layoutInflater) }

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
        sharedViewModel.openedPost.observe(viewLifecycleOwner) { post ->
            binding.author.text = post.author
            binding.text.text = post.title
            Glide.with(requireActivity())
                .load(post.imageUrl)
                .placeholder(R.drawable.ic_image)
                .into(binding.image)
        }
    }

}