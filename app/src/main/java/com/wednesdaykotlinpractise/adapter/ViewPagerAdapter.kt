package com.wednesdaykotlinpractise.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wednesdaykotlinpractise.fragment.FavBankFragment
import com.wednesdaykotlinpractise.fragment.SearchFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    val tabs  = arrayOf("Search Bank", "Favourite Banks")
    override fun getCount(): Int {
        return tabs.size
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> SearchFragment()
            1 -> FavBankFragment()
            else -> SearchFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position]
    }
}