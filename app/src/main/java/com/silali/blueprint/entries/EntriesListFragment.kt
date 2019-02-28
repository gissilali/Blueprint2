package com.silali.blueprint.entries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.silali.blueprint.R
import kotlinx.android.synthetic.main.fragment_entries_list.view.*
import org.jetbrains.anko.support.v4.toast


class EntriesListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_entries_list, container, false)

        val bundle = this.arguments
         bundle?.let {
             val one = bundle.getString("entry_type")
             view.makende.text = one
         }



        return view
}

}