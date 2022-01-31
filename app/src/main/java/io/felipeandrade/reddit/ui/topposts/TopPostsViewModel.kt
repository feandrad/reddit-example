package io.felipeandrade.reddit.ui.topposts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.felipeandrade.reddit.data.model.RedditPost
import io.felipeandrade.reddit.domain.usecase.LoadTop50PostsUseCase
import kotlinx.coroutines.launch

class TopPostsViewModel(private val loadTop50PostsUseCase: LoadTop50PostsUseCase): ViewModel() {

    val posts = MutableLiveData<List<RedditPost>>()
    val readingPost = MutableLiveData<RedditPost>()

    init{
        loadTop50Posts()
    }

    fun loadTop50Posts() {
        viewModelScope.launch {
            val res = loadTop50PostsUseCase()
            posts.postValue(res)
        }
    }

    fun readPost(post: RedditPost) {
        readingPost.postValue(post)
    }
}
