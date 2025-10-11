package com.aqeel.shredpreff

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aqeel.shredpreff.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    companion object {
        private const val PREF_NAME = "AppPrefs"
        private const val KEY_USERNAME = "username"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE)

        // If not logged in, kick back to login
        if (!prefs.getBoolean(KEY_IS_LOGGED_IN, false)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        val username = prefs.getString(KEY_USERNAME, "User")
        binding.tvRecieved.text = "WELCOME $username!"

        binding.btnLogout.setOnClickListener {
            // Clear everything and mark logged out
            prefs.edit().clear().apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
