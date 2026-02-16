package com.example.myapplication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.SearchBarBackground
import com.example.myapplication.ui.theme.SearchBarContent

@Composable
fun Searcher(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Search Springfield...",
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { 
            Text(
                text = placeholder, 
                color = SearchBarContent.copy(alpha = 0.7f) 
            ) 
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = SearchBarContent
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = SearchBarBackground,
            unfocusedContainerColor = SearchBarBackground,
            disabledContainerColor = SearchBarBackground,
            cursorColor = SearchBarContent,
            focusedTextColor = SearchBarContent,
            unfocusedTextColor = SearchBarContent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(50),
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun SearcherPreview() {
    MyApplicationTheme {
        var text by remember { mutableStateOf("") }
        Searcher(
            value = text,
            onValueChange = { text = it }
        )
    }
}
