package com.silali.blueprint

import android.net.Uri
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.silali.blueprint.dashboard.DashboardFragment
import com.silali.blueprint.dashboard.OverviewGraphFragment
import com.silali.blueprint.entries.EntriesFragment
import com.silali.blueprint.labels.LabelsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(),
DashboardFragment.OnFragmentInteractionListener,
LabelsFragment.OnFragmentInteractionListener{
    override fun onFragmentInteraction(uri: Uri) {

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_dashboard -> {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                val fragment = DashboardFragment()
                fragmentTransaction.replace(R.id.fragment_container, fragment)
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                fragmentTransaction.commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_expenses -> {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                val fragment = EntriesFragment()

                val bundle = Bundle()
                bundle.putString("entry_type", "expenses")
                fragment.arguments = bundle

                fragmentTransaction.replace(R.id.fragment_container, fragment)
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                fragmentTransaction.commit()

                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_income -> {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                val fragment = EntriesFragment()

                val bundle = Bundle()
                bundle.putString("entry_type", "income")
                fragment.arguments = bundle

                fragmentTransaction.replace(R.id.fragment_container, fragment)
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                fragmentTransaction.commit()

                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_labels -> {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                val fragment = LabelsFragment()
                fragmentTransaction.replace(R.id.fragment_container, fragment)
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                fragmentTransaction.commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        val fragment = DashboardFragment()
//        fragmentTransaction.add(R.id.fragment_container, fragment)
//        fragmentTransaction.commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            navigation.selectedItemId = R.id.navigation_dashboard
        }
    }
}
