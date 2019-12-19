package com.example.tictactoyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        restart.setOnClickListener {
            restartGame()
        }
    }

    fun bnClick(view: View) {
        val buSelected: Button = view as Button

        var cellId = 0
        when (buSelected.id) {
            R.id.b1 -> cellId = 1
            R.id.b2 -> cellId = 2
            R.id.b3 -> cellId = 3
            R.id.b4 -> cellId = 4
            R.id.b5 -> cellId = 5
            R.id.b6 -> cellId = 6
            R.id.b7 -> cellId = 7
            R.id.b8 -> cellId = 8
            R.id.b9 -> cellId = 9
        }
        playGame(cellId, buSelected)
//        Log.d("buClick:", buSelected.id.toString())
//        Log.d("buClick: cellId:", cellId.toString())
    }

    private var activePlayer = 1

    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()

    private fun playGame(cellId: Int, buSelected: Button) {

        if (activePlayer == 1) {
            buSelected.text = "X"
            buSelected.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            activePlayer = 2
            autoPlay()
        } else {
            buSelected.text = "O"
            buSelected.setBackgroundResource(R.color.yellow)
            player2.add(cellId)
            activePlayer = 1
        }

        buSelected.isEnabled = false

        checkWinner()
    }

    private fun checkWinner() {
        var winner = -1

        // row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        // row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        // row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        // col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        // col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        // col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        // diagonal 1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        // diagonal 3
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        when (winner) {
            1 -> {
                player1WinsCounts += 1
                Toast.makeText(this, "Player 1 win the game", Toast.LENGTH_LONG).show()
                Handler().postDelayed({
                    restartGame()
                }, 1000)
            }
            2 -> {
                player2WinsCounts += 1
                Toast.makeText(this, "Player 2 win the game", Toast.LENGTH_LONG).show()
                Handler().postDelayed({
                    restartGame()
                }, 1000)
            }
        }
    }
    private fun autoPlay() {
        val emptyCells = ArrayList<Int>()
        for (cellId: Int in 1..9) {
            if (!(player1.contains(cellId) || player2.contains(cellId))) {
                emptyCells.add(cellId)
            }
        }
        val r = Random()
        try {
            val randIndex: Int = r.nextInt(emptyCells.size)
            val cellId: Int = emptyCells[randIndex]

            val buSelected: Button?
            buSelected = when (cellId) {
                1 -> b1
                2 -> b2
                3 -> b3
                4 -> b4
                5 -> b5
                6 -> b6
                7 -> b7
                8 -> b8
                9 -> b9
                else -> {
                    b1
                }
            }
            playGame(cellId, buSelected)

        } catch (e: Exception) {

        }
    }

    private var player1WinsCounts = 0
    private var player2WinsCounts = 0
    private fun restartGame() {
        activePlayer = 1
        player1.clear()
        player2.clear()

        for (cellId: Int in 1..9) {
            val buSelected = when (cellId) {
                1 -> b1
                2 -> b2
                3 -> b3
                4 -> b4
                5 -> b5
                6 -> b6
                7 -> b7
                8 -> b8
                9 -> b9
                else -> {
                    b1
                }
            }
            buSelected!!.text = ""
            buSelected.setBackgroundResource(R.color.white)
            buSelected.isEnabled=true
        }
        Toast.makeText(this, "Player1: $player1WinsCounts, Player2: " +
                "$player2WinsCounts",Toast.LENGTH_LONG).show()
    }
}
