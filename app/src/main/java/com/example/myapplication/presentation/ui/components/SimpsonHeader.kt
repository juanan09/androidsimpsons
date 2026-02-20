package com.example.myapplication.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.SearchBarBackground
import com.example.myapplication.ui.theme.SearchBarContent

@Composable
fun SimpsonHeader(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = SearchBarBackground,
        tonalElevation = 8.dp,
        shadowElevation = 4.dp,
    ) {
        Text(
            text = "Simpson Characters",
            color = SearchBarContent,
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 16.dp, bottom = 16.dp),
            textAlign = TextAlign.Center,
            letterSpacing = 1.5.sp
        )
    }
}
