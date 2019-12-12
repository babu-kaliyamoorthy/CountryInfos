package com.cts.countryinfos.model

import com.squareup.moshi.Json

/**
 * Created by Babu Kaliyamoorthy on 12/12/19.
 */
data class Info(
    @field:Json(name = "description") val description: String,
    @field:Json(name = "imageHref") val imageHref: String,
    @field:Json(name = "title") val title: String
)