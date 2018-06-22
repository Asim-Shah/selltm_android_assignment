package com.selltm.assignment.booking


import android.app.Fragment
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.selltm.assignment.R
import com.selltm.assignment.activities.MainActivity
import com.selltm.assignment.holiday.fragments.HolidayFragment
import com.selltm.assignment.pojos.User
import kotlinx.android.synthetic.main.booking_details_selected_car_card.*
import kotlinx.android.synthetic.main.fragment_booking_details.*
import kotlinx.android.synthetic.main.traveller_details_layout.*

/**
 * Created by Asim on 22/06/18.
 */
class BookingDetailsFragment : Fragment() {
    private val TAG = javaClass.simpleName

    private val onClickListener = View.OnClickListener {v ->
        when(v.id) {
            R.id.button_book -> { validateDataEntered() }
        }
    }

    private fun validateDataEntered() {
        if(TextUtils.isEmpty(first_name_ET.text) || TextUtils.isEmpty(last_name_ET.text) ||
                TextUtils.isEmpty(email_ET.text) || TextUtils.isEmpty(mobile_ET.text) ||
                TextUtils.isEmpty(pickup_address_ET.text) || TextUtils.isEmpty(pickup_pin_ET.text) ||
                TextUtils.isEmpty(drop_address_ET.text) || TextUtils.isEmpty(drop_pin_ET.text) ||
                TextUtils.isEmpty(no_of_person_ET.text)) {
            Toast.makeText(activity,getString(R.string.string_please_complete), Toast.LENGTH_SHORT).show()
        } else {
            displayHolidayFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setToolbar()
        (activity as MainActivity).setToolbarTitle(getString(R.string.string_booking_details))
        Log.e(TAG, "selected Cab--> ${User.getInstance().cabSelected}")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking_details, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        resolveFirstCard()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        button_book.setOnClickListener(onClickListener)
    }

    private fun resolveFirstCard() {
        val selectedCab = User.getInstance().cabSelected
        car_type_TV.text = selectedCab.carType
        cars_names_TV.text = selectedCab.carNames2
        pickup_date_time_TV.text = selectedCab.pickupDateAndTime
        fare_TV.text = selectedCab.fare.toString()
        total_seats_TV.text = getString(R.string.string_total_seats, selectedCab.totalSeats)
        total_bags_TV.text = getString(R.string.string_total_bags, (selectedCab.bigBags + selectedCab.smallBags))
    }

    private fun displayHolidayFragment() {
        (activity as MainActivity).setFragment(HolidayFragment.getInstance(),
                HolidayFragment::class.java.simpleName)
    }

    companion object {
        fun getInstance(): BookingDetailsFragment {
            return BookingDetailsFragment()
        }
    }
}
