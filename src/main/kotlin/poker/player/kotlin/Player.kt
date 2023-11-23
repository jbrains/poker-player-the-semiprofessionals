package poker.player.kotlin

import org.json.JSONObject

class Player {
    fun betRequest(game_state: JSONObject): Int {
        return game_state["minimum_raise"] as Int
    }

    fun showdown() {
    }

    fun version(): String {
        return "Kotlin Player 0.0.1"
    }
}
