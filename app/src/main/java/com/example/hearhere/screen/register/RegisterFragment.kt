package com.example.hearhere.screen.register

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
import androidx.navigation.findNavController
import com.example.hearhere.R
import com.example.hearhere.databinding.FragmentRegisterBinding
import com.example.hearhere.models.RegisterModel


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val registerViewModel: RegisterViewModel by lazy {
        // to create object
        ViewModelProvider(this)[RegisterViewModel::class.java]

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        goToLogin()
        showPassword()
        clickOnButtonRegister()
    }
    private fun clickOnButtonRegister(){
       binding.registerButton.setOnClickListener {
           if(binding.edtFullName.text.toString().isNotEmpty() &&
               binding.edtEmailAddress.text.toString().isNotEmpty()&&
               binding.edtPassword.text.toString().isNotEmpty()){
               register(
                   binding.edtFullName.text.toString(),
                   binding.edtEmailAddress.text.toString(),
                   binding.edtPassword.text.toString()
               )
           }else{
               Toast.makeText(requireContext(),"Please Fill All Required Fields",
                   Toast.LENGTH_SHORT).show()
           }
       }
    }
    private fun register(name:String , email:String , password:String){
        registerViewModel.register(
            RegisterModel(
                name = name,
                email = email,
                password = password
            )
        )
        registerViewModel.getStatus().observe(viewLifecycleOwner){
            status ->
            if (status == "success") {
                binding.txErrorMessageRegister.visibility = View.GONE
                Toast.makeText(requireContext(), "Registration successful!", Toast.LENGTH_SHORT).show()
            } else {
                observeErrorMessage()
            }
        }
        observeLoading()
    }
    private fun goToLogin(){
        binding.loginLink.setOnClickListener {
            it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            it.findNavController().popBackStack()
            it.findNavController().popBackStack()
        }
    }
    private fun showPassword(){
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
        registerViewModel.getLoading().observe(viewLifecycleOwner) {
                isActive ->
            if(isActive){
                binding.progressBarRegister.isEnabled = isActive
                binding.progressBarRegister.visibility = View.VISIBLE
            }else{
                binding.progressBarRegister.isEnabled = isActive

                binding.progressBarRegister.visibility = View.GONE
            }
        }
    }
    private fun observeErrorMessage() {
        registerViewModel.getErrorMessage().observe(viewLifecycleOwner) { error ->
            Log.d("RegisterFragment", "Error message: $error") // Updated tag for consistency
            if (error.isNotEmpty()) {
                binding.txErrorMessageRegister.visibility = View.VISIBLE
                binding.txErrorMessageRegister.text = error
            } else {
                binding.txErrorMessageRegister.visibility = View.GONE
            }
        }
    }


}