package com.nexmo.example.britt.playingvapi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

const val APP_JWT = TODO("copy your jwt here.")

interface NexmoApiService {

    @Headers(
        "Authorization: Bearer $APP_JWT",
        "Content-Type: application/json"
    )
    @POST("calls")
    fun makeCall(@Body request: NXMRequest): Call<Unit>
}