package com.guzzardi.profileslist.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.guzzardi.profileslist.databinding.ActivityMainBinding

class ProfilesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)
    }
}
