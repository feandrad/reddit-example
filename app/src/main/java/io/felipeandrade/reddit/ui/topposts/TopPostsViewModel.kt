package io.felipeandrade.reddit.ui.topposts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.felipeandrade.reddit.data.model.RedditPost
import io.felipeandrade.reddit.domain.usecase.DismissPostUseCase
import io.felipeandrade.reddit.domain.usecase.LoadTopPostsUseCase
import io.felipeandrade.reddit.domain.usecase.MarkPostReadUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TopPostsViewModel(
    private val loadTopPostsUseCase: LoadTopPostsUseCase,
    private val dismissPostUseCase: DismissPostUseCase,
    private val markPostReadUseCase: MarkPostReadUseCase,
) : ViewModel() {

    val posts = loadTopPostsUseCase("funny")
    val openedPost = MutableLiveData<RedditPost>()


    fun openPost(post: RedditPost) {
        openedPost.postValue(post)
    }
}
