package com.hayala.rock_paper_scissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hayala.rock_paper_scissors.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var buttonPlay: Button
    private lateinit var buttonRock: Button
    private lateinit var buttonPaper: Button
    private lateinit var buttonScissors: Button
    private lateinit var buttonLizard: Button
    private lateinit var buttonSpock: Button
    private lateinit var buttonStartOver: Button
    private lateinit var computerText: TextView
    private lateinit var userText: TextView
    private lateinit var textResult_Winner: TextView
    private lateinit var result: TextView

    private var nameChoiceComputer = ""
    private var choiceComputer = 0
    private var nameChoiceUser = ""
    private var choiceUser = 0
    private var step = "user"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonPlay = binding.btnPlay
        buttonRock = binding.btnRock
        buttonPaper = binding.btnPaper
        buttonScissors = binding.btnScissors
        buttonLizard = binding.btnLizard
        buttonSpock = binding.btnSpock
        computerText = binding.textChoiceComputer
        userText = binding.textChoiceUser
        buttonStartOver = binding.btnStartOver
        textResult_Winner = binding.textWinner
        result = binding.result

        buttonPlay.setOnClickListener { onButtonPlayPressed() }
        buttonRock.setOnClickListener { onButtonRockPressed() }
        buttonPaper.setOnClickListener { onButtonPaperPressed() }
        buttonScissors.setOnClickListener { onButtonScissorsPressed() }
        buttonLizard.setOnClickListener { onButtonLizardPressed() }
        buttonSpock.setOnClickListener { onButtonSpockPressed() }
        buttonStartOver.setOnClickListener { onButtonStartOverPressed() }

        if (savedInstanceState == null) {

        }
        else {

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun renderState() = with(binding) {

    }

    companion object {
        @JvmStatic private val FIRST_OPERAND = "txtFirstOperand"
    }

    private fun onButtonPlayPressed() {
        if (step == "user") {
            Toast.makeText(
                applicationContext,
                "Сделайте выбор, прежде чем продолжить!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            choiceComputer = Random.nextInt(1, 6)
            changesAfterUserSelection()
            getResultOfGame()
            step = "user"
        }
    }

    private fun changesAfterUserSelection() {
        buttonScissors.visibility = View.GONE
        buttonPaper.visibility = View.GONE
        buttonSpock.visibility = View.GONE
        buttonLizard.visibility = View.GONE
        buttonRock.visibility = View.GONE

        nameChoiceComputer = valueOfNumber(choiceComputer, nameChoiceComputer)
        nameChoiceUser = valueOfNumber(choiceUser, nameChoiceUser)
        computerText.visibility = View.VISIBLE
        userText.visibility = View.VISIBLE
        result.visibility = View.VISIBLE

        computerText.setText("Компьютера - \n$nameChoiceComputer")
        userText.setText("Пользователя - \n$nameChoiceUser")

        buttonPlay.visibility = View.GONE
        buttonStartOver.visibility = View.VISIBLE
    }

    private fun valueOfNumber(choice: Int, nameChoice: String):String {
        var name = nameChoice
        when (choice) {
            1 -> name = "камень"
            2 -> name = "бумага"
            3 -> name = "ножницы"
            4 -> name = "ящерица"
            5 -> name = "спок"
        }
        return name
    }

    private fun getResultOfGame() {
        textResult_Winner.visibility = View.VISIBLE

        if (choiceUser == choiceComputer)
            textResult_Winner.text = "Победила ничья, попробуйте переиграть"
        else if ((choiceUser == 1 && (choiceComputer == 3 || choiceComputer == 4)) ||
            (choiceUser == 2 && (choiceComputer == 1 || choiceComputer == 5)) ||
            (choiceUser == 3 && (choiceComputer == 2 || choiceComputer == 4)) ||
            (choiceUser == 4 && (choiceComputer == 5 || choiceComputer == 2)) ||
            (choiceUser == 5 && (choiceComputer == 3 || choiceComputer == 1))
        )
            textResult_Winner.text = "Победил пользователь"
        else
            textResult_Winner.text = "Победил компьютер"
    }

    private fun onButtonStartOverPressed() {
        buttonScissors.visibility = View.VISIBLE
        buttonPaper.visibility = View.VISIBLE
        buttonSpock.visibility = View.VISIBLE
        buttonLizard.visibility = View.VISIBLE
        buttonRock.visibility = View.VISIBLE

        computerText.visibility = View.GONE
        userText.visibility = View.GONE
        textResult_Winner.visibility = View.GONE
        result.visibility = View.GONE

        buttonPlay.visibility = View.VISIBLE
        buttonStartOver.visibility = View.GONE
    }

    private fun onButtonRockPressed() {
        step = "computer"
        nameChoiceComputer = "камень"
        choiceUser = 1
    }

    private fun onButtonPaperPressed() {
        step = "computer"
        nameChoiceComputer = "бумага"
        choiceUser = 2
    }

    private fun onButtonScissorsPressed() {
        step = "computer"
        nameChoiceComputer = "ножницы"
        choiceUser = 3
    }

    private fun onButtonLizardPressed() {
        step = "computer"

        choiceUser = 4
    }

    private fun onButtonSpockPressed() {
        step = "computer"
        choiceUser = 5
    }
}