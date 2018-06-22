package com.selltm.assignment.activities

import android.app.Fragment
import android.os.Bundle
import com.selltm.assignment.R
import com.selltm.assignment.base.BaseActivity
import com.selltm.assignment.login.LoginFragment

/**
 * Created by Asim on 20/06/18.
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayLoginOnLaunch()
    }

    private fun displayLoginOnLaunch() {
        setFragment(LoginFragment.getInstance(), null)
    }

    fun setFragment(fragment: Fragment, backStackEntry: String?) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        backStackEntry?.let { fragmentTransaction.addToBackStack(backStackEntry) }
        fragmentTransaction.commit()
    }
}
