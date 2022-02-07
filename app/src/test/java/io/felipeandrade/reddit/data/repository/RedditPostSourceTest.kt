package io.felipeandrade.reddit.data.repository

import androidx.paging.PagingSource
import io.felipeandrade.reddit.data.model.RedditPost
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

class RedditPostSourceTest {

    private val subreddit = "funny"
    private val mockApi = MockApi()


    @Test
    fun load() = runBlocking {

        val pagingSource = RedditPostSource(mockApi, subreddit)
        val expected: PagingSource.LoadResult.Page<String, RedditPost> = mockApi.expectedFirstPage()

        val result: PagingSource.LoadResult<String, RedditPost> = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )

        if (result is PagingSource.LoadResult.Error) {
            result.throwable.printStackTrace()
            fail(result.throwable.message)
        } else {
            result is PagingSource.LoadResult.Page<String, RedditPost>
            assertEquals(expected, result)
        }
    }
}