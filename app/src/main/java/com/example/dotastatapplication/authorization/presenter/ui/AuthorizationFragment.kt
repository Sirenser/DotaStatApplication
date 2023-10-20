package com.example.dotastatapplication.authorization.presenter.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dotastatapplication.R
import com.example.dotastatapplication.authorization.presenter.item.AccountSearchItem
import com.example.dotastatapplication.authorization.utils.ContentViewState
import com.example.dotastatapplication.databinding.AuthorizationFragmentBinding
import com.example.dotastatapplication.di.getAppComponent
import com.example.dotastatapplication.utils.BaseViewModelFactory
import com.example.dotastatapplication.utils.NavigationDestination
import com.example.dotastatapplication.utils.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AuthorizationFragment : Fragment(R.layout.authorization_fragment) {

    private val viewModel: AuthorizationViewModel by viewModels {
        BaseViewModelFactory {
            getAppComponent().authViewModel()
        }
    }

    private val binding: AuthorizationFragmentBinding by viewBinding()

    private val itemAdapter by lazy {
        GroupieAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAppComponent().inject(this)
        setContentByState()
        setDestinationByState()
        initView()
    }

    private fun initView() {
        with(binding) {
            accountListRecyclerView.adapter = itemAdapter
            authTextInput.addTextChangedListener { editableText ->
                if (!editableText.isNullOrBlank()) viewModel.fetchAccount(editableText.toString())
            }
            errorConnection.authButtonRefresh.setOnClickListener {
                viewModel.fetchAccount(authTextInput.text.toString())
            }
        }
    }

    private fun setContentByState() = lifecycleScope.launch {
        viewModel.accountInfoStateFlow.collectLatest(::handleContentState)
    }

    private fun setDestinationByState() = lifecycleScope.launch {
        viewModel.destinationStateFlow.collectLatest(::handleNextDestination)
    }

    private fun handleContentState(state: ContentViewState) {
        with(binding) {
            authLoadingBar.isVisible = state is ContentViewState.Loading
            accountListRecyclerView.isVisible = (state is ContentViewState.Success && state.data.isNotEmpty())
            errorConnection.root.isVisible = state is ContentViewState.FailureConnection
            errorView.root.isVisible = (state is ContentViewState.Success && state.data.isEmpty())

            if (state is ContentViewState.FailureConnection) {
                Snackbar.make(
                    root,
                    requireContext().resources.getText(R.string.toast_error_connection),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            if (state is ContentViewState.Success && state.data.isNotEmpty()) {
                val mappedData = state.data.map { accountInfoUI ->
                    AccountSearchItem(
                        accountInfoUI, viewModel::saveAccountId
                    )
                }
                itemAdapter.update(mappedData)
            }
        }
    }

    private fun handleNextDestination(destination: NavigationDestination) {
        if (destination == NavigationDestination.TO_ONBOARDING) {
            findNavController().navigate(R.id.action_authorizationFragment_to_onboardingFragment)
            viewModel.setDefaultDestination()
        }
        if (destination == NavigationDestination.TO_OVERVIEW) {
            //TODO навигация в овервью
            viewModel.setDefaultDestination()
        }
    }

}


