package com.cts.countryinfos

import android.os.Bundle
import com.cts.countryinfos.base.CountryInfoBaseActivity

/**
 * Created by Babu Kaliyamoorthy on 12/12/19.
 */
class MainActivity : CountryInfoBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
