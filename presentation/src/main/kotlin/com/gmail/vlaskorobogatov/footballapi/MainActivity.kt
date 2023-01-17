package com.gmail.vlaskorobogatov.footballapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gmail.vlaskorobogatov.football_app.R
import com.gmail.vlaskorobogatov.footballapi.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}