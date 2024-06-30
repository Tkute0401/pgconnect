package com.example.pgconnect.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentViewHolder

class ViewPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val fragmentslist= mutableListOf<Fragment>()
    val titlelist= mutableListOf<String>()

    override fun getCount(): Int {
        return fragmentslist.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentslist.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titlelist.get(position)
    }

    fun addfragments(fragment: Fragment,title:String){
        fragmentslist.add(fragment)
        titlelist.add(title)
    }
}