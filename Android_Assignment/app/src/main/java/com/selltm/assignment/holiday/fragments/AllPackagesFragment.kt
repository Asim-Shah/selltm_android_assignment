package com.selltm.assignment.holiday.fragments


import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.selltm.assignment.R
import com.selltm.assignment.activities.MainActivity
import com.selltm.assignment.holiday.adapters.AllPackagesAdapter
import kotlinx.android.synthetic.main.fragment_all_packages.*


/**
 * Created by Asim on 22/06/18.
 */
class AllPackagesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setHolidayToolbar()
        (activity as MainActivity).setToolbarTitle("Holidays")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_packages, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val allPackagesAdapter = AllPackagesAdapter.getInstance(activity)
        val layoutManager = LinearLayoutManager(this.activity!!.applicationContext)
        all_packages_list_RV.layoutManager = layoutManager
        all_packages_list_RV.itemAnimator = DefaultItemAnimator()
        all_packages_list_RV.adapter = allPackagesAdapter
    }

    companion object {
        fun getInstance(): AllPackagesFragment {
            return AllPackagesFragment()
        }
    }
}
