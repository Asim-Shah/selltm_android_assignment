package com.selltm.assignment.cabs.fragments


import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.selltm.assignment.R
import com.selltm.assignment.activities.MainActivity
import com.selltm.assignment.booking.BookingDetailsFragment
import com.selltm.assignment.cabs.adapters.CabsAdapter
import com.selltm.assignment.cabs.pojos.*
import com.selltm.assignment.pojos.*
import kotlinx.android.synthetic.main.fragment_cab_list.*

/**
 * Created by Asim on 22/06/18.
 */
class CabListFragment : Fragment() {
    private var cabArrayList: ArrayList<Cab>? = null
    private var cabsAdapter: CabsAdapter? = null

    private val onClickListener = View.OnClickListener {v ->
        val selectedCab: Int = v.getTag(R.id.car_type_TV) as Int
        User.getInstance().cabSelected = cabArrayList?.get(selectedCab)
        displayBookingDetailsFragment()
    }

    private fun displayBookingDetailsFragment() {
        (activity as MainActivity).setFragment(BookingDetailsFragment.getInstance(),
                BookingDetailsFragment::class.java.simpleName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setToolbarShare()
        (activity as MainActivity).setToolbarTitle(getString(R.string.string_cab_list))
        cabsAdapter = CabsAdapter.getInstance(activity, onClickListener)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cab_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        prepareTempCarSingletonData()
        setUpRecyclerView()
        setDataToViews()
    }

    private fun prepareTempCarSingletonData() {
        cabArrayList = ArrayList()
        cabArrayList?.add(CompactCab.instance)
        cabArrayList?.add(SedanCab.instance)
        cabArrayList?.add(SUVCab.instance)
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this.activity!!.applicationContext)
        cabs_list_RV.layoutManager = layoutManager
        cabs_list_RV.itemAnimator = DefaultItemAnimator()
        cabs_list_RV.adapter = cabsAdapter
    }

    private fun setDataToViews() {
        val user = User.getInstance()
        trip_type_TV.text = user.tripType.toUpperCase()
        pickup_city_TV.text = user.pickupCity
        drop_city_TV.text = user.dropCity
        pickup_date_time_TV.text = user.pickupDateAndTimeString
        cabArrayList?.let { cabsAdapter?.setData(cabArrayList!!) }
    }

    companion object {
        fun getInstance(): CabListFragment {
            return CabListFragment()
        }
    }
}
