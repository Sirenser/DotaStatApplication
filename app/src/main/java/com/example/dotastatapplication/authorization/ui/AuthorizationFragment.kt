package com.example.dotastatapplication.authorization.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dotastatapplication.databinding.AuthorizationFragmentBinding

class AuthorizationFragment : Fragment() {


    private lateinit var viewModel: AuthorizationViewModel

    private lateinit var binding: AuthorizationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = AuthorizationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


}