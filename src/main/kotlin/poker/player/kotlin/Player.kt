package poker.player.kotlin

import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.ceil
import kotlin.math.max

class Player {
    fun betRequest(game_state: JSONObject): Int {
        val players = game_state["players"] as JSONArray
        val ourPlayerIndex = game_state["in_action"] as Int
        val us = players[ourPlayerIndex] as JSONObject
        val holeCards = us["hole_cards"] as JSONArray
        val first = holeCards[0] as JSONObject
        val second = holeCards[1] as JSONObject

        println(holeCards)
        val calculatedBet = when {
            isHighValuePair(first, second) -> betAllIn(us)
            isMidValuePair(first, second) -> raise(game_state)
            else -> fold(us)
        }
        return max(calculatedBet, 0)
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

class PlayerNew {
    fun betRequest(gameState: Game): Int {
        println("kotlin object: $gameState")
        val players = gameState.players
        val ourPlayerIndex = gameState.inAction
        val us = players[ourPlayerIndex]
        val holeCards = us.holeCards!!

        // with aces, don't have to go all in if nobody raised before - minimize losses?

        println(holeCards)
        val calculatedBet = when {
            isHighValuePair(holeCards[0], holeCards[1]) -> if (somebodyPreviouslyRaisedPot(gameState)) {
                betAllIn(us)
            } else {
                raise(gameState)
            }
            isMidValuePair(holeCards[0], holeCards[1]) -> raise(gameState)
            else -> fold(us)
        }
        return max(calculatedBet, 0)
    }

    private fun somebodyPreviouslyRaisedPot(gameState: Game) = gameState.pot - (3 * gameState.smallBlind) > 0

    private fun isMidValuePair(first: HoleCard, second: HoleCard) =
        isPair(first, second) && (first.rank in arrayOf("10", "J", "Q"))

    private fun fold(player: PlayerInGame) = 0

    private fun betAllIn(player: PlayerInGame): Int = player.stack.toInt()

    private fun raise(gameState: Game): Int {
        val pot = gameState.pot
        return ceil(pot * 2.0 / 3.0).toInt()
    }

    // rank difference

    private fun isHighValuePair(first: HoleCard, second: HoleCard) =
        isPair(first, second) && (first.rank in arrayOf("A", "K"))

    private fun isPair(first: HoleCard, second: HoleCard) = first.rank == second.rank

    fun showdown() {
    }

    fun version(): String {
        return "Kotlin Player 0.1.0"
    }
}