package com.example.visprog_assignment5.ui.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.visprog_assignment5.ui.viewmodel.Soal2ViewModel

val ColorSoal2Blue: Color = Color(0xFF495D91)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Soal2View(courseViewModel: Soal2ViewModel = viewModel()){
    val textFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Color.Black,
        disabledTextColor = Color.Transparent,
        containerColor = Color.White,
        focusedIndicatorColor = ColorSoal2Blue,
        unfocusedIndicatorColor = Color.Transparent
    )

    val uiState by courseViewModel.uiState.collectAsState()

    var sks by rememberSaveable {
        mutableStateOf("")
    }
    var score by rememberSaveable {
        mutableStateOf("")
    }
    var name by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(0.95f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Courses", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "Total SKS: ${courseViewModel.totalSKS}")
            Text(text = "IPK: ${courseViewModel.IPK}")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(0.95f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    alignment = Alignment.CenterHorizontally,
                    space = 16.dp
                )
            ) {
                TextField( //sks textfield
                    value = sks,
                    onValueChange = { if (it.isDigitsOnly()) sks = it },
                    modifier = Modifier
                        .border(2.dp, ColorSoal2Blue, RoundedCornerShape(7))
                        .weight(0.4f)
                    ,
                    label = { Text(text = "SKS")},
                    colors = textFieldColors
                )
                TextField( //score textfield
                    value = score,
                    onValueChange = { if (it.isDigitsOnly()) score = it },
                    modifier = Modifier
                        .border(2.dp, ColorSoal2Blue, RoundedCornerShape(7))
                        .weight(0.4f)
                    ,
                    label = { Text(text = "Score")},
                    colors = textFieldColors
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField( //name textfield
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .border(2.dp, ColorSoal2Blue, RoundedCornerShape(7))
                        .clip(RoundedCornerShape(16.dp)),
                    label = { Text(text = "Name")},
                    colors = textFieldColors
                )
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(ColorSoal2Blue),
                ) {
                    Text(text = "+", color = Color.White)
                }
            }
        }


//        The Thingy
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal2Preview(){
    Soal2View()
}