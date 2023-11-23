package poker.player.kotlin

import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.ceil
import kotlin.math.max

class PlayerNew {
    fun betRequest(game_state: Game): Int {
        println(game_state)
        val players = game_state.players
        val ourPlayerIndex = game_state.inAction
        val us = players[ourPlayerIndex]
        val holeCards = us.holeCards
        val first = holeCards[0]
        val second = holeCards[1]

        println(holeCards)
        val calculatedBet = when {
            isHighValuePair(first, second) -> betAllIn(us)
            isMidValuePair(first, second) -> raise(game_state)
            else -> fold(us)
        }
        return max(calculatedBet, 0)
    }
    private fun isMidValuePair(first: HoleCard, second: HoleCard) =
        isPair(first, second) && (first.rank == "10" || first.rank == "J" || first.rank == "Q")

    private fun fold(player: PlayerInGame) = 0

    private fun betAllIn(player: PlayerInGame): Int = player.stack.toInt()

    private fun raise(game_state: Game): Int {
        val pot = game_state.pot
        return ceil(pot * 2.0 / 3.0).toInt()
    }

    private fun isHighValuePair(first: HoleCard, second: HoleCard) =
        isPair(first, second) && (first.rank == "A" || first.rank == "K")

    private fun isPair(first: HoleCard, second: HoleCard) = first.rank == second.rank

    fun showdown() {
    }

    fun version(): String {
        return "Kotlin Player 0.1.0"
    }
}