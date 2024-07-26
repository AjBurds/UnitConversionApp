package com.example.unitconversionapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconversionapp.ui.theme.UnitConversionAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UnitConversionScreen() {
    var input by remember { mutableStateOf(TextFieldValue("")) }
    var output by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedUnit by remember { mutableStateOf("Select a Unit") }
    val units = listOf("Temperature", "Length", "Weight")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .border(1.dp, Color.Gray)
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    if (input.text.isEmpty()) {
                        Text("Input Value")
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .border(1.dp, Color.Gray)
                .padding(8.dp)
        ) {
            Text(selectedUnit, modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .padding(8.dp))
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                units.forEach { unit ->
                    DropdownMenuItem(onClick = {
                        selectedUnit = unit
                        expanded = false
                    }) {
                        Text(unit)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val inputValue = input.text.toDoubleOrNull()
                if (inputValue != null) {
                    output = when (selectedUnit) {
                        "Temperature" -> convertTemperature(inputValue).toString()
                        "Length" -> convertLength(inputValue).toString()
                        "Weight" -> convertWeight(inputValue).toString()
                        else -> "Select a Unit"
                    }
                } else {
                    output = "Invalid input"
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
        ) {
            Text("Convert")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = output,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

fun convertTemperature(value: Double): Double {
    return value * 9 / 5 + 32
}

fun convertLength(value: Double): Double {
    return value * 3.28084
}

fun convertWeight(value: Double): Double {
    return value * 2.20462
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UnitConversionAppTheme {
        UnitConversionScreen()
    }
}
