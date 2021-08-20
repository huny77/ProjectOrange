package com.keelim.orange.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.keelim.orange.R
import com.keelim.orange.databinding.ActivityAuthBinding
import com.keelim.orange.ui.auth.login.LoginViewModel
import com.keelim.orange.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
  private val binding by lazy { ActivityAuthBinding.inflate(layoutInflater) }
  private val loginViewModel by viewModels<LoginViewModel>()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
  }

  private fun navController() = findNavController(R.id.nav_host_fragment_activity_auth)

  private fun tokenCheck(){
    val pref = getSharedPreferences("token", MODE_PRIVATE)
    val token = pref.getString("token", "")

    if(token != ""){
      startActivity(Intent(this, MainActivity::class.java))
    }
  }
}
