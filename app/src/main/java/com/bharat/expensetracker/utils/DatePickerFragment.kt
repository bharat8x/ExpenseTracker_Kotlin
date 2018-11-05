package com.bharat.expensetracker.utils

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import java.util.*


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var mListener: OnCompleteListener? = null

    // make sure the Activity implemented it
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            this.mListener = activity as OnCompleteListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement OnCompleteListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val picker = DatePickerDialog(
            activity!!,
            this, year, month, day
        )
        picker.datePicker.minDate = c.time.time

        // Create a new instance of DatePickerDialog and return it
        return picker
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {

        if (view.isShown()) {
            // Do something with the date chosen by the user
            val date = Integer.toString(year) + "-" + getMonth(month + 1) + "-" + Integer.toString(day)
            this.mListener?.onComplete(date)
        }
    }

    interface OnCompleteListener {
        fun onComplete(time: String)
    }

    fun getMonth(month: Int): String {

        when (month) {
            1 -> return "January"
            2 -> return "February"
            3 -> return "March"
            4 -> return "April"
            5 -> return "May"
            6 -> return "June"
            7 -> return "July"
            8 -> return "August"
            9 -> return "September"
            10 -> return "October"
            11 -> return "November"
            else -> return "December"
        }
    }
}