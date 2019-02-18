package com.nexmo.example.britt.playingvapi

import org.junit.Assert.*
import org.junit.Test


class MoshiTest {


    @Test
    @Throws(Exception::class)
    fun booleanAdapter() {
//        val adapter = moshi.adapter(Boolean::class.javaPrimitiveType!!).lenient()
        val adapter = moshi.adapter(Boolean::class.javaPrimitiveType!!).lenient()
        assertEquals(adapter.fromJson("true"), true)
        assertEquals(adapter.fromJson("TRUE"), true)

    }



    @Test
    @Throws(Exception::class)
    fun testingTesting(){

        val json1 = "{\"action\":\"talk\",\"text\":\"You are joining a conference that was created using Nexmo's Voice API.\"}"

        val action1 = Talk("You are joining a conference that was created using Nexmo's Voice API.")

        val action1Json = moshi.adapter(NccoAction::class.java).toJson(action1)

        assertEquals(action1Json, json1)


    }
}