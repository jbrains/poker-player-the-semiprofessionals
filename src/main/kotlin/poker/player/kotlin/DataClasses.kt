package poker.player.kotlin

import com.fasterxml.jackson.annotation.JsonProperty

data class PlayerInGame(
    /** Id of the player (same as the index) */
    val id: Long,
    /** Name specified in the tournament config */
    val name: String,
    /** Status of the player:
    - active: the player can make bets, and win
    the current pot
    - folded: the player folded, and gave up interest in
    the current pot. They can return in the next round.
    - out: the player lost all chips, and is out of this
    sit'n'go */
    val status: String,
    /**  Version identifier returned by the player */
    val version: String,
    /**  Amount of chips still available for the player.
    (Not including the chips the player bet in
    this round.) */
    val stack: Long,
    /** The amount of chips the player put into the pot */
    val bet: Long,
    /**  The cards of the player. This is only visible for
    your own player except after showdown, when cards
    revealed are also included. */
    @JsonProperty("hole_cards")
    val holeCards: List<Card>?,
    @JsonProperty("amount_won")
    val amountWon: Int?
)

data class Card(
    /** Rank of the card. Possible values are
    numbers 2-10 and J,Q,K, */
    val rank: String,
    /** Suit of the card. Possible values are:
     clubs,spades,hearts,diamonds */
    val suit: String,
)

data class Game(
    /** Id of the current tournament */
    @JsonProperty("tournament_id")
    val tournamentId: String,
    /** Id of the current sit'n'go game. You can use this to link a
     sequence of game states together for logging purposes, or to
    make sure that the same strategy is played for an entire game */
    @JsonProperty("game_id")
    val gameId: String,
    /** ndex of the current round within a sit'n'go */
    val round: Long,
    /** Index of the betting opportunity within a round */
    @JsonProperty("bet_index")
    val betIndex: Long,
    /** The small blind in the current round. The big blind is twice
     the small blind */
    @JsonProperty("small_blind")
    val smallBlind: Int,
    /** The amount of the largest current bet from any one player */
    @JsonProperty("current_buy_in")
    val currentBuyIn: Long,
    /** The size of the pot (sum of the player bets) */
    val pot: Long,
    /** Minimum raise amount. To raise you have to return at least:
     current_buy_in - players[in_action][bet] + minimum_raise */
    @JsonProperty("minimum_raise")
    val minimumRaise: Long,
    /** The index of the player on the dealer button in this round
     The first player is (dealer+1)%(players.length) */
    val dealer: Long,
    /** Number of orbits completed. (The number of times the dealer
        button returned to the same player.) */
    val orbits: Long,
    /** The index of your player, in the players array */
    @JsonProperty("in_action")
    val inAction: Int,
    /**
     * An array of the players. The order stays the same during the
     *  entire tournament
     */
    val players: List<PlayerInGame>,
    @JsonProperty("community_cards")
    val communityCards: List<Card>,
    @JsonProperty("amount_won")
    val amountWon: Int?
)

