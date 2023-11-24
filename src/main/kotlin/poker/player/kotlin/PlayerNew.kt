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
            isHighValueHand(holeCards) -> if (somebodyPreviouslyRaisedPot(gameState)) {
                betAllIn(us)
            } else {
                raiseTwoThirds(gameState)
            }

            isMidValuePair(holeCards) -> raiseTwoThirds(gameState)
            isPair(holeCards) -> raiseDoubleSmallBlind(gameState)
            else -> fold(us)
        }
        return max(calculatedBet, 0)
    }

    private fun somebodyPreviouslyRaisedPot(gameState: Game) = gameState.pot - (3 * gameState.smallBlind) > 0


    private fun isMidValuePair(holeCards: List<HoleCard>): Boolean {
        return isPair(holeCards) && (holeCards[0].rank in arrayOf("10", "J", "Q"))
    }

    private fun fold(player: PlayerInGame) = 0

    private fun betAllIn(player: PlayerInGame): Int = player.stack.toInt()

    private fun raiseTwoThirds(gameState: Game): Int {
        val pot = gameState.pot
        return ceil(pot * 2.0 / 3.0).toInt()
    }

    private fun raiseDoubleSmallBlind(gameState: Game) = gameState.smallBlind * 2

    // rank difference


    private fun isHighValueHand(holeCards: List<HoleCard>): Boolean {
        return isHighValuePair(holeCards) || isSuitedAceKing(holeCards)
    }

    fun isSuitedAceKing(holeCards: List<HoleCard>): Boolean {
        val aceAndKing = holeCards.sortedBy { it.rank }.map { it.rank } == listOf("A", "K")
        return aceAndKing && isSameSuit(holeCards)
    }

    private fun isSameSuit(holeCards: List<HoleCard>) =
        holeCards[0].suit == holeCards[1].suit

    private fun isHighValuePair(holeCards: List<HoleCard>): Boolean {
        return isPair(holeCards) && (holeCards[0].rank in arrayOf("A", "K"))
    }

    private fun isPair(holeCards: List<HoleCard>) = holeCards[0].rank == holeCards[1].rank

    fun showdown() {
    }

    fun version(): String {
        return "Kotlin Player 0.3.1"
    }
}