package com.example.viddownt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viddownt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Add video download functionality here
    }
}