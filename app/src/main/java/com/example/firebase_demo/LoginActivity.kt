package com.example.firebase_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        btnLogin = findViewById(R.id.loginbtn)
        btnRegister = findViewById(R.id.registerbtn)

        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener{
            val userEmail = email.text.toString()
            val userPassword = password.text.toString()
            if(userEmail.isEmpty() || userPassword.isEmpty()){
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(userEmail, userPassword)
            }
        }
    }

    private fun loginUser(userEmail: String, userPassword: String){
        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) {task ->
                if(task.isSuccessful && auth.currentUser != null){
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }
}