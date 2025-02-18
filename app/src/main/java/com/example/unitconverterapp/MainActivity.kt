package com.example.unitconverterapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverterapp.ui.theme.UnitConverterAppTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),//The surface should fill the entire screen size
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

//Let's add our own composable
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UnitConverter() {

    var inputValue by remember {
        mutableStateOf("")
    }
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit by remember {
        mutableStateOf("Meters")
    }
    var outputUnit by remember {
        mutableStateOf("Meters")
    }
    //The input and output dropdown menus should be collapsed by default
    var inputExpanded by remember {
        mutableStateOf(false)
    }
    var outputExpanded by remember {
        mutableStateOf(false)
    }
    val inputConversionFactor = remember {
        mutableStateOf(1.00)
    }
    val outputConversionFactor = remember {
        mutableStateOf(1.00)
    }

    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Cursive,
        fontSize = 30.sp,
        color = Color.Black
    )

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        //inputValue.toDoubleOrNull(): attempts to convert the inputValue to a Double. If the conversion is successful, it returns the Double value. If the conversion fails (e.g., if inputValue is not a valid number), it returns null.
        //Elvis operator (?:): This operator is used to provide a default value in case the expression on its left side is null.
        //0.0: This is the default value that will be used if inputValue.toDoubleOrNull() returns null.

        val result =
            (inputValueDouble * inputConversionFactor.value * 100 / outputConversionFactor.value).roundToInt() / 100
        outputValue = result.toString()
    }

    //Since our func is a composable, we can use other composable functions in it
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Here our UI elements will be stacked on top of each other
        Text(text = "Unit Converter", style = customTextStyle)
        Spacer(modifier = Modifier.height(18.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnits()
            //Here goes what should happen when the value of our OutlinedTextField changes
        },
            label = { Text(text = "Enter Value") })
        Spacer(modifier = Modifier.height(18.dp))
        Row {
            //Here our UI elements will be next to each other
            //Input box
            Box {
                //Input button
                Button(onClick = { inputExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down Arrow")
                }
                DropdownMenu(
                    expanded = inputExpanded,
                    onDismissRequest = { inputExpanded = false }) {
                    listOf(
                        "Centimeters" to 0.01,
                        "Meters" to 1.00,
                        "Feet" to 0.3048,
                        "Millimeters" to 0.001,
                        "Kilometers" to 1000.0,
                        "Miles" to 1609.34
                    ).forEach { (unit, factor) ->
                        DropdownMenuItem(text = { Text(unit) }, onClick = {
                            inputExpanded = false
                            inputUnit = unit
                            inputConversionFactor.value = factor
                            convertUnits()
                        })
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output box
            Box {
                //Output button
                Button(onClick = { outputExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down Arrow")
                }
                DropdownMenu(
                    expanded = outputExpanded,
                    onDismissRequest = { outputExpanded = false }) {
                    listOf(
                        "Centimeters" to 0.01,
                        "Meters" to 1.00,
                        "Feet" to 0.3048,
                        "Millimeters" to 0.001,
                        "Kilometers" to 1000.0,
                        "Miles" to 1609.34
                    ).forEach { (unit, factor) ->
                        DropdownMenuItem(text = { Text(unit) }, onClick = {
                            outputExpanded = false
                            outputUnit = unit
                            outputConversionFactor.value = factor
                            convertUnits()
                        })
                    }
                }

            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        //Result Text
        Text(
            text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}