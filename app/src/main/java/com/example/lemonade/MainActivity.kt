package com.example.lemonade

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LemonApp()
                }

            }
        }
    }
}

@Composable
fun LemonApp(){

    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    var tapCount by remember { mutableStateOf(0) }

    val imageResource = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart

    }
    val contentDescription = when (currentStep) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }
    val text = when (currentStep) {
        1 -> R.string.select_lemon
        2 -> R.string.squeeze_lemon
        3 -> R.string.drink_lemon
        else -> R.string.restart_lemon}



    Column {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(top = 25.dp)
                .background(Color(0xFFF1E03C).copy(alpha = 0.5f))
        ) {
            Text(
                text = "Lemonade",
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                fontFamily = FontFamily.SansSerif

            )
        }

        Spacer(modifier = Modifier.height(250.dp))

        Image(painter = painterResource(imageResource),
            contentDescription = stringResource(contentDescription),
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(32.dp))
                .background(Color(0xFF20B2AA).copy(alpha = 0.3f))
                .clickable {
                    when (currentStep) {
                        1 -> {
                            currentStep = 2
                            squeezeCount = (2..10).random()
                            tapCount = 0
                        }

                        2 -> {
                            if (tapCount >= squeezeCount) {
                                currentStep = 3
                            } else {
                                tapCount++

                            }
                        }

                        3 -> {
                            currentStep = 4
                        }

                        else -> {
                            currentStep = 1
                        }
                    }
                })

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(text), fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally), fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(40.dp))

        if(currentStep != 1) {
            Text(text = stringResource(R.string.squeeze_count, tapCount), fontSize = 30.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }


}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }

}