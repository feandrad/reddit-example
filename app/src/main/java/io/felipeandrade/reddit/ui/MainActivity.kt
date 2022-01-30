package io.felipeandrade.reddit.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.felipeandrade.reddit.R

class MainActivity: AppCompatActivity() {

    private val sharedData: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        observeLiveData()
    }

    private fun observeLiveData() {

    }
}