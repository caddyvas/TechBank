package com.deep.techbank.presentation.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deep.techbank.presentation.ui.theme.TechBankTheme
import com.deep.techbank.presentation.viewModel.LoginIntent
import com.deep.techbank.presentation.viewModel.LoginViewModel

/**
 * : It is now a "dumb" view. It only knows how to display the LoginState and how to send LoginIntents when the user interacts with it.
 */
@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {

    val state by loginViewModel.state.collectAsState()

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        unfocusedBorderColor = Color.Black,
        focusedBorderColor = Color.Black
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF8091F3))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // User Type Selection
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = state.userType == "Admin",
                onClick = { loginViewModel.onIntent(LoginIntent.UserTypeChange("Admin")) }
            )
            Text(text = "Admin")

            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = state.userType == "Customer",
                onClick = { loginViewModel.onIntent(LoginIntent.UserTypeChange("Customer")) }
            )
            Text(text = "Customer")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Conditional Text Fields
        if (state.userType == "Admin") {
            OutlinedTextField(
                value = state.username,
                onValueChange = { loginViewModel.onIntent(LoginIntent.UsernameChanged(it)) },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )
        } else {
            OutlinedTextField(
                value = state.accountNumber,
                onValueChange = { loginViewModel.onIntent(LoginIntent.AccountNumberChanged(it)) },
                label = { Text("Account Number") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = { loginViewModel.onIntent(LoginIntent.PasswordChanged(it)) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldColors
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { loginViewModel.onIntent(LoginIntent.SubmitLogin) },
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape
        ) {
            Text(text = "SUBMIT")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TechBankTheme {
        // We can't easily preview the ViewModel-driven screen without a fake ViewModel.
        // For now, we can leave this as is or create a separate preview composable.
    }
}
