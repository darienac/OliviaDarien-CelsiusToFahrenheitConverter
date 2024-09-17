package com.example.cs501_g2_q1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.hsl
import androidx.compose.ui.graphics.Color.Companion.hsv
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cs501_g2_q1.ui.theme.CS501_G2_Q1Theme
import kotlin.math.floor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CS501_G2_Q1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppLayout(modifier=Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppLayout(modifier: Modifier = Modifier) {

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment=Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        var celsiusVal by remember { mutableFloatStateOf(0f) }
        var fahrenheitVal by remember { mutableFloatStateOf(32f) }

        TempSlider("Celsius", celsiusVal, 0f..100f) { value: Float ->
            celsiusVal = floor(value * 100f) / 100f
            fahrenheitVal = celsiusVal * (9f/5f) + 32f
            fahrenheitVal = floor(fahrenheitVal * 100f) / 100f
        }
        TempSlider("Fahrenheit", fahrenheitVal, 0f..212f) { value: Float ->
            fahrenheitVal = floor(value * 100f) / 100f
            if (fahrenheitVal < 32f) {
                fahrenheitVal = 32f
            }
            celsiusVal = (fahrenheitVal - 32f) * (5f/9f)
            celsiusVal = floor(celsiusVal * 100f) / 100f
        }
        Text(
            text=(if (celsiusVal <= 20f) "I wish it were warmer" else "I wish it were colder"),
            color = (if (celsiusVal <= 20f) Color.hsv(194f, 0.37f, 0.52f) else Color.hsv(14f, 0.51f, 0.70f))
        )
    }
}

@Composable
fun TempSlider(label: String, value: Float, range: ClosedFloatingPointRange<Float>, onValueChange: (Float) -> Unit) {
    Text(text=label, fontSize=24.sp, fontWeight=FontWeight.Bold)
    Slider(value=value, onValueChange=onValueChange, valueRange=range)
    Text(text=value.toString(), fontSize=20.sp, textDecoration=TextDecoration.Underline)
    Spacer(modifier=Modifier.height(80.dp))
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    CS501_G2_Q1Theme {
        AppLayout()
    }
}