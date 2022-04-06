package com.flashlabs.catchthejerrygame

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.flashlabs.catchthejerrygame.databinding.ActivityGameBinding
import java.util.*
import kotlin.collections.ArrayList

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {  }
    var highScore = 0
    var highScore1 = 0
    var highScore2 = 0
    var difficult = 0
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        imageArray.add(binding.mouse1)
        imageArray.add(binding.mouse2)
        imageArray.add(binding.mouse3)
        imageArray.add(binding.mouse4)
        imageArray.add(binding.mouse5)
        imageArray.add(binding.mouse6)
        imageArray.add(binding.mouse7)
        imageArray.add(binding.mouse8)
        imageArray.add(binding.mouse9)
        imageArray.add(binding.mouse10)
        imageArray.add(binding.mouse11)
        imageArray.add(binding.mouse12)

        hideImages()

        sharedPreferences = this@GameActivity.getSharedPreferences("com.flashlabs.catchthejerrygame", MODE_PRIVATE)
        highScore = sharedPreferences.getInt("Easy", 0)
        highScore1 = sharedPreferences.getInt("Medium", 0)
        highScore2 = sharedPreferences.getInt("Hard", 0)

        difficult = intent.getIntExtra("Difficult", 3)
        when (difficult) {
            1 -> {
                if (highScore == -1) {
                    binding.highScoreText.text = "H.Score: 0"
                } else {
                    binding.highScoreText.text = "H.Score: $highScore"
                }
            }
            2 -> {
                if (highScore1 == -1) {
                    binding.highScoreText.text = "H.Score: 0"
                } else {
                    binding.highScoreText.text = "H.Score: $highScore1"
                }
            }
            else -> {
                if (highScore2 == -1) {
                    binding.highScoreText.text = "H.Score: 0"
                } else {
                    binding.highScoreText.text = "H.Score: $highScore2"
                }
            }
        }

        object : CountDownTimer(16000, 1000) {
            override fun onTick(p0: Long) {
                binding.timeText.text = "Time: " + p0 / 1000
            }

            override fun onFinish() {
                binding.timeText.text = "Time Over"

                handler.removeCallbacks(runnable)

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                when (difficult) {
                    1 -> {
                        if (score > highScore) {
                            highScore = score
                            binding.highScoreText.text = "H.Score: $highScore"
                            sharedPreferences.edit().putInt("Easy", highScore).apply()
                        }
                    }
                    2 -> {
                        if (score > highScore1) {
                            highScore1 = score
                            binding.highScoreText.text = "H.Score: $highScore1"
                            sharedPreferences.edit().putInt("Medium", highScore1).apply()
                        }
                    }
                    else -> {
                        if (score > highScore2) {
                            highScore2 = score
                            binding.highScoreText.text = "H.Score: $highScore2"
                            sharedPreferences.edit().putInt("Hard", highScore2).apply()
                        }
                    }
                }

                if (!isFinishing) {
                    val alert = AlertDialog.Builder(this@GameActivity)
                    alert.setTitle("Game Over")
                    alert.setMessage("Your Score: $score \n Do you want to Play Again?")
                    alert.setPositiveButton("Yes") { dialog, which ->

                        val intent = intent
                        finish()
                        startActivity(intent)

                    }

                    alert.setNegativeButton("No") {dialog, which ->
                        val intent = Intent(this@GameActivity, MainActivity::class.java)
                        finish()
                        startActivity(intent)
                    }
                    alert.show()
                }
            }

        }.start()
    }

    fun hideImages() {

        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val randonIndex = random.nextInt(12)
                imageArray[randonIndex].visibility = View.VISIBLE

                when (difficult) {
                    1 -> {
                        handler.postDelayed(runnable, 650)
                    }
                    2 -> {
                        handler.postDelayed(runnable, 500)
                    }
                    else -> {
                        handler.postDelayed(runnable, 350)
                    }
                }

            }
        }

        handler.post(runnable)
    }

    fun increaseScore(view: View) {
        score++
        binding.scoreText.text = "Score: $score"

    }
}