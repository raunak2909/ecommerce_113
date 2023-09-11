package com.probot.ecommerce_wscubetech.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationBarView
import com.probot.ecommerce_wscubetech.Fragments.CartFragment
import com.probot.ecommerce_wscubetech.Fragments.CategoryFragment
import com.probot.ecommerce_wscubetech.Fragments.HomeFragment
import com.probot.ecommerce_wscubetech.Fragments.LikeFragment
import com.probot.ecommerce_wscubetech.Fragments.ProfileFragment
import com.probot.ecommerce_wscubetech.R
import com.probot.ecommerce_wscubetech.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var fm: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fm = supportFragmentManager

        // Setting default home fragment
        loadFrag(HomeFragment(),true)

        // For select the home fragment
        val menu = binding.bottomNavigationView.menu
        val middleItemId = R.id.home_menu
        menu.findItem(middleItemId)?.isChecked = true


        // Setting Bottom Navigation
        binding.bottomNavigationView.setOnItemSelectedListener(object: NavigationBarView.OnItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.home_menu -> loadFrag(HomeFragment(), false)
                R.id.like -> loadFrag(LikeFragment(), false)
                R.id.cart -> loadFrag(CartFragment(), false)
                R.id.profile -> loadFrag(ProfileFragment(), false)
                R.id.category -> loadFrag(CategoryFragment(), false)
            }
            return true
        }
    })

    }

    fun loadFrag(fragment: Fragment, flag:Boolean){
        val ft = fm.beginTransaction()
        if (flag){
            ft.add(R.id.container,fragment)
        }else{
            ft.replace(R.id.container,fragment)
        }
        ft.commit()
    }
}