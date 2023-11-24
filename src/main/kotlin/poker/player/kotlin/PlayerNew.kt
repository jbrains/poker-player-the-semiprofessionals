package poker.player.kotlin

import kotlin.math.ceil
import kotlin.math.max

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
            isHighValueHand(holeCards[0], holeCards[1]) -> if (somebodyPreviouslyRaisedPot(gameState)) {
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

    private fun isHighValueHand(first: HoleCard, second: HoleCard) =
        isHighValuePair(first, second) || isSuitedAceKing(first, second)

    fun isSuitedAceKing(first: HoleCard, second: HoleCard): Boolean {
        return listOf(first, second).sortedBy { it.rank }.map { it.rank } == listOf("A", "K") && first.suit == second.suit
    }

    private fun isHighValuePair(first: HoleCard, second: HoleCard) =
        isPair(first, second) && (first.rank in arrayOf("A", "K"))

    private fun isPair(first: HoleCard, second: HoleCard) = first.rank == second.rank

    fun showdown() {
    }

    fun version(): String {
        return "Kotlin Player 0.2.0"
    }
}