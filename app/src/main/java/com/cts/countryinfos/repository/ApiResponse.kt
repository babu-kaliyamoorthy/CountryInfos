package com.cts.countryinfos.repository

/**
 * Created by Babu Kaliyamoorthy on 13/12/19.
 */
sealed class ApiResponse<out T : Any> {
    class SuccessResponse<out T : Any>(val data: T) : ApiResponse<T>()
    class ErrorResponse(val exception: Throwable) : ApiResponse<Nothing>()
}