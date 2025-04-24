package com.trupti.movieapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trupti.movieapplication.ui.theme.MovieApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieApplicationTheme {
                Scaffold {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(it),
                        contentAlignment = Alignment.Center
                    ){
                        Column(

                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center

                        ){
                            Greeting()

                            Spacer(modifier = Modifier.padding(16.dp))

                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.padding(16.dp)
                                    .fillMaxWidth(0.8f)
                            ) {
                                Text(
                                    text = "Get Started",
                                    fontSize = 18.sp
                                )
                            }

                        }






                    }

                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Text(
        text = "Welcome to Movie App",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Yellow

    )
}

