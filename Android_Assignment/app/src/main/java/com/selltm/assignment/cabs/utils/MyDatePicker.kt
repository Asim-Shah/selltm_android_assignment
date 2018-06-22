package com.selltm.assignment.cabs.utils

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.selltm.assignment.R
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Asim on 22/06/18.
 */
class MyDatePicker {
    private var context: Context? = null
    private var textView1: TextView? = null
    private var textView2: TextView? = null
    var selectedDate = Calendar.getInstance()
    private var dateLinearLayout: LinearLayout? = null

    private val dateSetListener: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        context?.let {
            if(!isPastDate(calendar)) {
                selectedDate = calendar
                updateDisplay()
            } else {
                Toast.makeText(context, context?.getString(R.string.string_past_date_time), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isPastDate(calendar: Calendar): Boolean {
        val currCalendar = Calendar.getInstance(TimeZone.getDefault())
        var isPast = false
        if(!(calendar.get(Calendar.YEAR) >= currCalendar.get(Calendar.YEAR) &&
                calendar.get(Calendar.MONTH) >= currCalendar.get(Calendar.MONTH) &&
                calendar.get(Calendar.DAY_OF_MONTH) >= currCalendar.get(Calendar.DAY_OF_MONTH))) {
            isPast = true
        }
        return isPast
    }

    private val onClickListener = View.OnClickListener {v ->
        when(v.id) {
            dateLinearLayout?.id -> {
                val calendar = Calendar.getInstance(TimeZone.getDefault())
                val dialog = DatePickerDialog(context, dateSetListener,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH))
                dialog.show()
            }
        }

    }

    private fun updateDisplay() {
        var sdf = SimpleDateFormat("dd MMM yy")
        textView1?.text = sdf.format(selectedDate.time)
        sdf = SimpleDateFormat("EEE")
        textView2?.text = sdf.format(selectedDate.time)
    }

    fun setClickListeners(dateLinearLayout: LinearLayout) {
        this.dateLinearLayout = dateLinearLayout
        this.dateLinearLayout?.setOnClickListener(onClickListener)
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        dateSetListener.onDateSet(null, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))

    }

    companion object {
        fun getInstance(context: Context, textView1: TextView, textView2: TextView) : MyDatePicker {
            val myDatePicker = MyDatePicker()
            myDatePicker.context = context
            myDatePicker.textView1 = textView1
            myDatePicker.textView2 = textView2
            return myDatePicker
        }
    }
}