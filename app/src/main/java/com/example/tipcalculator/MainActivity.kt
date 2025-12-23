package com.example.tipcalculator


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme { // using default theme for now
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipCalculatorScreen()
                }
            }
        }
    }
}

/*@Composable annotation to mark function as a Composable, allowing to
describe part of the UI in a declarative way using Jetpack Compose*/
@Composable
fun TipCalculatorScreen(modifier: Modifier = Modifier) {
    var inputBillAmount by remember { mutableStateOf("") }
    var tipAmount by remember { mutableStateOf(0.0) }
    var totalBill by remember { mutableStateOf(0.0) }

    /*currency formatter*/
    fun formatCurrency(amount: Double): String {
        return NumberFormat.getCurrencyInstance().format(amount)
    }

    /*logic to calculate tip amount*/
    fun calculateTip(percentage: Double) {
        val bill = inputBillAmount.toDoubleOrNull() ?: 0.0
        val tip = bill * percentage
        tipAmount = tip
        totalBill = bill + tip
    }

    /*centered horizontally in the screen with the EditText on top, the button below it
    in a single row, and the TextView below the button*/
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = inputBillAmount,
            onValueChange = { inputBillAmount = it },
            label = {
                Text(
                    text = "Enter Bill Amount",
                    style = TextStyle(color = Color.Gray)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(0.7f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        /*single row button spaced evenly*/
        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { calculateTip(0.15) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                ),
                shape = RectangleShape
            ) {
                Text("15%")
            }
            Button(
                onClick = { calculateTip(0.18) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                ),
                shape = RectangleShape
            ) {
                Text("18%")
            }
            Button(
                onClick = { calculateTip(0.20) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                ),
                shape = RectangleShape) {
                Text("20%")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        /*textView to view the tip amount and total bill amount
        default tip set to 0.00 and total bill set to 0.00*/
        Text(
            text = "Tip: ${formatCurrency(tipAmount)}, Total Bill: ${formatCurrency(totalBill)}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray
        )
    }
}

/*preview pane*/
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TipCalculatorScreenPreview() {
    MaterialTheme {
        TipCalculatorScreen()
    }
}