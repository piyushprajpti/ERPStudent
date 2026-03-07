package com.piyushprajpti.erpstudent

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun App() {
    MaterialTheme {
        Text(
            text = "hello",
            fontSize = 50.sp
        )
    }
}