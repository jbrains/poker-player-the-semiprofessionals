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

class ContainsPair {
    @Test
    fun hasPair() {
        assertEquals(
            "A",
            PlayerNew().containsPair(
                listOf(
                    Card("A", "hearts"),
                    Card("K", "spades"),
                    Card("A", "spades")
                )
            )
        )
    }

    @Test
    fun noPair() {
        assertEquals(
            null,
            PlayerNew().containsPair(
                listOf(
                    Card("A", "hearts"),
                    Card("K", "spades"),
                    Card("Q", "spades")
                )
            )
        )
    }

    @Test
    fun multiplePairs() {
        assertEquals(
            "A",
            PlayerNew().containsPair(
                listOf(
                    Card("10", "hearts"),
                    Card("10", "spades"),
                    Card("A", "hearts"),
                    Card("A", "spades")
                )
            )
        )
    }
}