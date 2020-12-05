package com.yilmazvolkan.simplebitcoinapp.ui.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import com.yilmazvolkan.simplebitcoinapp.R
import com.yilmazvolkan.simplebitcoinapp.fragments.ShowGraphFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), MainView {

    @Inject
    lateinit var mainPresenter: MainPresenter

    private var showGraphFragment: ShowGraphFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        mainPresenter.loadMain()

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

    override fun onMainLoaded() {

    }
}