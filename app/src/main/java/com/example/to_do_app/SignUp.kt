package com.example.to_do_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val regbtn:Button=findViewById(R.id.reg_button)
        regbtn.setOnClickListener{
            performSignUp();
        }
        val signin:TextView=findViewById(R.id.reg_signin)
        signin.setOnClickListener {
            val intent=Intent(this, SignIn::class.java);
            startActivity(intent)
        }
    }

    private fun performSignUp() {
        val email = findViewById<EditText>(R.id.reg_Email);
        val password = findViewById<EditText>(R.id.reg_Password);
        val before = findViewById<EditText>(R.id.reg_password1)

        if (email.text.isEmpty() || password.text.isEmpty() || password.text != before.text) {
            Toast.makeText(this, "Incorrect Credentials", Toast.LENGTH_SHORT).show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this, MainActivity::class.java);
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
        else{
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
        }
    }
}