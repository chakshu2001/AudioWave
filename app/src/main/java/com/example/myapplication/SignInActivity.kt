package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {
    lateinit var databse:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val etUserName=findViewById<TextInputEditText>(R.id.userName_SignIn)
        val etPassword=findViewById<TextInputEditText>(R.id.password_SignIn)
        val button=findViewById<Button>(R.id.btnLoginIn)

        button.setOnClickListener {
            val userName=etUserName.text.toString()
            val password=etPassword.text.toString()
            val user=LogIN(userName,password)

            if(password.isNotEmpty() && userName.isNotEmpty()){
             readData(user)
            }

        }

    }

    private fun readData(user: LogIN) {
       databse=FirebaseDatabase.getInstance().getReference("Users")
       databse.child(user.userName).get().addOnSuccessListener {
        if(it.exists()){
            if(it.child("password").value.toString()==user.password){
                val intent=Intent(this, MusicPlayer::class.java)
                startActivity(intent)
                finish()
            }
            else{
                if(user.password.isNotEmpty()){
                    Toast.makeText(this,"Enter your correct Password",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Fill the Box of Password",Toast.LENGTH_SHORT).show()
                }
            }

          }
           else{
               Toast.makeText(this,"user doesn't exist",Toast.LENGTH_SHORT).show()
        }
       }.addOnFailureListener {
           Toast.makeText(this,"Error in Database",Toast.LENGTH_SHORT).show()
       }

    }
}