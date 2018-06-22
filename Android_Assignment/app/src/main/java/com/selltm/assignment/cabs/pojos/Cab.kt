package com.selltm.assignment.cabs.pojos

/**
 * Created by Asim on 22/06/18.
 */
open class Cab {
    var carType: String? = null
    var carNames1: String? = null
    var carNames2: String? = null
    var pickupDateAndTime: String? = null
    var totalSeats: Int = 0
    var smallBags: Int = 0
    var bigBags: Int = 0
    var commission: Double = 0.0
    var chargePerKm: Double = 0.0
    var fare: Int = 0

    override fun toString(): String {
        return "Cab(carType=$carType, carNames1=$carNames1, carNames2=$carNames2, pickupDateAndTime=$pickupDateAndTime, totalSeats=$totalSeats, smallBags=$smallBags, bigBags=$bigBags, commission=$commission, chargePerKm=$chargePerKm, fare=$fare)"
    }
}