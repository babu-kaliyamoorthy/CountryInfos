package com.cts.countryinfos.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cts.countryinfos.R

/**
 * Created by Babu Kaliyamoorthy on 12/12/19.
 */
open class CountryInfoBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_info)
    }
}
