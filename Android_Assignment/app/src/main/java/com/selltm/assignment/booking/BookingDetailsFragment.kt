package com.selltm.assignment.booking


import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.selltm.assignment.R
import com.selltm.assignment.activities.MainActivity
import com.selltm.assignment.pojos.User
import kotlinx.android.synthetic.main.booking_details_selected_car_card.*

/**
 * Created by Asim on 22/06/18.
 */
class BookingDetailsFragment : Fragment() {
    private val TAG = javaClass.simpleName

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

    companion object {
        fun getInstance(): BookingDetailsFragment {
            return BookingDetailsFragment()
        }
    }
}
