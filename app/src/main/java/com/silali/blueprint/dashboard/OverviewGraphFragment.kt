package com.silali.blueprint.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silali.blueprint.uicomponents.RoundedBottomSheetDialogFragment
import android.widget.TextView
import com.silali.blueprint.R


class OverviewGraphFragment : RoundedBottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_overview_graph, container, false)

        return view
    }

}