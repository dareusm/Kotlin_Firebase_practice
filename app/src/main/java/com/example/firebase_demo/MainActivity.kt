package com.example.firebase_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

private lateinit var signoutBtn : Button
private lateinit var name: EditText
private lateinit var add: Button
private lateinit var listView: ListView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signoutBtn = findViewById(R.id.signOutBtn)
        name = findViewById(R.id.name)
        add = findViewById(R.id.addBtn)
        listView = findViewById(R.id.languageList)

        //Sign out
        signoutBtn.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            finish()
        }

        //Add data to firebase real time database
        add.setOnClickListener{
            val name_txt = name.text.toString()
            if(name_txt.isEmpty()){
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }else{
                FirebaseDatabase.getInstance().getReference().child("Test").push().setValue(name_txt)
            }
        }

        //Create Array list
        val list = ArrayList<String>()

        //Create Array adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.setAdapter(adapter)

        //Create database reference
        val reference = FirebaseDatabase.getInstance().getReference("Languages")
        reference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (data in snapshot.children){
                    val value = data.getValue(String::class.java)
                    list.add(value!!)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })

    }
}