package com.roshan.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()
            val scaffoldState = rememberScaffoldState()
            Scaffold(scaffoldState = scaffoldState) {
//                var counter by remember {
//                    mutableStateOf(0)
//                }
                val counter = produceState(initialValue = 0) {
                    delay(300)
                    value = 4
                }
                if (counter.value % 5 == 0 && counter.value > 0) {
                    LaunchedEffect(key1 = scaffoldState) {
                        scaffoldState.snackbarHostState.showSnackbar(
                            "Hello"
                        )
                    }

                }
                Button(onClick = { }) {
                    Text(text = "Click Me : $counter")
                }
            }
        }
    }
}

@Composable
fun DisposalCompose(backPressedDispatcher: OnBackPressedDispatcher) {
    val callback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Do Something
            }

        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }
}

