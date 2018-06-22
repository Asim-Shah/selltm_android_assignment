package com.selltm.assignment.cabs.pojos

import com.selltm.assignment.pojos.User
import com.selltm.assignment.utils.HardcodedData

/**
 * Created by Asim on 22/06/18.
 */
class CompactCab private constructor() : Cab() {
    init {
        carType = "Compact"
        carNames1 = "Indica, Swift, Alto, Ford Figo or \nequivalent"
        carNames2 = "Indica, Swift, Alto, Ford Figo or equivalent"
        pickupDateAndTime = User.getInstance().pickupDateAndTimeString
        totalSeats = 4
        smallBags = 2
        bigBags = 0
        commission = HardcodedData.COMMISSION
        chargePerKm = 14.0
        fare = HardcodedData.FARE
    }

    private object Holder { val  INSTANCE = CompactCab() }

    companion object {
        val instance: CompactCab by lazy { Holder.INSTANCE }
    }
}