package com.silali.blueprint.entries

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

import com.silali.blueprint.R
import kotlinx.android.synthetic.main.fragment_entries.view.*


class EntriesFragment : androidx.fragment.app.Fragment() {
    private lateinit var entryType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        entryType = this.arguments!!.getString("entry_type")
        val view = inflater.inflate(R.layout.fragment_entries, container, false)
        val viewPager = view.viewpager
        val tabLayout = view.tabs

        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        return view
    }
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)

        val entriesListFragment = EntriesListFragment()
        val bundle = Bundle()
        bundle.putString("entry_type","income")
        entriesListFragment.arguments = bundle

        val entriesListFragment1 = EntriesListFragment()
        val bundle1 = Bundle()
        bundle1.putString("entry_type","expense")
        entriesListFragment1.arguments = bundle1

        adapter.addFragment(entriesListFragment, "Entries")
        adapter.addFragment(entriesListFragment1, "Entries 2")
        viewPager.adapter = adapter

    }

    inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }



}
