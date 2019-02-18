package com.nexmo.example.britt.playingvapi

import android.support.annotation.IntRange
import com.squareup.moshi.Json


data class NXMRequest(val to: Array<PhoneNum> ,
                      val from: PhoneNum, val ncco: List<NccoAction>) //val answer_url: Array<String>)

enum class ActionType {
    @Json(name = "phone")phone,
    @Json(name = "talk")talk,
    @Json(name = "record")record,
    @Json(name = "conversation")conversation,
    @Json(name = "stream")stream,
    @Json(name = "input")input

}
sealed class NccoAction(@Json(name="action") val actionType: ActionType, @Transient val synchronous: Boolean)

data class PhoneNum(val type: String = "phone", val number: String)

data class Record(val eventUrl: Array<String>) : NccoAction(ActionType.record, false)

//create standard or moderated conferences.
data class Conversation(val name: String, val musicOnHoldUrl: String? = null, val startOnEnter: Boolean? = null, val endOnExit: Boolean? = null, val record: Boolean? = null) : NccoAction(ActionType.conversation, true)

//data class Connect(val eventUrl: Array<String>, val from: String, val endpoint: Array<PhoneNum>): NccoAction("connect")

data class Talk(val text: String, val voiceName: String? = null, val bargeIn: Boolean? = null, val loop: Int? = null, val level: Double? = null) : NccoAction(ActionType.talk, synchronous = bargeIn != true) //if bargeIn == true then add input action

enum class Tunes(val url : String){
    DixieHorn("https://jk9nj200-a.akamaihd.net/downloads/ringtones/files/mp3/dixie-horn-of-the-general-lee-2333366-43394.mp3"),
    Kriket("https://ypvnxx00-a.akamaihd.net/downloads/ringtones/files/mp3/jangkrik-malam-42841.mp3"),
    Cuco("https://jk9nj200-a.akamaihd.net/downloads/ringtones/files/mp3/cuco-32367.mp3"),
    ToThePoint("https://ypvnxx00-a.akamaihd.net/downloads/ringtones/files/dl/mp3/to-the-point-43704.mp3")
}

data class Stream( val streamUrl: List<String>, @Transient val tuneName: String? = null) : NccoAction(ActionType.stream, false)

data class Input(val submitOnHash: Boolean = false, @IntRange(from=3,to=10) val timeOut: Int = 3) : NccoAction(ActionType.input, true)




