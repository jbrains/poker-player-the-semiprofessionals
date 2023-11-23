package poker.player.kotlin

import org.json.JSONArray
import org.json.JSONObject

class Player {
    fun betRequest(game_state: JSONObject): Int {
        println(game_state)
        val players = game_state["players"] as JSONArray
        val us = players[3] as JSONObject
        val holeCards = us["hole_cards"] as JSONArray
        val first = holeCards[0] as JSONObject
        val second = holeCards[1] as JSONObject
        val ourStack = us["stack"]

        println(holeCards)
        return (if (isHighValuePair(first, second)) {
            ourStack
        } else {
            0
        }) as Int
    }

    private fun isHighValuePair(first: JSONObject, second: JSONObject) =
        isPair(first, second) && (first["rank"] == "A" || first["rank"] == "K")

    private fun isPair(first: JSONObject, second: JSONObject) = first["rank"] == second["rank"]

    fun showdown() {
    }

    fun version(): String {
        return "Kotlin Player 0.0.1"
    }
}