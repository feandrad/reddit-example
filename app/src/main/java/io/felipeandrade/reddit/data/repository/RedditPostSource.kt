package io.felipeandrade.reddit.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.felipeandrade.reddit.data.api.RedditApi
import io.felipeandrade.reddit.data.model.RedditPost
import java.lang.Exception

class RedditPostSource(
    private val api: RedditApi,
    private val subreddit: String,
    private val before: String? = null,
    private val after: String? = null,
) : PagingSource<String, RedditPost>() {

    override fun getRefreshKey(state: PagingState<String, RedditPost>): String? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditPost> = try {
        val response = api.getTopPosts(subreddit, before, after)
        val postList = response.data.children.map { RedditPost(it.data) }

        LoadResult.Page(
            data = postList,
            prevKey = response.data.before,
            nextKey = response.data.after,
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

}