package io.felipeandrade.reddit.ui.topposts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import io.felipeandrade.reddit.data.model.RedditPost
import io.felipeandrade.reddit.domain.usecase.LoadTopPostsUseCase


class TopPostsViewModel(
    loadTopPostsUseCase: LoadTopPostsUseCase,
) : ViewModel() {

    val pagingData = loadTopPostsUseCase()

    val openedPost = MutableLiveData<RedditPost>()
    val posts = pagingData.flow.cachedIn(viewModelScope)


    fun openPost(post: RedditPost) {
        openedPost.postValue(post)
    }

    fun dismissAll() {

    }
}
