package com.example.pgconnect.Post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.pgconnect.HomeActivity
import com.example.pgconnect.Models.Post
import com.example.pgconnect.Models.Users
import com.example.pgconnect.databinding.ActivityPostBinding
import com.example.pgconnect.utils.POST
import com.example.pgconnect.utils.POST_FOLDER
import com.example.pgconnect.utils.USER_NODE
import com.example.pgconnect.utils.USER_PROFILE_FOLDER
import com.example.pgconnect.utils.uploadimage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class PostActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    var imageurl:String?=null
    val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
            uri->
        uri?.let {
            uploadimage(uri, POST_FOLDER){
                url->
                if (url!=null){
                    binding.selectimage.setImageURI(uri)
                    imageurl=url
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@PostActivity,HomeActivity::class.java))
            finish()
        }
        binding.selectimage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.cancel.setOnClickListener {
            startActivity(Intent(this@PostActivity,HomeActivity::class.java))
            finish()
        }
        binding.post.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user=it.toObject<Users>()!!
                val post:Post=Post(imageurl!!,binding.caption.text.toString(),
                    binding.location.text.toString(),binding.rent.text.toString(),
                    binding.tenanttype.text.toString(),binding.roomtype.text.toString(),Firebase.auth.uid.toString(),System.currentTimeMillis().toString())


            Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post)
                    .addOnSuccessListener {
                        startActivity(Intent(this@PostActivity, HomeActivity::class.java))
                        finish()
                    }
            }
            }
        }
    }
}