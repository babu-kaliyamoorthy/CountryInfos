package com.cts.countryinfos.model

import com.squareup.moshi.Json

/**
 * Created by Babu Kaliyamoorthy on 12/12/19.
 */
data class Country(
    @field:Json(name = "rows") val rows: List<Info>,
    @field:Json(name = "title") val title: String
)