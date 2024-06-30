package com.example.pgconnect.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pgconnect.Models.Post
import com.example.pgconnect.databinding.MyPostRvDesignBinding
import com.squareup.picasso.Picasso

class MyPostRvAdapter(var context: Context,var postlist:ArrayList<Post>):RecyclerView.Adapter<MyPostRvAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: MyPostRvDesignBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=MyPostRvDesignBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Picasso.get().load(postlist.get(position).posturl).into(holder.binding.postImgHome)

    }
}