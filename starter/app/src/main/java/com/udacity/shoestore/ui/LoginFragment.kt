package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false);
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.apply {
            newAccountButton.setOnClickListener {
                if (binding.editTextTextEmailAddress.text.toString().isEmpty()) {
                    binding.editTextTextEmailAddress.error = "email required"
                }
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                        binding.editTextTextEmailAddress.text.toString()
                    )
                )
            }

            loginAccountButton.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToShoeListFragment())
            }
        }


    }
}