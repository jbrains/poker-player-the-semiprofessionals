package poker.player.kotlin

import org.json.JSONObject

class Player {
    fun betRequest(game_state: JSONObject): Int {
        println(game_state)
        return game_state["minimum_raise"] as Int
    }

    fun showdown() {
    }

    fun version(): String {
        return "Kotlin Player 0.0.1"
    }
}
