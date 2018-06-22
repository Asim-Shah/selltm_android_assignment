package com.selltm.assignment.home.pojos

/**
 * Created by Asim on 22/06/18.
 */
class Notification private constructor() {
    val title = "Test"
    val upto = "UPTO: 30 Dec 19"
    val content = "Content of notification"
    val tags = "Tags"

    private object Holder { val  INSTANCE = Notification() }

    companion object {
        val instance: Notification by lazy { Notification.Holder.INSTANCE }
    }
}