package com.silali.blueprint.dashboard

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.silali.blueprint.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.silali.blueprint.adapters.EntryAdapter
import com.silali.blueprint.entries.EntriesController

private const val ARG_PARAM = "entry_type"

class RecentEntriesFragment : Fragment() {
    private var entryType: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            entryType = it.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recyclerView = inflater.inflate(R.layout.fragment_recent_entries, container, false) as RecyclerView

        val entries = EntriesController().fetchEntries(entryType).take(4)

        val entriesList = recyclerView!!.findViewById<RecyclerView>(R.id.list_fragment_dash_recent_entries)

        val entriesAdapter = EntryAdapter(entries)

        entriesList.adapter = entriesAdapter

        entriesList.layoutManager = LinearLayoutManager(context)

        return view
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecentEntriesFragment().apply {

            }
    }
}
