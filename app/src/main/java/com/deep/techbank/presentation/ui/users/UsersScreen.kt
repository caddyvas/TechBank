package com.deep.techbank.presentation.ui.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deep.techbank.presentation.viewModel.UserIntent
import com.deep.techbank.presentation.viewModel.UsersViewModel

// The viewModel parameter is now required, no longer a default.
@Composable
fun UsersScreen(viewModel: UsersViewModel) {
    val state by viewModel.state.collectAsState()

    // When the screen first appears, send the intent to retrieve the users.
    LaunchedEffect(Unit) {
        viewModel.onIntent(UserIntent.RetrieveUsers)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (state.error != null) {
            Text(
                text = "Error: ${state.error}",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()
                .padding(16.dp)) {
                items(state.usersList) { user ->
                    // This is a simple representation of a user item.
                    // We can make this more complex later.
                    Text(text = user.name,
                        color = Color.Black)
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}
