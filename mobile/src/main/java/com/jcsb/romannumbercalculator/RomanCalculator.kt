package com.jcsb.romannumbercalculator

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.jcsb.romannumber.RomanDivResult
import com.jcsb.romannumber.RomanNumber
import kotlinx.android.synthetic.main.fragment_roman_calculator.*
import com.jcsb.romannumber.exceptions.InvalidNumberToRomanException



class RomanCalculator : Fragment() {

    private var leftNum: String = ""
    private var leftRoman: RomanNumber? = null
    private var rightNum: String = ""
    private var rightRoman: RomanNumber? = null
    private var signal: String = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_roman_calculator, container, false)


        val btn0 = view.findViewById<Button>(R.id.btn0)

        btn0.setOnClickListener {

            addNumberToCalc("0")

        }

        val btn1 = view.findViewById<Button>(R.id.btn1)

        btn1.setOnClickListener {

            addNumberToCalc("1")

        }

        val btn2 = view.findViewById<Button>(R.id.btn2)

        btn2.setOnClickListener {

            addNumberToCalc("2")

        }

        val btn3 = view.findViewById<Button>(R.id.btn3)

        btn3.setOnClickListener {

            addNumberToCalc("3")

        }

        val btn4 = view.findViewById<Button>(R.id.btn4)

        btn4.setOnClickListener {

            addNumberToCalc("4")

        }

        val btn5 = view.findViewById<Button>(R.id.btn5)

        btn5.setOnClickListener {

            addNumberToCalc("5")

        }

        val btn6 = view.findViewById<Button>(R.id.btn6)

        btn6.setOnClickListener {

            addNumberToCalc("6")

        }

        val btn7 = view.findViewById<Button>(R.id.btn7)

        btn7.setOnClickListener {

            addNumberToCalc("7")

        }

        val btn8 = view.findViewById<Button>(R.id.btn8)

        btn8.setOnClickListener {

            addNumberToCalc("8")

        }

        val btn9 = view.findViewById<Button>(R.id.btn9)

        btn9.setOnClickListener {

            addNumberToCalc("9")

        }


        val btnDiv = view.findViewById<Button>(R.id.btnDiv)

        btnDiv.setOnClickListener {

            addSignal("/")

        }

        val btnMulti = view.findViewById<Button>(R.id.btnMulti)

        btnMulti.setOnClickListener {

            addSignal("*")

        }

        val btnMinus = view.findViewById<Button>(R.id.btnMinus)

        btnMinus.setOnClickListener {

            addSignal("-")

        }


        val btnPlus = view.findViewById<Button>(R.id.btnPlus)

        btnPlus.setOnClickListener {

            addSignal("+")

        }


        val btnEqual = view.findViewById<Button>(R.id.btnEqual)

        btnEqual.setOnClickListener {

            calc()

        }

        val btnAc = view.findViewById<Button>(R.id.btnAc)

        btnAc.setOnClickListener {

            clean()
            cleanTv()

        }




        return view
    }


    private fun addNumberToCalc(number: String) {
        val oldLeftNum = leftNum
        val oldRightNum = rightNum

        if (signal == "")
            leftNum += number
        else
            rightNum += number

        leftNum = leftNum.toInt().toString()

        try {
            leftRoman = RomanNumber(leftNum.toInt())
        } catch (e: InvalidNumberToRomanException) {
            Toast.makeText(this.context, e.message, Toast.LENGTH_SHORT).show()
            leftNum = oldLeftNum
        }

        if (rightNum != "") {
            rightNum = rightNum.toInt().toString()
            try {
                rightRoman = RomanNumber(rightNum.toInt())
            } catch (e: InvalidNumberToRomanException) {

                Toast.makeText(this.context, e.message, Toast.LENGTH_SHORT).show()
                rightNum = oldRightNum
            }
        }

        val resultNum = String.format("%s %s %s", leftNum, signal, rightNum)


        val resultRom = String.format("%s %s %s", leftRoman?.stringValue, signal, rightRoman?.stringValue
                ?: "")

        tvCalcMonitor.text = resultNum
        tvCalcMonitorTranslator.text = resultRom


    }

    private fun addSignal(sign: String) {


        signal = sign


    }

    private fun calc() {

        if (leftNum == "" || rightNum == "")
            return

        val rn1 = RomanNumber(leftNum.toInt())
        val rn2 = RomanNumber(rightNum.toInt())


        var result: RomanNumber? = null
        var resultDiv: RomanDivResult? = null
        var resultStr: String
        var resultRomanStr = ""

        when (signal) {
            "+" -> result = rn1 + rn2
            "-" -> result = rn1 - rn2
            "*" -> result = rn1 * rn2
            "/" -> resultDiv = rn1 / rn2
        }



        if (result != null) {
            resultStr = result.numericValue.toString()
            resultRomanStr = result.stringValue
        } else if (resultDiv != null) {

            resultStr = resultDiv.quotient!!.numericValue.toString()
            resultRomanStr = resultDiv.quotient!!.stringValue


            if (resultDiv.remainder!!.numericValue > 0) {
                resultStr += " sobra " + resultDiv.remainder!!.numericValue.toString()
                resultRomanStr += " sobra " + resultDiv.remainder!!.stringValue
            }


        } else
            resultStr = ""



        resultStr = String.format("%s %s %s = %s", leftNum, signal, rightNum, resultStr)
        resultRomanStr = String.format("%s %s %s = %s", leftRoman?.stringValue, signal, rightRoman?.stringValue, resultRomanStr)

        tvCalcMonitor.text = resultStr
        tvCalcMonitorTranslator.text = resultRomanStr

        clean()

    }

    private fun clean() {
        leftNum = ""
        leftRoman = null
        rightNum = ""
        rightRoman = null
        signal = ""

    }

    private fun cleanTv() {
        tvCalcMonitorTranslator.text = ""
        tvCalcMonitor.text = ""
    }

}
