package com.selltm.assignment.cabs.pojos

import com.selltm.assignment.pojos.User
import com.selltm.assignment.utils.HardcodedData

/**
 * Created by Asim on 22/06/18.
 */
class SedanCab private constructor() : Cab() {
    init {
        carType = "Sedan"
        carNames1 = "Dzire, Toyota Etios, Tata Indigo or equivalent"
        carNames2 = "Dzire, Toyota Etios, Tata Indigo \nor equivalent"
        pickupDateAndTime = User.getInstance().pickupDateAndTimeString
        totalSeats = 4
        smallBags = 1
        bigBags = 2
        commission = HardcodedData.COMMISSION
        chargePerKm = 15.0
        fare = HardcodedData.FARE
    }

    private object Holder { val  INSTANCE = SedanCab() }

    companion object {
        val instance: SedanCab by lazy { Holder.INSTANCE }
    }
}