package com.cts.countryinfos.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cts.countryinfos.R
import com.cts.countryinfos.base.CountryInfoBaseActivity
import com.cts.countryinfos.model.Info
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_info_details.*

/**
 * Created by Babu Kaliyamoorthy on 16/12/19.
 */
class CountryInfoListItemDetailActivity : CountryInfoBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.country_info_details)

        val title = intent.getStringExtra(INTENT_TITLE)
        val description = intent.getStringExtra(INTENT_DESCRIPTION)
        val url = intent.getStringExtra(INTENT_URL)

        tvTitle.text = title
        tvDestription.text = description

        Picasso.get()
            .load(url)
            .placeholder(R.drawable.placeholder)
            .into(ivCountryInfo)

    }

    companion object {
        private val INTENT_TITLE = "title"
        private val INTENT_DESCRIPTION = "description"
        private val INTENT_URL = "imageHref"

        fun newIntent(context: Context, info: Info): Intent {
            val intent = Intent(context, CountryInfoListItemDetailActivity::class.java)
            intent.putExtra(INTENT_TITLE, info.title)
            intent.putExtra(INTENT_DESCRIPTION, info.description)
            intent.putExtra(INTENT_URL, info.imageHref)
            return intent
        }
    }
}