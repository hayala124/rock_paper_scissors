package com.hayala.rock_paper_scissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import com.hayala.rock_paper_scissors.databinding.ActivityMainBinding
import kotlinx.parcelize.Parcelize

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val choice = arrayOf("камень", "бумага", "ножницы", "ящерица", "спок")
    lateinit var state: State
    private var step = "user"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.setOnClickListener { onButtonPlayPressed() }
        binding.btnRock.setOnClickListener { onButtonRockPressed() }
        binding.btnPaper.setOnClickListener { onButtonPaperPressed() }
        binding.btnScissors.setOnClickListener { onButtonScissorsPressed() }
        binding.btnLizard.setOnClickListener { onButtonLizardPressed() }
        binding.btnSpock.setOnClickListener { onButtonSpockPressed() }
        binding.btnStartOver.setOnClickListener { onButtonStartOverPressed() }


        state = if (savedInstanceState == null) {
            step = "user"
            State(
                textResultIsVisible = false,
                choiceUser = " ",
                choiceUserIsVisible = false,
                choiceComputer = " ",
                choiceComputerIsVisible = false,
                winner = " ",
                textWinnerIsVisible = false,
                buttonPlayIsVisible = true,
                buttonStartOverIsVisible = false,
                buttonRockIsVisible = true,
                buttonPaperIsVisible = true,
                buttonScissorsIsVisible = true,
                buttonLizardIsVisible = true,
                buttonSpockIsVisible = true
            )
        } else {
            step = savedInstanceState.getString(KEY_STEP).toString()
            savedInstanceState.getParcelable(KEY_STATE)!!
        }
        setState()
    }

    private fun setState() = with(binding) {
        result.visibility = if (state.textResultIsVisible) View.VISIBLE else View.INVISIBLE
        textChoiceUser.setText(state.choiceUser)
        textChoiceUser.visibility = if (state.choiceUserIsVisible) View.VISIBLE else View.INVISIBLE
        textChoiceComputer.setText(state.choiceComputer)
        textChoiceComputer.visibility = if (state.choiceComputerIsVisible) View.VISIBLE else View.INVISIBLE
        textWinner.setText(state.winner)
        textWinner.visibility = if (state.textWinnerIsVisible) View.VISIBLE else View.INVISIBLE
        btnPlay.visibility = if (state.buttonPlayIsVisible) View.VISIBLE else View.INVISIBLE
        btnStartOver.visibility = if (state.buttonStartOverIsVisible) View.VISIBLE else View.INVISIBLE
        btnRock.visibility = if (state.buttonRockIsVisible) View.VISIBLE else View.INVISIBLE
        btnPaper.visibility = if (state.buttonPaperIsVisible) View.VISIBLE else View.INVISIBLE
        btnScissors.visibility = if (state.buttonScissorsIsVisible) View.VISIBLE else View.INVISIBLE
        btnLizard.visibility = if (state.buttonLizardIsVisible) View.VISIBLE else View.INVISIBLE
        btnSpock.visibility = if (state.buttonSpockIsVisible) View.VISIBLE else View.INVISIBLE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state)
        outState.putString(KEY_STEP, step)
    }

    @Parcelize
    class State(
        var textResultIsVisible: Boolean,
        var choiceUser: String,
        var choiceUserIsVisible: Boolean,
        var choiceComputer: String,
        var choiceComputerIsVisible: Boolean,
        var winner: String,
        var textWinnerIsVisible: Boolean,
        var buttonPlayIsVisible: Boolean,
        var buttonStartOverIsVisible: Boolean,
        var buttonRockIsVisible: Boolean,
        var buttonPaperIsVisible: Boolean,
        var buttonScissorsIsVisible: Boolean,
        var buttonLizardIsVisible: Boolean,
        var buttonSpockIsVisible: Boolean,
    ) : Parcelable

    companion object {
        @JvmStatic private val KEY_STATE = "STATE"
        @JvmStatic private val KEY_STEP = "STEP"
    }

    private fun onButtonPlayPressed() {
        if (step == "user") { Toast.makeText(applicationContext, "Сделайте выбор, прежде чем продолжить!", Toast.LENGTH_SHORT).show() }
        else {
            state.choiceComputer = choice.random()
            changesAfterUserSelection()
            getResultOfGame()
            step = "user"
            setState()
        }
    }

    private fun changesAfterUserSelection() {
        state.buttonScissorsIsVisible = !state.buttonScissorsIsVisible
        state.buttonPaperIsVisible = !state.buttonPaperIsVisible
        state.buttonSpockIsVisible = !state.buttonSpockIsVisible
        state.buttonLizardIsVisible = !state.buttonLizardIsVisible
        state.buttonRockIsVisible = !state.buttonRockIsVisible

        state.choiceUserIsVisible = !state.choiceUserIsVisible
        state.choiceComputerIsVisible = !state.choiceComputerIsVisible
        state.textResultIsVisible = !state.textResultIsVisible

        state.buttonPlayIsVisible = !state.buttonPlayIsVisible
        state.buttonStartOverIsVisible = !state.buttonStartOverIsVisible
    }

    private fun getResultOfGame() {
        state.textWinnerIsVisible = !state.textWinnerIsVisible

        if (state.choiceUser == state.choiceComputer)
            state.winner = "Победила ничья, попробуйте переиграть"
        else if ((state.choiceUser == choice[0] && (state.choiceComputer == choice[2] || state.choiceComputer == choice[3])) ||
            (state.choiceUser == choice[1] && (state.choiceComputer == choice[0] || state.choiceComputer == choice[4])) ||
            (state.choiceUser == choice[2] && (state.choiceComputer == choice[1] || state.choiceComputer == choice[3])) ||
            (state.choiceUser == choice[3] && (state.choiceComputer == choice[4] || state.choiceComputer == choice[1])) ||
            (state.choiceUser == choice[4] && (state.choiceComputer == choice[2] || state.choiceComputer == choice[0]))
        )
            state.winner = "Победил пользователь"
        else
            state.winner = "Победил компьютер"
    }

    private fun onButtonStartOverPressed() {
        state.buttonScissorsIsVisible = !state.buttonScissorsIsVisible
        state.buttonPaperIsVisible = !state.buttonPaperIsVisible
        state.buttonSpockIsVisible = !state.buttonSpockIsVisible
        state.buttonLizardIsVisible = !state.buttonLizardIsVisible
        state.buttonRockIsVisible = !state.buttonRockIsVisible
        state.buttonPlayIsVisible = !state.buttonPlayIsVisible

        state.buttonStartOverIsVisible = !state.buttonStartOverIsVisible
        state.choiceUserIsVisible = !state.choiceUserIsVisible
        state.choiceComputerIsVisible = !state.choiceComputerIsVisible
        state.textResultIsVisible = !state.textResultIsVisible
        state.textWinnerIsVisible = !state.textWinnerIsVisible

        setState()
    }

    private fun onButtonRockPressed() {
        step = "computer"
        state.choiceUser = binding.btnRock.text.toString().lowercase()
    }

    private fun onButtonPaperPressed() {
        step = "computer"
        state.choiceUser = binding.btnPaper.text.toString().lowercase()
    }

    private fun onButtonScissorsPressed() {
        step = "computer"
        state.choiceUser = binding.btnScissors.text.toString().lowercase()
    }

    private fun onButtonLizardPressed() {
        step = "computer"
        state.choiceUser = binding.btnLizard.text.toString().lowercase()
    }

    private fun onButtonSpockPressed() {
        step = "computer"
        state.choiceUser = binding.btnSpock.text.toString().lowercase()
    }
}