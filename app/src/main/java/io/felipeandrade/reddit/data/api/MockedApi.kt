package io.felipeandrade.reddit.data.api

import android.content.Context
import com.google.gson.Gson
import io.felipeandrade.reddit.R
import io.felipeandrade.reddit.data.model.RedditResponse

class MockedApi(private val applicationContext: Context) : RedditApi {

    override suspend fun getTopPosts(count: Int, before: String?, after: String?): RedditResponse {

        // Since https://apigee.com/console/reddit is returning "Error 410: The API Console Service is no longer available."
        // I am using the json file. Send me the correct API url and information so I can convert it to use live data.

        val jsonText = applicationContext.resources.openRawResource(R.raw.response)
            .bufferedReader().use { it.readText() }

        return Gson().fromJson(jsonText, RedditResponse::class.java)
    }
}