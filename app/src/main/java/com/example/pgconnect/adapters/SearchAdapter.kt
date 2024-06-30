package com.example.pgconnect.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pgconnect.Models.Users
import com.example.pgconnect.R
import com.example.pgconnect.databinding.SearchrvBinding

class SearchAdapter(var context: Context,var userlist: ArrayList<Users>):RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(var binding:SearchrvBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding =SearchrvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(userlist.get(position).image).placeholder(R.drawable.user).into(holder.binding.searchImgvw)
        holder.binding.textView4.text=userlist.get(position).name
    }
}