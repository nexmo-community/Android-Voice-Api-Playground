package com.nexmo.example.britt.playingvapi

import com.squareup.moshi.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

var moshi = Moshi.Builder()
    .add(
        PolymorphicJsonAdapterFactory.of(NccoAction::class.java, "action")
            .withSubtype(Talk::class.java, ActionType.talk.name)
            .withSubtype(Stream::class.java, ActionType.stream.name)
            .withSubtype(Input::class.java, ActionType.input.name)
    )
    .add(KotlinJsonAdapterFactory())
    .build()

val httpLogging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
val httpClient = OkHttpClient.Builder().addInterceptor(httpLogging).build()

var retrofit = Retrofit.Builder()
    .baseUrl("https://api.nexmo.com/v1/")
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(httpClient)
    .build()

var nexmoService = retrofit.create<NexmoApiService>(NexmoApiService::class.java)
