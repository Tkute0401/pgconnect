package com.example.pgconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pgconnect.Models.Users
import com.example.pgconnect.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.login.setOnClickListener {
            if (binding.email.text.toString().equals("") or
                binding.password.text.toString().equals("") )
            {
                Toast.makeText(this@LoginActivity, "Please fill all the details", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var user=Users(binding.email.text.toString(),binding.password.text.toString())
                Firebase.auth.signInWithEmailAndPassword(user.email!!,user.password!!)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                            finish()
                        }
                        else {
                            Toast.makeText(this@LoginActivity, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        binding.createacc.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignupActivity::class.java))
        }
    }
}