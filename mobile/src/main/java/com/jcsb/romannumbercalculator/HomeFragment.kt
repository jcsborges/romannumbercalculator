package com.jcsb.romannumbercalculator

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class HomeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)


        val btnStart = view.findViewById<Button>(R.id.btnStart)

        btnStart.setOnClickListener {
            activity!!.drawer_layout.openDrawer(GravityCompat.START)
        }




        return view
    }


}
