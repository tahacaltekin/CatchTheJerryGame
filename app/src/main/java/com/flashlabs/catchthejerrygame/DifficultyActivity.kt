package com.flashlabs.catchthejerrygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flashlabs.catchthejerrygame.databinding.ActivityDifficultyBinding

class DifficultyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDifficultyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDifficultyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.easy.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("Difficult", 1)
            finish()
            startActivity(intent)
        }

        binding.medium.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("Difficult", 2)
            finish()
            startActivity(intent)
        }

        binding.hard.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("Difficult", 3)
            finish()
            startActivity(intent)
        }
    }
}