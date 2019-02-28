package com.silali.blueprint.entries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.silali.blueprint.R
import com.silali.blueprint.adapters.EntryAdapter
import kotlinx.android.synthetic.main.fragment_entries_list.view.*
import org.jetbrains.anko.support.v4.toast


class EntriesListFragment : Fragment() {
    private val entriesController = EntriesController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_entries_list, container, false)

        val bundle = this.arguments
         bundle?.let {
             val entryType = bundle.getString("entry_type")
             initializeRecentList(view, entryType)
         }



        return view
}

    private fun initializeRecentList(view: View?, entryType : String) {
        val entriesList = view!!.list_fragment_entries_recent_entries

        val entries = entriesController.fetchEntries(entryType).take(4)


        val entriesAdapter = EntryAdapter(entries)


        entriesList.adapter = entriesAdapter

        entriesList.layoutManager = LinearLayoutManager(context)
    }

}