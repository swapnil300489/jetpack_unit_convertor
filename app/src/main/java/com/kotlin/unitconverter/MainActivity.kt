package com.kotlin.unitconverter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.kotlin.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    UnitConvertor()
                }
            }
        }
    }
}

@Composable
fun UnitConvertor() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var iconversionFactor = remember { mutableStateOf(0.01) }
    var oconversionFactor = remember { mutableStateOf(0.01) }

    fun outputItemClick(){
        oExpanded = false
    }

    fun inputItemClick(){
        iExpanded = false
    }

    fun convertInput(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * iconversionFactor.value * 100.0 / oconversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.Red
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Unit Convertor",
            style = customTextStyle )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value =inputValue  ,
            onValueChange = {
                inputValue = it
                convertInput()
            },
            label = { Text(text = "Enter Value")}
        )


        Spacer(modifier = Modifier.height(20.dp))
        Row (horizontalArrangement = Arrangement.SpaceBetween){
                Box {
                    Button(onClick = { iExpanded = true }) {
                        Text(text = inputUnit)
                        Icon(Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down")
                    }

                    DropdownMenu(expanded = iExpanded, onDismissRequest = { inputItemClick() }) {
                        DropdownMenuItem(text = {
                            Text(text = "Centimeter")

                        }, onClick = {
                            inputUnit = "Centimeter"
                            iconversionFactor.value = 0.01
                            convertInput()
                            inputItemClick()
                        })
                        DropdownMenuItem(text = {
                            Text(text = "Meter")

                        }, onClick = {
                            inputUnit = "Meter"
                            iconversionFactor.value = 1.0
                            convertInput()
                            inputItemClick()
                        })
                        DropdownMenuItem(text = {
                            Text(text = "Feet")

                        }, onClick = {
                            inputUnit = "Feet"
                            iconversionFactor.value = 0.3048
                            convertInput()
                            inputItemClick()
                        })
                        DropdownMenuItem(text = {
                            Text(text = "Milimeter")

                        }, onClick = {
                            inputUnit = "Milimeter"
                            iconversionFactor.value = 0.001
                            convertInput()
                            inputItemClick()
                        })

                    }
                }

            Spacer(modifier = Modifier.width(20.dp))

            Box {
                    Button(onClick = { oExpanded = true }) {
                        Text(text = outputUnit)
                        Icon(Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down")
                        
                    }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { outputItemClick() }) {
                    DropdownMenuItem(text = {
                        Text(text = "Centimeter")

                    }, onClick = {
                        outputUnit = "Centimeter"
                        oconversionFactor.value = 0.01
                        convertInput()
                        outputItemClick()

                    })
                    DropdownMenuItem(text = {
                        Text(text = "Meter")

                    }, onClick = {
                        outputItemClick()
                        outputUnit = "Meter"
                        oconversionFactor.value = 1.00
                        convertInput()
                    })
                    DropdownMenuItem(text = {
                        Text(text = "Feet")

                    }, onClick = {
                        outputUnit = "Feet"
                        oconversionFactor.value = 0.3048
                        convertInput()
                        outputItemClick()
                    })
                    DropdownMenuItem(text = {
                        Text(text = "Milimeter")

                    }, onClick = {
                        outputUnit = "Milimeter"
                        oconversionFactor.value = 0.001
                        convertInput()
                        outputItemClick() })

                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}





@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConvertor()
}