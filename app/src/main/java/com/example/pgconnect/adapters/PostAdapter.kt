package com.example.pgconnect.adapters

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pgconnect.Models.Post
import com.example.pgconnect.Models.Users
import com.example.pgconnect.R
import com.example.pgconnect.contact_activity
import com.example.pgconnect.databinding.PostRvBinding
import com.example.pgconnect.fragments.ContactFragment
import com.example.pgconnect.utils.SAVED
import com.example.pgconnect.utils.USER_NODE
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import kotlin.random.Random

class PostAdapter(var context: Context,var postlist:ArrayList<Post>) :RecyclerView.Adapter<PostAdapter.MyHolder>(){

    inner class MyHolder(var binding : PostRvBinding):RecyclerView.ViewHolder(binding.root)

    var settings: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = settings?.edit()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var binding=PostRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
       return postlist.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        try {
            Firebase.firestore.collection(USER_NODE).document(postlist.get(position).uid).get().addOnSuccessListener {
                var user=it.toObject<Users>()
                Glide.with(context).load(user!!.image).placeholder(R.drawable.user).into(holder.binding.profileImage)
                holder.binding.name.text=user.name

            }
        }
        catch (e:Exception)
        {

        }
        Glide.with(context).load(postlist.get(position).posturl).placeholder(R.drawable.loading).into(holder.binding.postImgHome)
        val text = TimeAgo.using(postlist.get(position).time.toLong())
        val post:Post= Post(holder.binding.profileImage.toString(),holder.binding.captionHome.toString(),
            holder.binding.Location.toString(),holder.binding.Rent.toString())
        holder.binding.time.text=text
        holder.binding.captionHome.text=postlist.get(position).caption
        holder.binding.Rent.text="Rent: "+postlist.get(position).rent
        holder.binding.Location.text="Location: "+postlist.get(position).location
            holder.binding.like.setOnClickListener {
                holder.binding.like.setImageResource(R.drawable.liked_like)}

                holder.binding.contactDetails.setOnClickListener {
//                    val intent  = Intent (context,contact_activity::class.java)
//                    context.startActivity(intent)
//                    settings = holder.itemView.context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
//                    editor!!.putString("email",Firebase.auth.currentUser?.email.toString())
//                    editor!!.putString("mobileno","97555461328")
//                    editor!!.apply()
                    val list = ArrayList<String>()
                    list.add("Admin@gmail.com")
                    list.add("cathy@gmail.com")
                    list.add("ian@gmail.com")
                    list.add("stefan@gmail.com")
//                    val random = Random()
//                    println(random.nextInt(list.size))
                    Toast.makeText(context, list.random().toString(), Toast.LENGTH_LONG).show()
                }
                holder.binding.saved.setOnClickListener{
                    holder.binding.saved.setImageResource(R.drawable.save_clicked)
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).firestore.collection(SAVED).document().set(post).
                    addOnSuccessListener {
                        Toast.makeText(context, "Post Saved", Toast.LENGTH_SHORT).show()
                    }
                        .addOnFailureListener {
                            Toast.makeText(context, "Save failed", Toast.LENGTH_SHORT).show()
                        }
                }
            }

    }
