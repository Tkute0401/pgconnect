package com.example.pgconnect.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pgconnect.Models.Post
import com.example.pgconnect.R
import com.example.pgconnect.adapters.PostAdapter
import com.example.pgconnect.databinding.FragmentHomeBinding
import com.example.pgconnect.utils.POST
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var postlist=ArrayList<Post>()
    private lateinit var adapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater, container, false)
        adapter= PostAdapter(requireContext(),postlist)
        binding.homerv.layoutManager=LinearLayoutManager(requireContext())
        binding.homerv.adapter=adapter
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbar2)

        Firebase.firestore.collection(POST).get().addOnSuccessListener {
            var templist=ArrayList<Post>()
            postlist.clear()
            for (i in it.documents){
                var post:Post=i.toObject<Post>()!!
                templist.add(post)
            }
            postlist.addAll(templist)
            adapter.notifyDataSetChanged()

        }

        return binding.root
    }

    companion object {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}