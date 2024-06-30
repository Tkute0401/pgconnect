package com.example.pgconnect.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pgconnect.Models.Users
import com.example.pgconnect.R
import com.example.pgconnect.adapters.SearchAdapter
import com.example.pgconnect.databinding.FragmentSearchBinding
import com.example.pgconnect.utils.USER_NODE
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class SearchFragment : Fragment() {

    lateinit var binding:FragmentSearchBinding
    lateinit var adapter: SearchAdapter
    var userlist=ArrayList<Users>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchrv.layoutManager=LinearLayoutManager(requireContext())
        adapter= SearchAdapter(requireContext(),userlist)
        binding.searchrv.adapter=adapter

        Firebase.firestore.collection(USER_NODE).get().addOnSuccessListener {

            var templist=ArrayList<Users>()
            userlist.clear()
            for (i in it.documents){
                if (i.id.toString().equals(Firebase.auth.currentUser!!.uid.toString())){

                }
                else{
                    var user:Users=i.toObject<Users>()!!
                    templist.add(user)
                }
            }
            userlist.addAll(templist)
            adapter.notifyDataSetChanged()
        }

        binding.searchimg.setOnClickListener {
            var text=binding.searchView.text.toString()

            Firebase.firestore.collection(USER_NODE).whereEqualTo("name",text).get().addOnSuccessListener {

                var templist=ArrayList<Users>()
                userlist.clear()
                if (it.isEmpty){

                }
                else{
                    for (i in it.documents){
                        if (i.id.toString().equals(Firebase.auth.currentUser!!.uid.toString())){

                        }
                        else{
                            var user:Users=i.toObject<Users>()!!
                            templist.add(user)
                        }
                    }
                    userlist.addAll(templist)
                    adapter.notifyDataSetChanged()
                }
            }

        }

        return binding.root
    }

    companion object {

    }
}