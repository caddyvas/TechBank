package com.deep.techbank.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.deep.techbank.R
import com.deep.techbank.presentation.viewModel.LoginIntent
import com.deep.techbank.presentation.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsState()

                LaunchedEffect(state.loginSuccess) {
                    if (state.loginSuccess) {
                        // Navigate to the new component selection screen
                        findNavController().navigate(R.id.action_loginFragment_to_componentSelectionFragment)
                        // IMPORTANT: Consume the event
                        viewModel.onIntent(LoginIntent.NavigationHandled)
                    }
                }
                
                LoginScreen(viewModel)
            }
        }
    }
}
