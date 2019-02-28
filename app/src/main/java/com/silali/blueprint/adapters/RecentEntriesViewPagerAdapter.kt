package com.silali.blueprint.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.silali.blueprint.R
import com.silali.blueprint.dashboard.RecentEntriesFragment
import com.silali.blueprint.entries.EntriesFragment

class RecentEntriesViewPagerAdapter(fragmentManager: FragmentManager)  : FragmentPagerAdapter(fragmentManager) {
    private var fragment : Fragment = RecentEntriesFragment()
//    private val fragmentManager = fragmentManager

    override fun getItem(position: Int): Fragment {
//        fragment = RecentEntriesFragment()
//        if (position == 0) {
//            fragment = RecentEntriesFragment()
//            val bundle = Bundle()
//            bundle.putString("entry_type", "income")
//            fragment.arguments = bundle
//
//        }  else if(position == 1) {
//            fragment = RecentEntriesFragment()
//            val bundle = Bundle()
//            bundle.putString("entry_type", "expense")
//            fragment.arguments = bundle
//        }

        return fragment
    }

    override fun getCount(): Int {
        return 1
    }
}