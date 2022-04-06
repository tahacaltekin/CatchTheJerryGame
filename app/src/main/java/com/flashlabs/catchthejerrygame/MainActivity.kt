package com.flashlabs.catchthejerrygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flashlabs.catchthejerrygame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.play.setOnClickListener {
            val intent = Intent(this, DifficultyActivity::class.java)
            startActivity(intent)
        }
    }
}