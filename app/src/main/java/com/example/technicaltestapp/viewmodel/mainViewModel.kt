package com.example.technicaltestapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.technicaltestapp.data.COLS
import com.example.technicaltestapp.data.Player
import com.example.technicaltestapp.data.ROWS

class MainViewModel: ViewModel() {
    // 2 dimension array as a mutable state to update the UI
    var board by mutableStateOf(Array(ROWS) { Array(COLS) { Player.NONE } })
        private set

    // capturing the current player
    var currentPlayer by mutableStateOf(Player.PLAYER1)
        private set

    // capturing the winner (null if there is no winner)
    var winner by mutableStateOf<Player?>(null)
        private set

    // iterating from bottom to top to place our disc in a column
    fun dropDisc(column: Int) {
        if (winner != null) return
        for (row in ROWS - 1 downTo 0) {
            if (board[row][column] == Player.NONE) {
                board[row][column] = currentPlayer
                if (checkWinner(row, column)) {
                    winner = currentPlayer
                } else {
                    currentPlayer = if (currentPlayer == Player.PLAYER1) Player.PLAYER2 else Player.PLAYER1
                }
                break
            }
        }
    }

    fun restart() {
        board = Array(ROWS) { Array(COLS) { Player.NONE } }
        winner = null
        currentPlayer = Player.PLAYER1
    }

    // checking the winner
    // we calculate the number of the players discs in sertan direction and its oposite and see if it reaches 4
    private fun checkWinner(row: Int, col: Int): Boolean {
        val player = board[row][col]
        if (player == Player.NONE) return false
        val directions = listOf(
            Pair(1, 0),
            Pair(0, 1),
            Pair(1, 1),
            Pair(1, -1)
        )
        // we calculate consecutively in a direction and its opposite
        return directions.any { (dr, dc) ->
            countConsecutive(row, col, dr, dc, player) + countConsecutive(row, col, -dr, -dc, player) >= 3
        }
    }

    // calculates how many discs of one of the players is in a direction (dr, dc)
    // until it finds a blanck spot of a disc of player to and give us the count
    private fun countConsecutive(r: Int, c: Int, dr: Int, dc: Int, player: Player): Int {
        var count = 0
        var row = r + dr
        var col = c + dc
        // we don't exeed the board
        // and the player still the same
        while (row in 0 until ROWS && col in 0 until COLS && board[row][col] == player) {
            count++
            row += dr
            col += dc
        }
        return count
    }
}