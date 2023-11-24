package poker.player.kotlin

import kotlin.math.abs
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
            gameState.players.filter { it.status == "active" }.map { it.name }.toSet() == setOf("PokerGPT", "The Semiprofessionals") -> {
                println("take their money")
                highValueBet(gameState, us)
            }
            isHighValueHand(holeCards, gameState.communityCards) -> highValueBet(gameState, us)
            isMidValuePair(holeCards) || isSuitedConnection(holeCards) -> raiseTwoThirds(gameState)
            isPair(holeCards) || isSuitedJackOrBetter(holeCards) -> raiseDoubleSmallBlind(gameState)
            else -> fold(us)
        }
        return max(calculatedBet, 0)
    }

    private fun highValueBet(gameState: Game, us: PlayerInGame) =
        if (somebodyPreviouslyRaisedPot(gameState)) {
            betAllIn(us)
        } else {
            raiseTwoThirds(gameState)
        }

    private fun somebodyPreviouslyRaisedPot(gameState: Game) = gameState.pot - (3 * gameState.smallBlind) > 0


    private fun isMidValuePair(holeCards: List<Card>): Boolean {
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


    private fun isHighValueHand(holeCards: List<Card>, communityCards: List<Card>): Boolean {
        return isHighValuePair(holeCards, communityCards) || isSuitedAceKing(holeCards)
    }

    fun isSuitedAceKing(holeCards: List<Card>): Boolean {
        val aceAndKing = holeCards.sortedBy { it.rank }.map { it.rank } == listOf("A", "K")
        return aceAndKing && isSameSuit(holeCards)
    }

    fun isSuitedConnection(holeCards: List<Card>): Boolean {
        val (first, second) = holeCards.map { rankNumber(it.rank) }.sortedDescending()
        val isClose = abs(first - second) <= 1
        return isClose && isSameSuit(holeCards)
    }

    fun isSuitedJackOrBetter(holeCards: List<Card>): Boolean {
        val highCardJackOrBetter = holeCards.map { rankNumber(it.rank) }.max() >= rankNumber("J")
        return highCardJackOrBetter && isSameSuit(holeCards)
    }

    private fun isSameSuit(holeCards: List<Card>) =
        holeCards[0].suit == holeCards[1].suit

    private fun isHighValuePair(holeCards: List<Card>, communityCards: List<Card>): Boolean {
        return (isPair(holeCards) && (holeCards[0].rank in arrayOf("A", "K"))) || containsPair(holeCards + communityCards) in arrayOf("A", "K")
    }

    fun containsPair(cards: List<Card>): String? {
        return cards.sortedByDescending { rankNumber(it.rank) }.zipWithNext().firstOrNull { (a, b) -> a.rank == b.rank }?.first?.rank
    }

    fun rankNumber(rank: String) = when (rank) {
        "1" -> 1
        "2" -> 2
        "3" -> 3
        "4" -> 4
        "5" -> 5
        "6" -> 6
        "7" -> 7
        "8" -> 8
        "9" -> 9
        "10" -> 10
        "J" -> 11
        "Q" -> 12
        "K" -> 13
        "A" -> 14
        else -> 0
    }

    private fun isPair(holeCards: List<Card>) = holeCards[0].rank == holeCards[1].rank

    fun showdown(readValue: Game) {
    }

    fun version(): String {
        return "Kotlin Player 0.7.2"
    }
}