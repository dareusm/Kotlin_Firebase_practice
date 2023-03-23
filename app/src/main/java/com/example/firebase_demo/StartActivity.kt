package com.example.firebase_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.FirebaseDatabase

class StartActivity : AppCompatActivity() {

    //Create private button variables
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btnLogin = findViewById(R.id.login)
        btnRegister = findViewById(R.id.register)

        //Set on click listeners for the buttons
        btnRegister.setOnClickListener {
            //Create an intent to go to the registration activity
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener {
            //Create an intent to go to the login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        /*val map = HashMap<String, Any>()
        map.put("Name", "Craig Creek")
        map.put("Email", "ccreek@aol.com")*/

        val languages = listOf("Java", "Kotlin", "Python", "JavaScript", "C++", "Swift")

        FirebaseDatabase.getInstance().getReference().child("Languages").setValue(languages)
    }
}