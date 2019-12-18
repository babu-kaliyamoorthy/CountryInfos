package com.cts.countryinfos

import com.cts.countryinfos.base.CoroutinesContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Created by Babu Kaliyamoorthy on 17/12/19.
 */
class TestContextProvider : CoroutinesContextProvider() {
    override val Main: CoroutineContext = Dispatchers.Unconfined
    override val IO: CoroutineContext = Dispatchers.Unconfined
}