package com.example.unitconverterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.unitconverterapp.ui.theme.UnitConverterAppTheme

class MainActivity : ComponentActivity() {
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
@Composable
fun UnitConverter() {
    //Since our func is a composable, we can use other Composables in it
    Column {
        //Here our UI elements will be stacked on top of each other
        Text(text = "Unit Converter")
        OutlinedTextField(value = "", onValueChange = {
            //Here goes what should happen when the value of our OutlinedTextField changes
        })
        Row {
            //Here our UI elements will be next to each other


        }
    }
}


@Preview
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}