package com.example.guessthenumber

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guessthenumber.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var number = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        val view = this.binding.root
        setContentView(view)
        setRandomNumber()
    }

    private fun setRandomNumber() {
        this.number = Random.nextInt(100) + 1
    }

    fun guess(view: View) {
        try {
            val guessNumber = this.binding.input.text.toString().toInt()
            this.score++
            analyzeInputNumber(guessNumber)
        } catch (e: NumberFormatException) {
            showToast("Give number")
        }
    }

    private fun analyzeInputNumber(guessNumber: Int) {
        if (guessNumber > this.number) {
            UpdateScoreText()
            showToast("Too high")
        } else if (guessNumber < this.number) {
            UpdateScoreText()
            showToast("Too low")
        } else {
            UpdateScoreText(Typeface.DEFAULT_BOLD)
            showToast("You guessed!")
            setGuessButtonListener(null)
        }
    }

    private fun showToast(info: String) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show()
    }

    private fun setGuessButtonListener(listener: View.OnClickListener?) {
        this.binding.guessButton.setOnClickListener(listener)
    }

    private fun UpdateScoreText(type: Typeface = Typeface.DEFAULT) {
        this.binding.textScore.text = "Score : " + score.toString()
        this.binding.textScore.typeface = type
    }

    fun restart(view: View) {
        this.score = 0
        this.binding.input.setText("")
        UpdateScoreText()
        setGuessButtonListener(this::guess)
        setRandomNumber()
    }
}
