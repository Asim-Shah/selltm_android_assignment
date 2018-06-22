package com.selltm.assignment.home.fragments


import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.selltm.assignment.R
import com.selltm.assignment.activities.MainActivity
import com.selltm.assignment.cabs.fragments.CabBookingFragment
import com.selltm.assignment.holiday.fragments.HolidayFragment
import com.selltm.assignment.home.adapters.ImageSwipeAdapter
import com.selltm.assignment.home.adapters.NotificationAdapter
import com.selltm.assignment.pojos.User
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_top.*

/**
 * Created by Asim on 20/06/18.
 */
class HomeFragment : Fragment() {
    private val TAG = javaClass.simpleName
    private var imageSwipeAdapter: ImageSwipeAdapter? = null

    private val onClickListener = View.OnClickListener {v ->
        Log.d(TAG, "onClickListener v --> ${v.id}")
        when (v.id) {
            R.id.flight_IV, R.id.flight_TV -> Toast.makeText(activity,
                    "Flight clicked", Toast.LENGTH_SHORT).show()

            R.id.bus_IV, R.id.bus_TV -> Toast.makeText(activity,
                    "Bus clicked", Toast.LENGTH_SHORT).show()

            R.id.cabs_IV, R.id.cabs_TV -> displayCabBookingFragment()

            R.id.holidays_IV, R.id.holidays_TV -> displayHolidayFragment()

            R.id.hotel_IV, R.id.hotel_TV -> Toast.makeText(activity,
                    "Hotel clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayCabBookingFragment() {
        (activity as MainActivity).setFragment(CabBookingFragment.getInstance(),
                CabBookingFragment::class.java.simpleName)
    }

    private fun displayHolidayFragment() {
        (activity as MainActivity).setFragment(HolidayFragment.getInstance(),
                HolidayFragment::class.java.simpleName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).setHomeToolbar()
        (activity as MainActivity).setToolbarTitle("Hi ${(User.getInstance().userName)}")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        setUpImagesViewPager()
        setUpNotificationsList()
        setClickListeners()
    }

    private fun setUpImagesViewPager() {
        imageSwipeAdapter = ImageSwipeAdapter(activity)
        images_view_pager.adapter = imageSwipeAdapter
        images_view_pager.clipToPadding = false
        images_view_pager.setPadding(50,0,50,0)
        images_view_pager.pageMargin = 15
        val images = intArrayOf(R.drawable.image1, R.drawable.image1, R.drawable.image1, R.drawable.image1)
        imageSwipeAdapter?.setData(images)
    }

    private fun setUpNotificationsList() {
        val notificationAdapter = NotificationAdapter()
        val layoutManager = LinearLayoutManager(this.activity!!.applicationContext)
        notification_recycler_view.layoutManager = layoutManager
        notification_recycler_view.itemAnimator = DefaultItemAnimator()
        notification_recycler_view.adapter = notificationAdapter
    }

    private fun setClickListeners() {
        flight_IV.setOnClickListener(onClickListener)
        flight_TV.setOnClickListener(onClickListener)
        bus_IV.setOnClickListener(onClickListener)
        bus_TV.setOnClickListener(onClickListener)
        cabs_IV.setOnClickListener(onClickListener)
        cabs_TV.setOnClickListener(onClickListener)
        holidays_IV.setOnClickListener(onClickListener)
        holidays_TV.setOnClickListener(onClickListener)
        hotel_IV.setOnClickListener(onClickListener)
        hotel_TV.setOnClickListener(onClickListener)
    }

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
