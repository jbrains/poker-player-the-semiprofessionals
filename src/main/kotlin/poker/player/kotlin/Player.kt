package poker.player.kotlin

import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.ceil

class Player {
    fun betRequest(game_state: JSONObject): Int {
        println(game_state)
        val players = game_state["players"] as JSONArray
        val us = players[3] as JSONObject
        val holeCards = us["hole_cards"] as JSONArray
        val first = holeCards[0] as JSONObject
        val second = holeCards[1] as JSONObject

        println(holeCards)
        return when {
            isHighValuePair(first, second) -> betAllIn(us)
            isMidValuePair(first, second) -> raise(game_state)
            else -> fold(us)
        }
    }
    private fun isMidValuePair(first: JSONObject, second: JSONObject) =
        isPair(first, second) && (first["rank"] == "10" || first["rank"] == "J" || first["rank"] == "Q")

    private fun fold(player: JSONObject) = 0

    private fun betAllIn(player: JSONObject): Int = player["stack"] as Int

    private fun raise(game_state: JSONObject): Int {
        val pot = game_state["pot"] as Int
        return ceil(pot * 2.0 / 3.0).toInt()
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