package com.deep.techbank.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.deep.techbank.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComponentSelectionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComponentSelectionScreen(
                    onNavigate = { screenId ->
                        when (screenId) {
                            USERS_SCREEN_ID -> {
                                findNavController().navigate(R.id.action_componentSelectionFragment_to_usersFragment)
                            }
                            ACCOUNTS_SCREEN_ID -> {
                                findNavController().navigate(R.id.action_componentSelectionFragment_to_accountsFragment)
                            }
                            TRANSACTIONS_SCREEN_ID -> {
                                // TODO: Navigate to the Transactions screen
                            }
                        }
                    }
                )
            }
        }
    }
}
