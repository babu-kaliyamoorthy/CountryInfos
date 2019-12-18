package com.cts.countryinfos.base

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Created by Babu Kaliyamoorthy on 17/12/19.
 */
open class CoroutinesContextProvider {

    open val Main: CoroutineContext by lazy { Dispatchers.Main }
    open val IO: CoroutineContext by lazy { Dispatchers.IO }
}