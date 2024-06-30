package com.example.pgconnect.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pgconnect.Models.Users
import com.example.pgconnect.SignupActivity
import com.example.pgconnect.adapters.ViewPagerAdapter
import com.example.pgconnect.databinding.FragmentProfileBinding
import com.example.pgconnect.utils.USER_NODE
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    private lateinit var binding :FragmentProfileBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.editProfile.setOnClickListener {
            var intent:Intent
            intent=Intent(activity,SignupActivity::class.java)
            intent.putExtra("MODE",1)
            activity?.startActivity(intent)
            activity?.finish()
        }

        viewPagerAdapter= ViewPagerAdapter(requireActivity().supportFragmentManager)
        viewPagerAdapter.addfragments(MyPostFragment(),"My Post")
        viewPagerAdapter.addfragments(WishlistFragment(),"Wishlist")
        binding.viewpager.adapter=viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewpager)
        return binding.root
    }

    companion object {

    }

    override fun onStart() {
        super.onStart()

        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {

                val user:Users=it.toObject<Users>()!!
                binding.name.text=user.name
                binding.bio.text=user.email
            if (!user.image.isNullOrEmpty())
            {
                Picasso.get().load(user.image).into(binding.profileImage)
            }

            }
    }
}