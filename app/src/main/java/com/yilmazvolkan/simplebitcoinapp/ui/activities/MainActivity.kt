package com.yilmazvolkan.simplebitcoinapp.ui.activities

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.yilmazvolkan.simplebitcoinapp.R
import com.yilmazvolkan.simplebitcoinapp.ui.fragments.ShowGraphFragment
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private var showGraphFragment: ShowGraphFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

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