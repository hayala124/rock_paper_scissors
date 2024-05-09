package com.hayala.rock_paper_scissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hayala.rock_paper_scissors.databinding.ActivityMainBinding

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
    private var choiceComputer = " "
    private var choiceUser = " "
    private var text_Result = " "
    private val choice = arrayOf("камень", "бумага", "ножницы", "ящерица", "спок")
    private var step = "user"

    private var btnRock_Visible = true
    private var btnPaper_Visible = true
    private var btnScissors_Visible = true
    private var btnLizard_Visible = true
    private var btnSpock_Visible = true
    private var btnStartOver_Visible = false
    private var result_Visible = false
    private var text_result_Winner_Visible = false
    private var userText_Visible = false
    private var computerText_Visible = false


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
            choiceComputer = " "
            choiceUser = " "
            step = "user"
            btnRock_Visible = true
            btnPaper_Visible = true
            btnScissors_Visible = true
            btnLizard_Visible = true
            btnSpock_Visible = true
            btnStartOver_Visible = false
            result_Visible = false
            text_Result = " "
            text_result_Winner_Visible = false
            userText_Visible = false
            computerText_Visible = false
        } else {
            choiceComputer = savedInstanceState.getString(CHOICE_COMPUTER).toString()
            choiceUser = savedInstanceState.getString(CHOICE_USER).toString()
            step = savedInstanceState.getString(STEP).toString()
            text_Result = savedInstanceState.getString(RESULT).toString()
            btnRock_Visible = savedInstanceState.getBoolean(BUTTON_ROCK_VISIBLE)
            btnPaper_Visible = savedInstanceState.getBoolean(BUTTON_PAPER_VISIBLE)
            btnScissors_Visible = savedInstanceState.getBoolean(BUTTON_SCISSORS_VISIBLE)
            btnLizard_Visible = savedInstanceState.getBoolean(BUTTON_LIZARD_VISIBLE)
            btnSpock_Visible = savedInstanceState.getBoolean(BUTTON_SPOCK_VISIBLE)
            btnStartOver_Visible = savedInstanceState.getBoolean(BUTTON_SPOCK_VISIBLE)
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(CHOICE_USER, choiceUser)
        outState.putString(CHOICE_COMPUTER, choiceComputer)
        outState.putString(RESULT, text_Result)
        outState.putBoolean(BUTTON_ROCK_VISIBLE, btnRock_Visible)
        outState.putBoolean(BUTTON_PAPER_VISIBLE, btnPaper_Visible)
        outState.putBoolean(BUTTON_SCISSORS_VISIBLE, btnScissors_Visible)
        outState.putBoolean(BUTTON_LIZARD_VISIBLE, btnLizard_Visible)
        outState.putBoolean(BUTTON_SPOCK_VISIBLE, btnSpock_Visible)
        outState.putBoolean(BUTTON_START_OVER_VISIBLE, btnStartOver_Visible)
    }

    private fun renderState() = with(binding) {
        textChoiceUser.setText(choiceUser)
        textChoiceComputer.setText(choiceComputer)
        result.setText(text_Result)

        btnRock.visibility = if (btnRock_Visible) View.VISIBLE else View.GONE
        btnPaper.visibility = if (btnPaper_Visible) View.VISIBLE else View.GONE
        btnScissors.visibility = if (btnScissors_Visible) View.VISIBLE else View.GONE
        btnLizard.visibility = if (btnLizard_Visible) View.VISIBLE else View.GONE
        btnSpock.visibility = if (btnSpock_Visible) View.VISIBLE else View.GONE

    }

    companion object {
        @JvmStatic private val CHOICE_COMPUTER = "choice_computer"
        @JvmStatic private val CHOICE_USER = "choice_user"
        @JvmStatic private val RESULT = "winner"
        @JvmStatic private val STEP = "step"
        @JvmStatic private val BUTTON_ROCK_VISIBLE = "rock_visible"
        @JvmStatic private val BUTTON_PAPER_VISIBLE = "paper_visible"
        @JvmStatic private val BUTTON_SCISSORS_VISIBLE = "scissors_visible"
        @JvmStatic private val BUTTON_LIZARD_VISIBLE = "lizard_visible"
        @JvmStatic private val BUTTON_SPOCK_VISIBLE = "spock_visible"
        @JvmStatic private val BUTTON_START_OVER_VISIBLE = "start_over_visible"
        @JvmStatic private val RESULT_VISIBLE = "result"
        @JvmStatic private val TEXT_RESULT_WINNER_VISIBLE = "result_winner"
        @JvmStatic private val USER_TEXT_VISIBLE = "user_text"
        @JvmStatic private val COMPUTER_TEXT_VISIBLE = "computer_text"
    }

    private fun onButtonPlayPressed() {
        if (step == "user") {
            Toast.makeText(applicationContext, "Сделайте выбор, прежде чем продолжить!", Toast.LENGTH_SHORT).show()
        } else {
            choiceComputer = choice.random()
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

        computerText.visibility = View.VISIBLE
        userText.visibility = View.VISIBLE
        result.visibility = View.VISIBLE

        computerText.setText("Компьютера - \n$choiceComputer")
        userText.setText("Пользователя - \n$choiceUser")

        buttonPlay.visibility = View.GONE
        buttonStartOver.visibility = View.VISIBLE
    }

    private fun getResultOfGame() {
        textResult_Winner.visibility = View.VISIBLE

        if (choiceUser == choiceComputer)
            textResult_Winner.text = "Победила ничья, попробуйте переиграть"
        else if ((choiceUser == choice[0] && (choiceComputer == choice[2] || choiceComputer == choice[3])) ||
            (choiceUser == choice[1] && (choiceComputer == choice[0] || choiceComputer == choice[4])) ||
            (choiceUser == choice[2] && (choiceComputer == choice[1] || choiceComputer == choice[3])) ||
            (choiceUser == choice[3] && (choiceComputer == choice[4] || choiceComputer == choice[1])) ||
            (choiceUser == choice[4] && (choiceComputer == choice[2] || choiceComputer == choice[0]))
        ) {
            text_Result = "Победил пользователь"
            textResult_Winner.text = text_Result
        } else {
            text_Result = "Победил компьютер"
            textResult_Winner.text = text_Result
        }
    }

    private fun onButtonStartOverPressed() {
        buttonScissors.visibility = View.VISIBLE
        buttonPaper.visibility = View.VISIBLE
        buttonSpock.visibility = View.VISIBLE
        buttonLizard.visibility = View.VISIBLE
        buttonRock.visibility = View.VISIBLE
        buttonPlay.visibility = View.VISIBLE

        buttonStartOver.visibility = View.GONE
        computerText.visibility = View.GONE
        userText.visibility = View.GONE
        textResult_Winner.visibility = View.GONE
        result.visibility = View.GONE
    }

    private fun onButtonRockPressed() {
        step = "computer"
        choiceUser = buttonRock.text.toString().lowercase()
    }

    private fun onButtonPaperPressed() {
        step = "computer"
        choiceUser = buttonPaper.text.toString().lowercase()
    }

    private fun onButtonScissorsPressed() {
        step = "computer"
        choiceUser = buttonScissors.text.toString().lowercase()
    }

    private fun onButtonLizardPressed() {
        step = "computer"
        choiceUser = buttonLizard.text.toString().lowercase()
    }

    private fun onButtonSpockPressed() {
        step = "computer"
        choiceUser = buttonSpock.text.toString().lowercase()
    }
}