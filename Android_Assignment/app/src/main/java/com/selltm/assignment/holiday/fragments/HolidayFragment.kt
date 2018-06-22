package com.selltm.assignment.holiday.fragments


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.selltm.assignment.R
import com.selltm.assignment.activities.MainActivity
import com.selltm.assignment.holiday.adapters.HolidaySwipeAdapter
import kotlinx.android.synthetic.main.fragment_holiday.*

/**
 * A simple [Fragment] subclass.
 */
class HolidayFragment : Fragment() {

    private val onClickListener = View.OnClickListener {v ->
        when(v.id) {
            R.id.button_all_packages -> { displayAllPackagesFragment() }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setHolidayToolbar()
        (activity as MainActivity).setToolbarTitle("Holiday")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_holiday, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        setUpViewPager()
        setOnClickListeners()
    }

    private fun setUpViewPager() {
        val holidaySwipeAdapter = HolidaySwipeAdapter(activity)
        images_view_pager.adapter = holidaySwipeAdapter
        images_view_pager.clipToPadding = false
        images_view_pager.setPadding(50,0,50,0)
        images_view_pager.pageMargin = 15
        val images = intArrayOf(R.drawable.image2, R.drawable.image2, R.drawable.image2, R.drawable.image2)
        holidaySwipeAdapter.setData(images)
    }

    private fun setOnClickListeners() {
        button_all_packages.setOnClickListener(onClickListener)
    }

    private fun displayAllPackagesFragment() {
        (activity as MainActivity).setFragment(AllPackagesFragment.getInstance(),
                AllPackagesFragment::class.java.simpleName)
    }

    companion object {
        fun getInstance(): HolidayFragment {
            return HolidayFragment()
        }
    }
}
