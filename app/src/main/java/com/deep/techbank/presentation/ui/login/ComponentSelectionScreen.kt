package com.deep.techbank.presentation.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// The constants now live with the Composable that uses them.
const val USERS_SCREEN_ID = "users"
const val ACCOUNTS_SCREEN_ID = "accounts"
const val TRANSACTIONS_SCREEN_ID = "transactions"

@Composable
fun ComponentSelectionScreen(
    onNavigate: (screenId: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { onNavigate(USERS_SCREEN_ID) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            shape = RectangleShape
        ) {
            Text(text = "VIEW USERS")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { onNavigate(ACCOUNTS_SCREEN_ID) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            shape = RectangleShape
        ) {
            Text(text = "VIEW ACCOUNTS")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { onNavigate(TRANSACTIONS_SCREEN_ID) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            shape = RectangleShape
        ) {
            Text(text = "VIEW TRANSACTIONS")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewOfComponentSelectionScreen() {
    ComponentSelectionScreen(onNavigate = { screenId ->
        println("Navigating to screen with ID: $screenId")
    })
}
