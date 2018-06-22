package com.selltm.assignment.cabs.utils

import android.app.TimePickerDialog
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
class MyTimePicker {
    private var context: Context? = null
    private var textView: TextView? = null
    var selectedTime = Calendar.getInstance()
    private var timeLinearLayout: LinearLayout? = null

    private val timeSetListener: TimePickerDialog.OnTimeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        context?.let {
            if(!isPastTime(calendar)) {
                selectedTime = calendar
                updateDisplay()
            } else {
                Toast.makeText(context, context?.getString(R.string.string_past_date_time), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isPastTime(calendar: Calendar): Boolean {
        val currCalendar = Calendar.getInstance(TimeZone.getDefault())
        var isPast = false
        if(!(calendar.get(Calendar.HOUR_OF_DAY) >= currCalendar.get(Calendar.HOUR_OF_DAY) &&
                calendar.get(Calendar.MINUTE) >= currCalendar.get(Calendar.MINUTE))) {
            isPast = true
        }
        return isPast
    }

    private val onClickListener = View.OnClickListener {v ->
        when(v.id) {
            timeLinearLayout?.id -> {
                val calendar = Calendar.getInstance(TimeZone.getDefault())

                val dialog = TimePickerDialog(context, timeSetListener,
                        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)
                dialog.show()
            }
        }

    }

    private fun updateDisplay() {
        val sdf = SimpleDateFormat("HH:mm")
        textView?.text = sdf.format(selectedTime.time)
    }

    fun setClickListeners(timeLinearLayout: LinearLayout) {
        this.timeLinearLayout = timeLinearLayout
        this.timeLinearLayout?.setOnClickListener(onClickListener)
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        timeSetListener.onTimeSet(null, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))

    }

    companion object {
        fun getInstance(context: Context, textView: TextView) : MyTimePicker {
            val myTimePicker = MyTimePicker()
            myTimePicker.context = context
            myTimePicker.textView = textView
            return myTimePicker
        }
    }
}