package com.example.hearhere.screen.login

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.hearhere.R
import com.example.hearhere.databinding.FragmentLoginBinding
import com.example.hearhere.models.LoginModel
import com.example.hearhere.models.LoginResponse
import com.example.hearhere.repository.local.CachHelper

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by lazy {
        // to create object
        ViewModelProvider(this)[LoginViewModel::class.java]

    }

    //to use store data come from LoginViewModel and watch this data
    private var data: LoginResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        login()
        signUpLink()
        toggleVisibility()
        observeLoading()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun login(){
        binding.loginButton.setOnClickListener {
            if (binding.edtEmailAddress.text.isNotEmpty() && binding.edtPassword.text.isNotEmpty()){
                callLogin(LoginModel(
                    email = binding.edtEmailAddress.text.toString(),
                    password = binding.edtPassword.text.toString()
                ))
            }else{
                Toast.makeText(requireContext(),"Please Fill All Required Fields",
                    Toast.LENGTH_SHORT).show()
            }

            //    activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.VISIBLE
        }
    }

    private fun callLogin(loginModel: LoginModel) {
            loginViewModel.login(loginModel)
            getLoginData()
    }

    private fun getLoginData() {

        loginViewModel.loginData.observe(viewLifecycleOwner) { loginData ->
            //to add all data and use it and use it to send to adapter later
            if (loginData.token != null) {
                data = loginData
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                CachHelper.getInstance(requireContext()).saveToken(data!!.token.toString())
                Log.i(TAG, "Data of login from server: ${data!!.token.toString()}")

            } else {

                Toast.makeText(requireContext(), "Data is null", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun signUpLink(){
        binding.signUpLink.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
    private fun toggleVisibility(){
        binding.togglePasswordVisibility.setOnClickListener {
            togglePasswordVisibility(binding.togglePasswordVisibility)
        }
    }
    private fun togglePasswordVisibility(view: ImageView) {
        val currentTransformationMethod = binding.edtPassword.transformationMethod
        if (currentTransformationMethod == PasswordTransformationMethod.getInstance()) {
            binding.edtPassword.transformationMethod = null
            view.setImageResource(R.drawable.eyeon)
        } else {
            binding.edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            view.setImageResource(R.drawable.eyeoff)
        }
    }
    private fun observeLoading() {
        loginViewModel.getLoading().observe(viewLifecycleOwner) {
                isActive ->
            if(isActive){
                binding.progressBarLogin.isEnabled = isActive
                binding.progressBarLogin.visibility = View.VISIBLE
            }else{
                binding.progressBarLogin.isEnabled = isActive

                binding.progressBarLogin.visibility = View.GONE
            }
        }
    }

    private fun observeErrorMessage() {
        loginViewModel.getErrorMessage().observe(viewLifecycleOwner) { error ->
            Log.d("LoginFragment", "Error message: $error") // Add this line for debugging
            if (error.isNotEmpty()) {
                binding.txErrorMessage.visibility = View.VISIBLE
                binding.txErrorMessage.text = error
                binding.progressBarLogin.visibility = View.GONE
            } else {
                binding.txErrorMessage.visibility = View.GONE
            }
        }
    }


}
