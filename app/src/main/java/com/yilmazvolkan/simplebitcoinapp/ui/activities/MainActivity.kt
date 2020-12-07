package com.yilmazvolkan.simplebitcoinapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yilmazvolkan.simplebitcoinapp.R
import com.yilmazvolkan.simplebitcoinapp.ui.fragments.ShowGraphFragment


class MainActivity : AppCompatActivity() {
    private var showGraphFragment: ShowGraphFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startFragment()
    }

    private fun startFragment() {
        showGraphFragment = ShowGraphFragment.newInstance()
        if (showGraphFragment != null && this.isFinishing.not()) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, showGraphFragment!!)
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
        }
    }
}