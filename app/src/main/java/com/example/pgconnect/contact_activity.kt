package com.example.pgconnect

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pgconnect.databinding.ActivityContactBinding
import com.example.pgconnect.databinding.ActivityLoginBinding

class contact_activity : AppCompatActivity() {

    private val binding by lazy {
        ActivityContactBinding.inflate(layoutInflater)
    }
    var settings: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = settings?.edit()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.emailshow.text= settings!!.getString("email","")
        binding.mobilenoshow.text=settings!!.getString("mobileno","")

    }
}