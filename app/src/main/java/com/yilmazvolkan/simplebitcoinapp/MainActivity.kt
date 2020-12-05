package com.yilmazvolkan.simplebitcoinapp

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.yilmazvolkan.simplebitcoinapp.fragments.ShowGraphFragment

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

        if (savedInstanceState == null) {
            startFragment()
        }
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