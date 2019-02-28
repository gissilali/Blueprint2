package com.silali.blueprint.dashboard

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.silali.blueprint.R
import com.silali.blueprint.adapters.EntryAdapter
import com.silali.blueprint.entries.EntriesController
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.google.android.material.tabs.TabLayout
import com.silali.blueprint.entries.EntriesListFragment
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import kotlinx.android.synthetic.main.fragment_entries.view.*
import org.jetbrains.anko.support.v4.toast
import java.lang.Exception
import java.util.*


class DashboardFragment : androidx.fragment.app.Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var entriesController : EntriesController
    private lateinit var entryType : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

//        val scrollView : ScrollView = view.scrollView
//        scrollView.isFillViewport = true

        entriesController = EntriesController()

        initializeChart(view)

//        initializeTabLayout(view)

        val viewPager = view.view_pager_fragment_dash_recent_list
        val tabLayout = view.tabs_fragment_dash_entry_filter

        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        viewPager.offscreenPageLimit = 2

        setupViewPager(viewPager)

//        initializeRecentList(view, "income")

        return view
    }

    private fun initializeRecentList(view: View?, entryType : String) {
        val entriesList = view!!.findViewById<RecyclerView>(R.id.list_fragment_dash_recent_entries)

//        val emptyStatus = view!!.findViewById<FrameLayout>(R.id.recent_empty)

        val entries = entriesController.fetchEntries(entryType).take(4)

//        if (entries.isEmpty()) {
//            emptyStatus.visibility = View.VISIBLE
//        } else {
//            emptyStatus.visibility = View.GONE
//        }


        val entriesAdapter = EntryAdapter(entries)


        entriesList.adapter = entriesAdapter

        entriesList.layoutManager = LinearLayoutManager(context)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener") as Throwable
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    private fun initializeChart(view : View) {
        val chartValues = entriesController.getExpenseOverView()
        val pieDataSet  = PieDataSet(chartValues, "")
        pieDataSet.valueColors
        pieDataSet.colors = listOf(
            Color.rgb(217, 80, 138), Color.rgb(254, 149, 7), Color.rgb(254, 247, 120),
            Color.rgb(106, 167, 134), Color.rgb(53, 194, 209))
        pieDataSet.selectionShift = 10f
        pieDataSet.valueTextSize = 16f
        pieDataSet.valueTextColor = Color.rgb(255, 255, 255)

        val data  = PieData(pieDataSet)

        val chart : PieChart = view.findViewById(R.id.pie_chart_fragment_dash_expense_overview)

        chart.setUsePercentValues(true)
        chart.dragDecelerationFrictionCoef = 0.95f
        chart.isDrawHoleEnabled = false
        chart.transparentCircleRadius = 61f
        chart.setDrawSliceText(false)


        val legend = chart.legend

        legend.position = Legend.LegendPosition.BELOW_CHART_CENTER

        chart.data = data
    }

    private fun initializeTabLayout(view: View) {
        val tabLayout = view!!.findViewById<TabLayout>(R.id.tabs_fragment_dash_entry_filter)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position) {
                    0 -> {
                        initializeRecentList(view, "income")
                    }

                    1 -> {
                        initializeRecentList(view, "expense")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
//                toast("reselected")
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
//                toast("reselected")
            }
        })
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)

        val entriesListFragment = EntriesListFragment()
        val bundle = Bundle()
        bundle.putString("entry_type","This is one")
        entriesListFragment.arguments = bundle

        val entriesListFragment1 = EntriesListFragment()
        val bundle1 = Bundle()
        bundle1.putString("entry_type","This is two")
        entriesListFragment1.arguments = bundle1

        adapter.addFragment(entriesListFragment, "Entries")
        adapter.addFragment(entriesListFragment1, "Entries 2")

        viewPager.adapter = adapter


    }

    inner class ViewPagerAdapter(manager: FragmentManager) : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
