package com.cts.countryinfos.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.cts.countryinfos.CountryInfoListActivity
import com.cts.countryinfos.R

/**
 * Created by Babu Kaliyamoorthy on 10/12/19.
 */
class SplashScreenActivity : Activity() {

    /**time out seconds to show the splash screen
     */
    private val splashTimeOut: Long = 2000 // 2 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, CountryInfoListActivity::class.java))
            finish()
        }, splashTimeOut)
    }
}