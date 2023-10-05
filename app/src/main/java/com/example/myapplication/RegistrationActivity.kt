package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {
    lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
         val etName=findViewById<TextInputEditText>(R.id.name)
        val etMail=findViewById<TextInputEditText>(R.id.Email)
        val etUserName=findViewById<TextInputEditText>(R.id.userName)
        val etpassword=findViewById<TextInputEditText>(R.id.password)
        val btn=findViewById<Button>(R.id.btnSignUp)

        btn.setOnClickListener {
            val name=etName.text.toString()
            val mail=etMail.text.toString()
            val userName=etUserName.text.toString()
            val password=etpassword.text.toString()
            val user=User(name, mail, userName, password)

            database=FirebaseDatabase.getInstance().getReference("Users")
            database.child(userName).setValue(user).addOnSuccessListener {
                etName.text?.clear()
                etMail.text?.clear()
                etUserName.text?.clear()
                etpassword.text?.clear()

                Toast.makeText(this,"User has been Registered",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }

        }
        var text=findViewById<TextView>(R.id.signIn)
        text.setOnClickListener {
            val openSignIn=Intent(this, SignInActivity::class.java)
            startActivity(openSignIn)
        }



    }
}