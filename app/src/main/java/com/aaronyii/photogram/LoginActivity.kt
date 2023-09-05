package com.aaronyii.photogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "LoginActivity"
class LoginActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null)
            goPostActivity()

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        btnLogin.setOnClickListener {
            btnLogin.isEnabled = false
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Email/password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // FireBase authentication check
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                btnLogin.isEnabled = true
                if (task.isSuccessful) {
                    Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                    goPostActivity()
                } else {
                    Log.e(TAG, "sign in with Email failed", task.exception)
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun goPostActivity() {
        Log.i(TAG, "goPostsActivity")
        val intent = Intent(this, PostsActivity::class.java)
        startActivity(intent)
        finish()
    }
}