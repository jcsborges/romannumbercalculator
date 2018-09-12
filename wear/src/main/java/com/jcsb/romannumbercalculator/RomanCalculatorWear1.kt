package com.jcsb.romannumbercalculator

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class RomanCalculatorWear1 : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roman_calculator_wear1)

        // Enables Always-on
        setAmbientEnabled()
    }
}
