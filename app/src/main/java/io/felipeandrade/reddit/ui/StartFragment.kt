package io.felipeandrade.reddit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.felipeandrade.reddit.databinding.FragmentStartBinding
import io.felipeandrade.reddit.ui.topposts.TopPostsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StartFragment : Fragment() {

    private val sharedViewModel: TopPostsViewModel by sharedViewModel()
    private val binding by lazy { FragmentStartBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}