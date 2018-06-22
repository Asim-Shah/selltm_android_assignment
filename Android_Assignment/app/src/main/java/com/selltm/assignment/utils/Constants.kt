package com.selltm.assignment.utils

/**
 * Created by Asim on 22/06/18.
 */
class Constants {
    companion object {
        private val citiesList = ArrayList<String>()
        const val SELECT_CITY = "Select City"

        fun getDefaultCitiesList(): ArrayList<String> {
            citiesList.clear()
            citiesList.add(SELECT_CITY)
            citiesList.add("Mumbai")
            citiesList.add("Pune")
            citiesList.add("Bangalore")
            citiesList.add("Hyderabad")
            return citiesList
        }
    }
}