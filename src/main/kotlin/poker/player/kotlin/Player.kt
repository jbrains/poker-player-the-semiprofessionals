package poker.player.kotlin

import org.json.JSONArray
import org.json.JSONObject

class Player {
    fun betRequest(game_state: JSONObject): Int {
        println(game_state)
        val players = game_state["players"] as Array<JSONObject>
        val us = players[3]
        val holeCards = us["hole_cards"] as Array<JSONObject>

        println(holeCards)
        return (if (holeCards[0]["rank"] == holeCards[1]["rank"]) us["stack"] else 0) as Int
    }

    fun showdown() {
    }

    fun version(): String {
        return "Kotlin Player 0.0.1"
    }
}
