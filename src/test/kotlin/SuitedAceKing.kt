import poker.player.kotlin.Card
import poker.player.kotlin.PlayerNew
import kotlin.test.Test
import kotlin.test.assertEquals


class SuitedAceKing {

    @Test
    fun aceKingSameSuit() {
        assertEquals(
            true,
            PlayerNew().isSuitedAceKing(
                listOf(
                    Card("A", "hearts"),
                    Card("K", "hearts"),
                )
            )
        )
    }

    @Test
    fun aceKingSameSuitBackwards() {
        assertEquals(
            true,
            PlayerNew().isSuitedAceKing(
                listOf(
                    Card("K", "hearts"),
                    Card("A", "hearts"),
                )
            )
        )
    }

    @Test
    fun aceKingDifferentSuit() {
        assertEquals(
            false,
            PlayerNew().isSuitedAceKing(
                listOf(
                    Card("A", "hearts"),
                    Card("K", "spades"),
                )
            )
        )
    }

    @Test
    fun aceNotKingSameSuit() {
        assertEquals(
            false,
            PlayerNew().isSuitedAceKing(
                listOf(
                    Card("A", "hearts"),
                    Card("Q", "hearts"),
                )
            )
        )
    }
}