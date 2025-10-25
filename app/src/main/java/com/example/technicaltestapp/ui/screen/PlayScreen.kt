package com.example.technicaltestapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.technicaltestapp.data.COLS
import com.example.technicaltestapp.data.Player
import com.example.technicaltestapp.data.ROWS
import com.example.technicaltestapp.viewmodel.MainViewModel

@Composable
fun PlayScreen(viewModel: MainViewModel = viewModel()) {
    val board = viewModel.board
    val currentPlayer = viewModel.currentPlayer
    val winner = viewModel.winner

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = when {
                winner != null -> "GAME ENDED"
                else -> "PLAY"
            },
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = when {
                currentPlayer == Player.PLAYER1 -> "Player 1  <="
                else -> "Player 1"
            },
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = when {
                currentPlayer == Player.PLAYER2 -> "Player 2  <="
                else -> "Player 2"
            },
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(16.dp))

        // iterating 6x7 times
        for (r in 0 until ROWS) {
            Row {
                for (c in 0 until COLS) {
                    val cell = board[r][c]
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .padding(4.dp)
                            .background(Color.Blue)
                            .clickable(enabled = winner == null) { viewModel.dropDisc(c) },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(
                                    when (cell) {
                                        Player.PLAYER1 -> Color.Red
                                        Player.PLAYER2 -> Color.Yellow
                                        else -> Color.White
                                    }
                                )
                        )
                    }
                }
            }
        }

        // Checking the winner to show the button to reset the game
        if (winner != null) {
            Spacer(Modifier.height(16.dp))
            Text("Congratulations $winner! you are the winner", fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            Button(onClick = { viewModel.restart() }) {
                Text("Restart the game")
            }
        }
    }
}
