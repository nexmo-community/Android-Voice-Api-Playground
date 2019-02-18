package com.nexmo.example.britt.playingvapi

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun executeMakeCall(context: Context, viewModel: MainActivity.CallRequestUiModel) {
    val request = NXMRequest(
        arrayOf(PhoneNum(number = viewModel.toPhone)),
        PhoneNum(number = viewModel.fromPhone),
        ncco = viewModel.actions
    )

    nexmoService.makeCall(request)
        .enqueue(object : Callback<Unit> {

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(context, "onFailure!", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                Toast.makeText(context, "onResponse!", Toast.LENGTH_LONG).show()

            }

        })

}
