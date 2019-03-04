package com.jcsb.romannumbercalculator

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import com.jcsb.romannumber.RomanNumber
import kotlinx.android.synthetic.main.fragment_roman_number_converter.*
import android.view.inputmethod.InputMethodManager



class RomanNumberConverter : Fragment() {

    private var translateDirection: Int = 0 // 0= romano para numeral, 1=numeral para romano


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_roman_number_converter, container, false)

        val edInput = view.findViewById<EditText>(R.id.edInput)

        edInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                translate()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })


        edInput.setOnFocusChangeListener {
            _, hasFocus ->


            if(!hasFocus)
                edInput.hideKeyboard()

        }


        val rg1 = view.findViewById<RadioGroup>(R.id.rg1)

        rg1.setOnCheckedChangeListener { _, checkedId -> checkChange(checkedId) }


        return view
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun translate() {

        val rn: RomanNumber


        if (this.translateDirection == 0) {
            rn = RomanNumber(edInput.text.toString())

            tvOut.text = rn.numericValue.toString()
        } else {

            var num = -1

            try {
                num = edInput.text.toString().toInt()
            } catch (ex: Exception) {
            }

            if (num > 0) {
                rn = RomanNumber(num)

                tvOut.text = rn.stringValue
            }

        }


    }

    private fun checkChange(checkedId: Int) {

        this.edInput.text.clear()


        if (checkedId == R.id.rb1) {

            this.translateDirection = 0

            this.edInput.inputType = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS


        } else {
            this.translateDirection = 1

            this.edInput.inputType = InputType.TYPE_CLASS_NUMBER
        }

        this.tvOut.text = ""
    }




}
