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
    private lateinit var computerText: TextView
    private lateinit var userText: TextView

    private var nameChoiceComputer = ""
    private var choiceComputer = 0
    private var nameChoiceUser = ""
    private var choiceUser = 0
    private var step = "user"
    //private var choice = listOf(1, 2', '3', '4', '5')

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

        buttonPlay.setOnClickListener { onButtonPlayPressed() }
        buttonRock.setOnClickListener { choiceUser = onButtonRockPressed() }
        buttonPaper.setOnClickListener { choiceUser = onButtonPaperPressed() }
        buttonScissors.setOnClickListener { choiceUser = onButtonScissorsPressed() }
        buttonLizard.setOnClickListener { choiceUser = onButtonLizardPressed() }
        buttonSpock.setOnClickListener { choiceUser = onButtonSpockPressed() }
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
            Toast.makeText(applicationContext, choiceComputer.toString(), Toast.LENGTH_SHORT).show()
            getResultOfGame()
            step = "user"
        }
    }

    private fun getResultOfGame() {
        //1-камень, 2-бумага, 3-ножницы, 4-ящерица, 5-спок
        if (choiceUser == choiceComputer)
            onCreate(savedInstanceState = null)
        else if ((choiceUser == 1 && (choiceComputer == 3 || choiceComputer == 4)) ||
            (choiceUser == 2 && (choiceComputer == 1 || choiceComputer == 5)) ||
            (choiceUser == 3 && (choiceComputer == 2 || choiceComputer == 4)) ||
            (choiceUser == 4 && (choiceComputer == 5 || choiceComputer == 2)) ||
            (choiceUser == 5 && (choiceComputer == 3 || choiceComputer == 1))
        )
            Toast.makeText(applicationContext, "Победил пользователь", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(applicationContext, "Победил компьютер", Toast.LENGTH_SHORT).show()
    }

    private fun onButtonRockPressed(): Int {
        step = "computer"
        choiceUser = 1
        return choiceUser
    }

    private fun onButtonPaperPressed(): Int {
        step = "computer"
        choiceUser = 2
        return choiceUser
    }

    private fun onButtonScissorsPressed(): Int {
        step = "computer"
        choiceUser = 3
        return choiceUser
    }

    private fun onButtonLizardPressed(): Int {
        step = "computer"
        choiceUser = 4
        return choiceUser
    }

    private fun onButtonSpockPressed(): Int {
        step = "computer"
        choiceUser = 5
        return choiceUser
    }

    private fun changesAfterUserSelection() {
        buttonScissors.visibility = View.GONE
        buttonPaper.visibility = View.GONE
        buttonSpock.visibility = View.GONE
        buttonLizard.visibility = View.GONE
        buttonRock.visibility = View.GONE

        //1-камень, 2-бумага, 3-ножницы, 4-ящерица, 5-спок
        nameChoiceComputer = valueOfNumber(choiceComputer, nameChoiceComputer)
        nameChoiceUser = valueOfNumber(choiceUser, nameChoiceUser)
        computerText.visibility = View.VISIBLE
        userText.visibility = View.VISIBLE

        computerText.text = "Компьютера - $nameChoiceComputer"
        userText.text = "Пользователя - $nameChoiceUser"
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
}