package com.selltm.assignment.cabs.pojos

import com.selltm.assignment.pojos.User
import com.selltm.assignment.utils.HardcodedData

/**
 * Created by Asim on 22/06/18.
 */
class SUVCab private constructor() : Cab() {
    init {
        carType = "SUV"
        carNames1 = "XUV, Toyota Fortuner, Pajero or equivalent"
        carNames2 = "XUV, Toyota Fortuner, Pajero \nor equivalent"
        pickupDateAndTime = User.getInstance().pickupDateAndTimeString
        totalSeats = 4
        smallBags = 2
        bigBags = 2
        commission = HardcodedData.COMMISSION
        chargePerKm = 16.0
        fare = HardcodedData.FARE
    }

    private object Holder { val  INSTANCE = SUVCab() }

    companion object {
        val instance: SUVCab by lazy { Holder.INSTANCE }
    }
}