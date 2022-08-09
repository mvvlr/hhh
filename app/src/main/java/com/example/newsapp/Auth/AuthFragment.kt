package com.example.newsapp.Auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentAuthBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private lateinit var callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private val mAuth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        phoneAuthProvaider()
        binding.btnGetCode.setOnClickListener {
            if (binding.etPhone.text.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Поле номера пустая, пж напиште номер",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val phone = binding.etPhone.text.toString()
                requestSMS(phone)
            }
        }
    }

    private fun requestSMS(phone: String) {
        val options = PhoneAuthOptions.newBuilder(mAuth).setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS).setActivity(requireActivity()).setCallbacks(callback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun phoneAuthProvaider() {
        callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInByCredential(credential)
            }

            override fun onVerificationFailed(p0: FirebaseException) {

            }

            override fun onCodeSent(s: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(s, token)
                binding.btnContinue.setOnClickListener {
                    verifyCode(s)

            }


        }

            private fun verifyCode(code: String) {

                val credential= PhoneAuthProvider.getCredential(code, binding.etSms.text.toString())
                signInByCredential(credential)
            }

        }




}

    private fun signInByCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(
                    requireContext(),
                    "Ошибка авторизации ${it.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        super.onStart()
        if (Firebase.auth.currentUser!=null){
            findNavController().navigateUp()
        }
    }

    }


