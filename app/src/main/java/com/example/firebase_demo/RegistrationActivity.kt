package com.example.firebase_demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        btnRegister = findViewById(R.id.registerbutton)

        //Connect to firebase
        btnRegister.setOnClickListener { view: View ->
            val useremail = email.text.toString()
            val userpassword = password.text.toString()
            if (useremail.isEmpty() || userpassword.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(useremail, userpassword)
            }
        }
    }

    private fun registerUser(useremail: String, userpassword: String) {
        //Create a firebase instance
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(useremail, userpassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //If the registration is successful, go to the login activity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.e("RegistrationActivity", "Registration failed", task.exception)
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}