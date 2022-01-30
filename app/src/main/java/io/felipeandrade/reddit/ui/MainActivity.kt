package io.felipeandrade.reddit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.felipeandrade.reddit.R

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}