package com.aqeel.shredpreff

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aqeel.shredpreff.databinding.ActivitySignUpBinding
import com.google.firebase.firestore.FirebaseFirestore


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private lateinit var db: FirebaseFirestore



    companion object {
        private const val PREF_NAME = "AppPrefs"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= FirebaseFirestore.getInstance()
        val prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE)

        binding.btnSignUp.setOnClickListener {

            val username = binding.etUsername.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val user=userModel(username,email,password)

            db.collection("user")
                .add(user)
                .addOnSuccessListener{
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
                .addOnFailureListener{
                    Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                }




           /* prefs.edit()
                .putString(KEY_USERNAME, username)
                .putString(KEY_EMAIL, email)
                .putString(KEY_PASSWORD, password)
                .putBoolean(KEY_IS_LOGGED_IN, false) // user must login after sign up
                .apply()

            Toast.makeText(this, "Sign Up Successful! Please login.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()*/
        }
    }
}
