package com.example.dotastatapplication.authorization.presenter.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.dotastatapplication.R
import com.example.dotastatapplication.authorization.presenter.item.AccountSearchItem
import com.example.dotastatapplication.authorization.utils.ContentViewState
import com.example.dotastatapplication.databinding.AuthorizationFragmentBinding
import com.example.dotastatapplication.di.getAppComponent
import com.example.dotastatapplication.utils.BaseViewModelFactory
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

    private val adapter by lazy {
        GroupieAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAppComponent().inject(this)
        initView()
    }

    private fun initView() {
        setContentByState()
        with(binding) {
            rvAccountList.adapter = adapter
            authTextInput.addTextChangedListener { editableText ->
                if (!editableText.isNullOrBlank()) viewModel.fetchAccount(editableText.toString())
            }
            errorConnection.authButtonRefresh.setOnClickListener {
                viewModel.fetchAccount(authTextInput.text.toString())
            }
        }
    }

    private fun setContentByState() {
        lifecycleScope.launch {
            viewModel.accountInfoStateFlow.collectLatest { state ->
                with(binding) {
                    authLoadingPb.isVisible =
                        state is ContentViewState.Loading
                    rvAccountList.isVisible =
                        (state is ContentViewState.Success && state.data.isNotEmpty())
                    errorConnection.root.isVisible =
                        state is ContentViewState.FailureConnection
                    errorView.root.isVisible =
                        (state is ContentViewState.Success && state.data.isEmpty())

                    if (state is ContentViewState.FailureConnection) {
                        Snackbar.make(
                            binding.root,
                            requireContext().resources.getText(R.string.toast_error_connection),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } else if (state is ContentViewState.Success && state.data.isNotEmpty()) {
                        adapter.clear()
                        state.data.forEach { accountInfoUI ->
                            adapter.add(AccountSearchItem(accountInfoUI))
                        }
                    }
                }
            }
        }
    }
}


