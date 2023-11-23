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
    val rank: String,
    val suit: String,
)
