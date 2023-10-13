package com.example.visprog_assignment5.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.visprog_assignment5.model.Soal1Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class Soal1ViewModel: ViewModel(){

    private val _uiState = MutableStateFlow(Soal1Game())
    val uiState: StateFlow<Soal1Game> = _uiState.asStateFlow()

    init {
        resetGame()
        generateRandomNumber()
    }

    fun generateRandomNumber(){
        val randomNumber = Random.nextInt(0, 11)
        _uiState.update { currentState ->
            currentState.copy(
                randomNumber = randomNumber
            )
        }
    }

    fun updateUserGuess(guess: Int){
        _uiState.update { currentState->
            currentState.copy(
                guess = guess
            )
        }
    }

    //returns true if game keeps going, else returns false/ game over
    fun checkUserGuess(): Boolean{
        if(_uiState.value.guess == _uiState.value.randomNumber){
            _uiState.update { currentState->
                currentState.copy(
                    score = uiState.value.score + 1
                )
            }
        }else{
            _uiState.update { currentState->
                currentState.copy(
                    lives = uiState.value.lives - 1
                )
            }
            if(_uiState.value.lives == 0){
                return false
            }
        }
        return true
    }

    fun resetGame(){
        _uiState.update { currentState ->
            currentState.copy(
                lives = 3,
                score = 0
            )
        }
    }


}