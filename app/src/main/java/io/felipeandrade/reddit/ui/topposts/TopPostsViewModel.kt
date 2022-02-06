package io.felipeandrade.reddit.ui.topposts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.felipeandrade.reddit.data.model.RedditPost
import io.felipeandrade.reddit.domain.usecase.DismissPostUseCase
import io.felipeandrade.reddit.domain.usecase.LoadTopPostsUseCase
import io.felipeandrade.reddit.domain.usecase.MarkPostReadUseCase
import kotlinx.coroutines.launch

class TopPostsViewModel(
    private val loadTopPostsUseCase: LoadTopPostsUseCase,
    private val dismissPostUseCase: DismissPostUseCase,
    private val markPostReadUseCase: MarkPostReadUseCase,
) : ViewModel() {

    val posts = MutableLiveData<List<RedditPost>>()
    val openedPost = MutableLiveData<RedditPost>()

    init {
        loadTop50Posts()
    }

    fun loadTop50Posts() {
        viewModelScope.launch {
            val res = loadTopPostsUseCase("funny", 50)
            posts.postValue(res.posts)
        }
    }

    fun openPost(post: RedditPost) {
        openedPost.postValue(post)
    }

    fun reloadPosts() {

    }
}
