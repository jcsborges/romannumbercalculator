package com.jcsb.romannumbercalculator

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.widget.EditText
import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import java.lang.reflect.Type
import java.util.*
import kotlin.reflect.KClass


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, RomanNumberConverter.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)




        this.changeFragment(HomeFragment::class.java, null, "Home")

    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_translate -> {

                this.changeFragment(RomanNumberConverter::class.java, item)


            }
            R.id.nav_calculator -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun <T : Any> changeFragment(fragmentClass: Class<T>, item: MenuItem? = null, newTitle: String? = null) {

        val fragmentManager = this.supportFragmentManager
        val fragment = fragmentClass.newInstance() as Fragment

        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit()

        if (item != null) {

            nav_view.setCheckedItem(item.itemId)

            title = item.title

        } else if (newTitle != null)
            title = newTitle
    }

}
