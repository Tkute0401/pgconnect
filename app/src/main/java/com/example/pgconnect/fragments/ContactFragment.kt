package com.example.pgconnect.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pgconnect.databinding.FragmentContactBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ContactFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentContactBinding.inflate(inflater, container, false)

        var settings: SharedPreferences? = null
        settings = this.requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor? = settings?.edit()
        binding.emailshow.text= settings!!.getString("email","")
        binding.mobilenoshow.text=settings!!.getString("mobileno","")


        return binding.root
    }

}