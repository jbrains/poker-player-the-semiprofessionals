package poker.player.kotlin
import com.fasterxml.jackson.annotation.JsonProperty

data class PlayerInGame(
    val id: Long,
    val name: String,
    val status: String,
    val version: String,
    val stack: Long,
    val bet: Long,
    @JsonProperty("hole_cards")
    val holeCards: List<HoleCard>,
)

data class HoleCard(
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
    val smallBlind: Long,
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
    val communityCards: List<CommunityCard>,
)

data class CommunityCard(
    val rank: String,
    val suit: String,
)

