package poker.player.kotlin

import kotlinx.serialization.Serializable
import org.json.JSONArray
import org.json.JSONObject

class Player {
    fun betRequest(game_state: JSONObject): Int {
        println(game_state)
        val players = game_state["players"] as JSONArray
        val us = players[3] as JSONObject
        val holeCards = us["hole_cards"] as JSONArray
        val first = holeCards[0] as JSONObject
        val second = holeCards[1] as JSONObject

        println(holeCards)
        return (if (isHighValuePair(first, second)) {
            betAllIn(us)
        } else {
            fold(us)
        })
    }

    private fun fold(player: JSONObject) = 0

    private fun betAllIn(player: JSONObject): Int = player["stack"] as Int

    private fun isHighValuePair(first: JSONObject, second: JSONObject) =
        isPair(first, second) && (first["rank"] == "A" || first["rank"] == "K")

    private fun isPair(first: JSONObject, second: JSONObject) = first["rank"] == second["rank"]

    fun showdown() {
    }

    fun version(): String {
        return "Kotlin Player 0.0.1"
    }
}

@Serializable
data class GameState(
    // Id of the current tournament
    val tournament_id: String,

// Id of the current sit'n'go game. You can use this to link a
// sequence of game states together for logging purposes, or to
// make sure that the same strategy is played for an entire game
val game_id: String,

//// Index of the current round within a sit'n'go
//"round":0,
//
//// Index of the betting opportunity within a round
//"bet_index":0,
//
//// The small blind in the current round. The big blind is twice
//// the small blind
//"small_blind": 10,
//
//// The amount of the largest current bet from any one player
//"current_buy_in": 320,
//
//// The size of the pot (sum of the player bets)
//"pot": 400,
//
//// Minimum raise amount. To raise you have to return at least:
//// current_buy_in - players[in_action][bet] + minimum_raise
//"minimum_raise": 240,
//
//// The index of the player on the dealer button in this round
//// The first player is (dealer+1)%(players.length)
//"dealer": 1,
//
//// Number of orbits completed. (The number of times the dealer
////     button returned to the same player.)
//"orbits": 7,
//
//// The index of your player, in the players array
//"in_action": 1,
//
//// An array of the players. The order stays the same during the
////     entire tournament
    val players: List<PlayerData>
//// Finally the array of community cards.
//"community_cards": [
//{
//    "rank": "4",
//    "suit": "spades"
//},
//{
//    "rank": "A",
//    "suit": "hearts"
//},
//{
//    "rank": "6",
//    "suit": "clubs"
//}
//]

)

@Serializable
data class PlayerData(
    val id: Int
//    // Your own player looks similar, with one extension.
//    "id": 1,
//    "name": "Bob",
//    "status": "active",
//    "version": "Default random player",
//    "stack": 1590,
//    "bet": 80,
//    // The cards of the player. This is only visible for
//    // your own player except after showdown, when cards
//    // revealed are also included.
//    "hole_cards": [
//    {
//        // Rank of the card. Possible values are
//        // numbers 2-10 and J,Q,K,A
//        "rank": "6",
//        // Suit of the card. Possible values are:
//        // clubs,spades,hearts,diamonds
//        "suit": "hearts"
//    },
//    {
//        "rank": "K",
//        "suit": "spades"
//    }
)