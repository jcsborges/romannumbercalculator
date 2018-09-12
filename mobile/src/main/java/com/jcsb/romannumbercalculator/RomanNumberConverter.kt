package com.jcsb.romannumbercalculator

import android.content.Context
import android.net.Uri
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


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RomanNumberConverter.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RomanNumberConverter.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RomanNumberConverter : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private var translateDirection: Int = 0 // 0= romano para numeral, 1=numeral para romano

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_roman_number_converter, container, false)


        var edInput = view.findViewById<EditText>(R.id.edInput)

        edInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                translate()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        var rg1 = view.findViewById<RadioGroup>(R.id.rg1)

        rg1.setOnCheckedChangeListener { _, checkedId -> checkChange(checkedId) }

        return view
    }

    private fun translate() {

        var rn: RomanNumber


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

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RomanNumberConverter.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                RomanNumberConverter().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
