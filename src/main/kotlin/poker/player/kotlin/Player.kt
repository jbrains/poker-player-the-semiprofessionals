package poker.player.kotlin

import kotlin.math.ceil
import kotlin.math.max

class PlayerNew {
    fun betRequest(game_state: Game): Int {
        println(game_state)
        val players = game_state.players
        val ourPlayerIndex = game_state.inAction
        val us = players[ourPlayerIndex]
        val holeCards = us.holeCards

        // with aces, don't have to go all in if nobody raised before - minimize losses?

        println(holeCards)
        val calculatedBet = when {
            isHighValuePair(holeCards[0], holeCards[1]) -> betAllIn(us)
            isMidValuePair(holeCards[0], holeCards[1]) -> raise(game_state)
            else -> fold(us)
        }
        return max(calculatedBet, 0)
    }
    private fun isMidValuePair(first: HoleCard, second: HoleCard) =
        isPair(first, second) && (first.rank in arrayOf("10", "J", "Q"))

    private fun fold(player: PlayerInGame) = 0

    private fun betAllIn(player: PlayerInGame): Int = player.stack.toInt()

    private fun raise(game_state: Game): Int {
        val pot = game_state.pot
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