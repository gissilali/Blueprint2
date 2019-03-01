package com.silali.blueprint

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.silali.blueprint.auth.LoginActivity
import com.silali.blueprint.dashboard.DashboardFragment
import com.silali.blueprint.entries.EntriesFragment
import com.silali.blueprint.labels.LabelsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

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
        FirebaseApp.initializeApp(this)
        try {
            val firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()
            val currentUser = firebaseAuth.currentUser
            if(currentUser == null) {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                toast(currentUser.displayName.toString())
            }
        } catch(e : Exception) {
            Log.d("Firebase  -----> ",e.localizedMessage)
            longToast(e.localizedMessage)
        }


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            navigation.selectedItemId = R.id.navigation_dashboard
        }
    }
}
