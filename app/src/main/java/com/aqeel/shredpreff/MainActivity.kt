package com.aqeel.shredpreff

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aqeel.shredpreff.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val PREF_NAME = "AppPrefs"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE)

        val isLogeIn =prefs.getBoolean(KEY_IS_LOGGED_IN, false)

        // Auto-navigate if already logged in
        if (isLogeIn) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            return
        }

        binding.btnSignIn.setOnClickListener {
            val inputEmail = binding.etEmail.text.toString().trim()
            val inputPassword = binding.etPassword.text.toString().trim()

            val savedEmail = prefs.getString(KEY_EMAIL, null)
            val savedPassword = prefs.getString(KEY_PASSWORD, null)

            if (inputEmail == savedEmail && inputPassword == savedPassword) {
                prefs.edit().putBoolean(KEY_IS_LOGGED_IN, true).apply()
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvsignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
