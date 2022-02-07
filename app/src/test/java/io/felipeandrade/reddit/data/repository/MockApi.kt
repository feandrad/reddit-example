package io.felipeandrade.reddit.data.repository

import androidx.paging.PagingSource
import com.google.gson.Gson
import io.felipeandrade.reddit.data.api.RedditApi
import io.felipeandrade.reddit.data.model.RedditPost
import io.felipeandrade.reddit.data.model.RedditResponseRaw
import java.io.InputStreamReader

class MockApi : RedditApi {

    override suspend fun getTopPosts(
        subreddit: String,
        after: String?,
        before: String?
    ): RedditResponseRaw = if (after == null)
        jsonResponse("first_response.json")
    else jsonResponse("second_response.json")


    private inline fun <reified T> jsonResponse(fileName: String): T {

        val resourceAsStream = javaClass.classLoader.getResourceAsStream("$fileName")

        InputStreamReader(resourceAsStream).use { streamReader ->
            val json: String = streamReader.readText()
            return Gson().fromJson(json, T::class.java)
        }
    }

    private fun firstResponsePosts(): List<RedditPost> = listOf(
        RedditPost(
            name = "t3_slsrcy",
            title = "The Nature Boy Woooo",
            author = "RajuNeupane",
            created = 1644139573L,
            imageUrl = "https://b.thumbs.redditmedia.com/_yDtXgbChPGWMLq2Bhal4SQkLEOKC3IXyGPo5SEAwyo.jpg",
            comments = 88, read = false, dismissed = false
        ),
        RedditPost(
            name = "t3_slvw6e",
            title = "How to use this toy",
            author = "Tr393w",
            created = 1644151694L,
            imageUrl = "https://b.thumbs.redditmedia.com/uzhyqHtoMUGFeJ2j7-7px6djMLozMYIRoFjLTmZP2IQ.jpg",
            comments = 96, read = false, dismissed = false
        ),
        RedditPost(
            name = "t3_slmoor",
            title = "I found three COVIDs at the beach today",
            author = "BigVariation3",
            created = 1644111634L,
            imageUrl = "https://a.thumbs.redditmedia.com/MLUyxUZqwXsyI8oSccx9FRAgAKmjE9M4Kxka_dvfp78.jpg",
            comments = 49, read = false, dismissed = false
        )


    )

    fun expectedFirstPage(): PagingSource.LoadResult.Page<String, RedditPost> =
        PagingSource.LoadResult.Page(
            data = firstResponsePosts(),
            prevKey = null,
            nextKey = "t3_slmoor",
            itemsBefore = -2147483648,
            itemsAfter =  -2147483648,
        )

}