package com.deep.techbank.presentation.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.deep.techbank.presentation.viewModel.UsersViewModel

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private val viewModel: UsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                // Explicitly pass the ViewModel instance
                UsersScreen(viewModel)
            }
        }
    }
}
