package com.example.to_do_app

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

class SignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Initialize Firebase Auth
        auth = Firebase.auth
        val signInbtn:Button=findViewById(R.id.login_button)
        signInbtn.setOnClickListener{
            performSignIn();
        }
        val signinreg:TextView=findViewById(R.id.reg)
        signinreg.setOnClickListener {
            val intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun performSignIn() {
        val email=findViewById<EditText>(R.id.login_email);
        val password=findViewById<EditText>(R.id.login_password);

        if(email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this,"Please fill the Credentials", Toast.LENGTH_SHORT).show()
            return
        }
        val userEmail=email.text.toString()
        val userPassword=password.text.toString()

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }
    }
}