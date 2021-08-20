package com.keelim.orange.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.keelim.orange.R
import com.keelim.orange.common.toast
import com.keelim.orange.databinding.FragmentLoginBinding
import com.keelim.orange.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
  private var _binding: FragmentLoginBinding? = null
  private val binding get() = _binding!!
  private val loginViewModel: LoginViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentLoginBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews()
    observeData()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun initViews() = with(binding) {
    val afterTextChangedListener = object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
      }

      override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
      }

      override fun afterTextChanged(s: Editable) {
        loginViewModel.loginDataChanged(
          username.text.toString(),
          password.text.toString()
        )
      }
    }
    username.addTextChangedListener(afterTextChangedListener)
    password.addTextChangedListener(afterTextChangedListener)
    password.setOnEditorActionListener { _, actionId, _ ->
      if (actionId == EditorInfo.IME_ACTION_DONE) {
        loginViewModel.login(
          username.text.toString(),
          password.text.toString()
        )
      }
      false
    }

    login.setOnClickListener {
      loginViewModel.login(
        username.text.toString(),
        password.text.toString()
      )
    }

    btnSignup.setOnClickListener {
      findNavController().navigate(R.id.signUpFragment)
    }
  }

  private fun observeData() {
    loginViewModel.loginFormState.observe(viewLifecycleOwner) { loginFormState ->
      if (loginFormState == null) {
        return@observe
      }
      loginFormState.usernameError?.let {
//        binding.username.error = getString(it)
      }
      loginFormState.passwordError?.let {
//        binding.password.error = getString(it)
      }
    }

    loginViewModel.loginResult.observe(viewLifecycleOwner) { loginResult ->

      loginResult ?: return@observe
      loginResult.error?.let {
        requireContext().toast(it)
      }
      loginResult.success?.let {
        val pref = requireActivity().getSharedPreferences("token", AppCompatActivity.MODE_PRIVATE)
        val token = pref.getString("token", "")
        if (token == "") {
          val editor = pref.edit().apply {
            putString("token", loginResult.token)
            apply()
          }
        }

        val pref2 = requireActivity().getSharedPreferences("userId", AppCompatActivity.MODE_PRIVATE)
        val userId = pref.getInt("userId", 19)
        if (userId == 19) {
          val editor = pref2.edit().apply {
            putInt("userId", loginResult.uid!!)
            apply()
          }
        }
        updateUiWithUser(it)
      }
    }
  }

  private fun updateUiWithUser(model: LoggedInUserView) {
    val welcome = getString(R.string.welcome) + model.displayName
    requireActivity().toast(welcome)
    requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))
    requireActivity().finish()
  }
}
