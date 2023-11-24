import poker.player.kotlin.HoleCard
import poker.player.kotlin.PlayerNew
import kotlin.test.Test
import kotlin.test.assertEquals


class SuitedAceKing {

    @Test
    fun aceKingSameSuit() {
        assertEquals(true,
            PlayerNew().isSuitedAceKing(
                HoleCard("A", "hearts"),
                HoleCard("K", "hearts"),
            )
        )
    }

    @Test
    fun aceKingSameSuitBackwards() {
        assertEquals(true,
            PlayerNew().isSuitedAceKing(
                HoleCard("K", "hearts"),
                HoleCard("A", "hearts"),
            )
        )
    }

    @Test
    fun aceKingDifferentSuit() {
        assertEquals(false,
            PlayerNew().isSuitedAceKing(
                HoleCard("A", "hearts"),
                HoleCard("K", "spades"),
            )
        )
    }

    @Test
    fun aceNotKingSameSuit() {
        assertEquals(false,
            PlayerNew().isSuitedAceKing(
                HoleCard("A", "hearts"),
                HoleCard("Q", "hearts"),
            )
        )
    }
}