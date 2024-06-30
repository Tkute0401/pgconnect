package com.example.pgconnect

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.pgconnect.Models.Users
import com.example.pgconnect.databinding.ActivitySignupBinding
import com.example.pgconnect.utils.USER_NODE
import com.example.pgconnect.utils.USER_PROFILE_FOLDER
import com.example.pgconnect.utils.uploadimage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val binding by lazy {
            ActivitySignupBinding.inflate(layoutInflater)
        }
        lateinit var user: Users
        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
                uri->
            uri?.let {
                uploadimage(uri, USER_PROFILE_FOLDER){
                    if (it!=null){
                        user.image=it
                        binding.profileImage.setImageURI(uri)
                    }
                }
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val text = "<font color=#000000>Already have an account</font> <font color=#1E88E5>Login ?</font>"
        binding.login.setText(Html.fromHtml(text))
        user= Users()
        if (intent.hasExtra("MODE")){
            if (intent.getIntExtra("MODE",-1)==1){
                binding.signup.text="Update Profile"
                Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {

                    user=it.toObject<Users>()!!
                    if (!user.image.isNullOrEmpty())
                    {
                        Picasso.get().load(user.image).into(binding.profileImage)
                    }
                    binding.name.setText(user.name)
                    binding.password.setText(user.password)
                    binding.email.setText(user.email)

                }
            }
        }
        binding.signup.setOnClickListener {

            if (intent.hasExtra("MODE")){
                if (intent.getIntExtra("MODE",-1)==1){

                    Firebase.firestore.collection(USER_NODE)
                        .document(Firebase.auth.currentUser!!.uid).set(user)
                        .addOnSuccessListener {
                            startActivity(
                                Intent(
                                    this@SignupActivity,
                                    HomeActivity::class.java))
                            finish()
                        }
                }
            }
            else {
                if (binding.name?.text.toString().equals("") or binding.email?.text.toString()
                        .equals("") or binding.password?.text.toString().equals("")
                ) {
                    Toast.makeText(this, "Please fill the above information", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.email.text.toString(), binding.password.toString()
                    ).addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            user.name = binding.name?.text.toString()
                            user.email = binding.email?.text.toString()
                            user.password = binding.password?.text.toString()
                            Firebase.firestore.collection(USER_NODE)
                                .document(Firebase.auth.currentUser!!.uid).set(user)
                                .addOnSuccessListener {
                                    startActivity(
                                        Intent(
                                            this@SignupActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                        } else {
                            Toast.makeText(
                                this@SignupActivity,
                                result.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
        binding.plusImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.login.setOnClickListener {
            startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
            finish()
        }
    }
}