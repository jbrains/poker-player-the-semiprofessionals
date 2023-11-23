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
    // Rank of the card. Possible values are
    // numbers 2-10 and J,Q,K,
    val rank: String,
    // Suit of the card. Possible values are:
    // clubs,spades,hearts,diamonds
    val suit: String,
)


data class Game(
    @JsonProperty("tournament_id")
    val tournamentId: String,
    @JsonProperty("game_id")
    val gameId: String,
    val round: Long,
    @JsonProperty("bet_index")
    val betIndex: Long,
    @JsonProperty("small_blind")
    val smallBlind: Long,
    @JsonProperty("current_buy_in")
    val currentBuyIn: Long,
    val pot: Long,
    @JsonProperty("minimum_raise")
    val minimumRaise: Long,
    val dealer: Long,
    val orbits: Long,
    @JsonProperty("in_action")
    val inAction: Long,
    val players: List<PlayerInGame>,
    @JsonProperty("community_cards")
    val communityCards: List<CommunityCard>,
)

data class CommunityCard(
    val rank: String,
    val suit: String,
)

