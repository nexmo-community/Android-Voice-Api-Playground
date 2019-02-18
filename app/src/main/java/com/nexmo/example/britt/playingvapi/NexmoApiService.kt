package com.nexmo.example.britt.playingvapi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

const val APP_JWT = "SAMPLE_JWT" //TODO: swap with your own. See README.md for more details

const val DEFAULT_CALLEE = "" //TODO: for convenience you can insert here the phone number you'd like to call to. you can change it from the UI as well.
const val DEFAULT_CALLER = "12345678901"

interface NexmoApiService {

    @Headers(
        "Authorization: Bearer $APP_JWT",
        "Content-Type: application/json"
    )
    @POST("calls")
    fun makeCall(@Body request: NXMRequest): Call<Unit>
}