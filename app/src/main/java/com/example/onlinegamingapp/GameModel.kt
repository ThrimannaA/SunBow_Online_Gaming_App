package com.example.onlinegamingapp

import android.media.AsyncPlayer
import java.text.FieldPosition
import kotlin.random.Random

data class GameModel (
    var gameId:String="-1",
    var filledPos : MutableList<String> = mutableListOf("","","","","","","","",""),
    var winner:String="",
    var gameStatus:GameStatus=GameStatus.CREATED,
    var currentPlayer: String = (arrayOf("☀", "🌈"))[Random.nextInt(2)]



)

enum class GameStatus{
    CREATED,
    JOINED,
    INPROGRESS,
    FINISHED
}