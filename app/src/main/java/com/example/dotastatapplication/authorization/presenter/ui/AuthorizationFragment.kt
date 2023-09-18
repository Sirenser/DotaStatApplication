package com.example.dotastatapplication.authorization.presenter.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.dotastatapplication.R
import com.example.dotastatapplication.databinding.AuthorizationFragmentBinding

class AuthorizationFragment : Fragment(R.layout.authorization_fragment) {


    private val viewModel: AuthorizationViewModel
            by viewModels(factoryProducer = ::AuthorizationViewModelFactory)

    private var binding: AuthorizationFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AuthorizationFragmentBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}


