package com.cts.countryinfos.utils

/**
 * Created by Babu Kaliyamoorthy on 13/12/19.
 */
open class DataSourceException(message: String? = null) : Exception(message)

class RemoteDataNotFoundException :
    DataSourceException("Something went wrong while accessing the remote api")