@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.visprog_assignment5.ui.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.visprog_assignment5.ui.viewmodel.Soal1ViewModel

val ColorSoal1Blue: Color = Color(0xFF4355B8)
val ColorSoal1Gray: Color = Color(0xFFE2E0EB)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Soal1View(gameViewModel: Soal1ViewModel = viewModel()) {
    val textFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Color.Black,
        disabledTextColor = Color.Transparent,
        containerColor = Color.White,
        focusedIndicatorColor = ColorSoal1Blue,
        unfocusedIndicatorColor = Color.Transparent
    )

    val uiState by gameViewModel.uiState.collectAsState()
    var guess by rememberSaveable {
        mutableStateOf("")
    }
    val openDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
                space = 18.dp,
                alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Guess The Number", fontSize = 24.sp)
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .fillMaxHeight(0.4f)
                .background(ColorSoal1Gray, RoundedCornerShape(5)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(0.85f  )
            ){
                Text(
                    text = "Number of Guesses: ${uiState.lives}",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .background(ColorSoal1Blue, RoundedCornerShape(20))
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }
            Text(
                text = "${uiState.randomNumber}",
                fontSize = 32.sp
            )
            Text(text = "From 1 to 10 Guess the Number")
            Text(text = "Score: ${uiState.score}")
            TextField(
                value = guess,
                onValueChange = { if (it.isDigitsOnly()) guess = it },
                modifier = Modifier
                    .border(2.dp, Color.Blue, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp)),
                label = { Text(text = "Enter your guess")},
                colors = textFieldColors
            )
        }
        Button(
            onClick = {
                gameViewModel.updateUserGuess(guess.toInt())
                if(!gameViewModel.checkUserGuess()){
                    openDialog.value = true
                }
                gameViewModel.generateRandomNumber()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorSoal1Blue,
                disabledContainerColor = ColorSoal1Gray
            ),
            enabled = guess.isNotBlank(),
            modifier = Modifier.fillMaxWidth(0.85f)
        ) {
            Text(text = "Submit")
        }

        if(openDialog.value){
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                title = {
                    Text(text = "Welp")
                },
                text = {
                    Text(text = "You scored: ${uiState.score}")
                },
                confirmButton = {
                    Button(onClick = {
                        openDialog.value = false
                        gameViewModel.resetGame()
                    }) {
                        Text(text = "Play Again")
                    }
                })
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal1Preview() {
    Soal1View()
}