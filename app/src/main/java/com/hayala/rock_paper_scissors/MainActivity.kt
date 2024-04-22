package com.hayala.rock_paper_scissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.hayala.rock_paper_scissors.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var buttonPlay:Button
    private lateinit var buttonRock:Button
    private lateinit var buttonPaper:Button
    private lateinit var buttonScissors:Button
    private lateinit var buttonLizard:Button
    private lateinit var buttonSpock:Button

    private var choiceComputer = '0'
    private var choiceUser = 0
    private var step = "user"
    private var choice = listOf('1', '2', '3', '4', '5')

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        buttonPlay.setOnClickListener { onButtonPlayPressed() }
        buttonRock.setOnClickListener { onButtonRockPressed() }
        buttonPaper.setOnClickListener { onButtonPaperPressed() }
        buttonScissors.setOnClickListener { onButtonScissorsPressed() }
        buttonLizard.setOnClickListener { onButtonLizardPressed() }
        buttonSpock.setOnClickListener { onButtonSpockPressed() }
    }
    private fun onButtonPlayPressed() {
        if (step == "user") {
            Toast.makeText(applicationContext, "Сделайте выбор, прежде чем продолжить!", Toast.LENGTH_SHORT).show()
        }
        else {
            choiceComputer = choice.random()
            step = "user"
        }
    }

    private fun onButtonRockPressed() {

    }
    private fun onButtonPaperPressed() {

    }
    private fun onButtonScissorsPressed() {

    }
    private fun  onButtonLizardPressed() {

    }
    private fun onButtonSpockPressed() {

    }
}