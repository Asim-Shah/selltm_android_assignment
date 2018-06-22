package com.selltm.assignment.cabs.fragments


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.Toast
import com.selltm.assignment.R
import com.selltm.assignment.activities.MainActivity
import com.selltm.assignment.cabs.adapters.ExpandableListAdapter
import com.selltm.assignment.cabs.utils.MyDatePicker
import com.selltm.assignment.cabs.utils.MyTimePicker
import com.selltm.assignment.pojos.User
import com.selltm.assignment.utils.Constants
import kotlinx.android.synthetic.main.fragment_cab_booking.*
import java.util.*

/**
 * Created by Asim on 22/06/18.
 */
//TODO: Implement logic for interchange
class CabBookingFragment : Fragment() {
    private val TAG = javaClass.simpleName
    private var pickupListHeader = ArrayList<String>()
    private var pickupListChild = HashMap<String, List<String>>()
    private var dropListHeader = ArrayList<String>()
    private var dropListChild = HashMap<String, List<String>>()
    private var pickupListAdapter: ExpandableListAdapter? = null
    private var dropListAdapter: ExpandableListAdapter? = null
    private var myDatePicker: MyDatePicker? = null
    private var myTimePicker: MyTimePicker? = null

    private val onChildClickListener: ExpandableListView.OnChildClickListener = ExpandableListView.OnChildClickListener { parent, v, groupPosition, childPosition, id ->
        parent.collapseGroup(groupPosition)
        when(parent.id) {
            R.id.pickup_city_expandable_listview -> {
                pickupListChild[pickupListHeader[groupPosition]]?.get(childPosition)?.let { pickupListHeader.add(it) }
                pickupListHeader.removeAt(groupPosition)
                if (!pickupListHeader.isEmpty()) {
                    pickupListChild[pickupListHeader[0]] = Constants.getDefaultCitiesList()
                }
                pickupListAdapter?.notifyDataSetChanged()
                User.getInstance().pickupCity = pickupListHeader[0]
                Toast.makeText(activity, "Pickup: ${pickupListHeader[0]} selected", Toast.LENGTH_SHORT).show()
            }

            R.id.drop_city_expandable_listview -> {
                dropListChild[dropListHeader[groupPosition]]?.get(childPosition)?.let { dropListHeader.add(it) }
                dropListHeader.removeAt(groupPosition)
                if (!dropListHeader.isEmpty()) {
                    dropListChild[dropListHeader[0]] = Constants.getDefaultCitiesList()
                }
                dropListAdapter?.notifyDataSetChanged()
                User.getInstance().dropCity = dropListHeader[0]
                Toast.makeText(activity, "Drop: ${dropListHeader[0]} selected", Toast.LENGTH_SHORT).show()
            }
        }

        true
    }

    private val onClickListener = View.OnClickListener {v ->
        when(v.id) {
            R.id.button_search -> {
                when(validateSearchCriteria()) {
                    0 -> { moveToCabList() }
                    1 -> { Toast.makeText(activity, getString(R.string.string_select_pickup),
                            Toast.LENGTH_SHORT).show() }
                    2 -> { Toast.makeText(activity, getString(R.string.string_select_drop),
                            Toast.LENGTH_SHORT).show() }
                }
            }
            R.id.interchange_IV -> {
                Toast.makeText(activity, "Interchange clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).setToolbar()
        (activity as MainActivity).setToolbarTitle(getString(R.string.string_cab_booking))
        return inflater.inflate(R.layout.fragment_cab_booking, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        populateCitiesList()
        setUpDateAndTimeSelector()
        setListeners()
    }

    private fun populateCitiesList() {
        pickupListAdapter = ExpandableListAdapter(activity)
        pickupListAdapter?.let { pickup_city_expandable_listview.setAdapter(pickupListAdapter) }
        dropListAdapter = ExpandableListAdapter(activity)
        dropListAdapter?.let { drop_city_expandable_listview.setAdapter(dropListAdapter) }

        pickupListHeader.clear()
        pickupListChild.clear()
        pickupListHeader.add(Constants.getDefaultCitiesList()[0])
        pickupListChild[pickupListHeader[0]] = Constants.getDefaultCitiesList()

        dropListHeader.clear()
        dropListChild.clear()
        dropListHeader.add(Constants.getDefaultCitiesList()[0])
        dropListChild[dropListHeader[0]] = Constants.getDefaultCitiesList()


        pickupListAdapter?.setData(pickupListHeader, pickupListChild)
        dropListAdapter?.setData(dropListHeader, dropListChild)
    }

    private fun setUpDateAndTimeSelector() {
        myDatePicker = MyDatePicker.getInstance(activity, pickup_date_TV, pickup_date_TV2)
        myTimePicker = MyTimePicker.getInstance(activity, pickup_time_TV)
    }

    private fun setListeners() {
        pickup_city_expandable_listview.setOnChildClickListener(onChildClickListener)
        drop_city_expandable_listview.setOnChildClickListener(onChildClickListener)
        myDatePicker?.setClickListeners(date_LL)
        myTimePicker?.setClickListeners(time_LL)
        button_search.setOnClickListener(onClickListener)
        interchange_IV.setOnClickListener(onClickListener)
    }

    private fun moveToCabList() {
        val pickupDateAndTime = Calendar.getInstance()
        var calendar: Calendar? = myDatePicker?.selectedDate
        calendar?.let {
            pickupDateAndTime.set(Calendar.YEAR, calendar!!.get(Calendar.YEAR))
            pickupDateAndTime.set(Calendar.MONTH, calendar!!.get(Calendar.MONTH))
            pickupDateAndTime.set(Calendar.DAY_OF_MONTH, calendar!!.get(Calendar.DAY_OF_MONTH))
        }
        calendar = myTimePicker?.selectedTime
        calendar?.let {
            pickupDateAndTime.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY))
            pickupDateAndTime.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE))
        }

        User.getInstance().pickupDateAndTime = pickupDateAndTime

        if(R.id.radio_one_way_trip == trip_type_rg.checkedRadioButtonId) {
            User.getInstance().isOneWayTrip = true
            User.getInstance().tripType = radio_one_way_trip.text.toString()
        } else {
            User.getInstance().tripType = radio_round_trip.text.toString()
        }

        displayCabListFragment()
    }

    private fun displayCabListFragment() {
        (activity as MainActivity).setFragment(CabListFragment.getInstance(),
                CabListFragment::class.java.simpleName)
    }

    private fun validateSearchCriteria(): Int {
        var result = 0
        if(pickupListHeader[0].equals(Constants.SELECT_CITY, true)) {
            result = 1
        } else if(dropListHeader[0].equals(Constants.SELECT_CITY, true)) {
            result = 2
        }
        return result
    }

    companion object {
        fun getInstance(): CabBookingFragment {
            return CabBookingFragment()
        }
    }
}
