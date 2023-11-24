import poker.player.kotlin.Card
import poker.player.kotlin.PlayerNew
import kotlin.test.Test
import kotlin.test.assertEquals


class SuitedAceKing {

    @Test
    fun aceKingSameSuit() {
        assertEquals(
            true,
            PlayerNew().isSuitedConnection(
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
            PlayerNew().isSuitedConnection(
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
            PlayerNew().isSuitedConnection(
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
            PlayerNew().isSuitedConnection(
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

class SuitedConnectorsTest {

    @Test
    fun connector1() {
        assertEquals(
            true,
            PlayerNew().isSuitedConnection(
                listOf(
                    Card("A", "hearts"),
                    Card("K", "hearts"),
                )
            )
        )
    }

    @Test
    fun connector2() {
        assertEquals(
            true,
            PlayerNew().isSuitedConnection(
                listOf(
                    Card("K", "hearts"),
                    Card("Q", "hearts"),
                )
            )
        )
    }

    @Test
    fun notSuited() {
        assertEquals(
            false,
            PlayerNew().isSuitedConnection(
                listOf(
                    Card("7", "hearts"),
                    Card("6", "spades"),
                )
            )
        )
    }

    @Test
    fun notConnector() {
        assertEquals(
            false,
            PlayerNew().isSuitedConnection(
                listOf(
                    Card("K", "hearts"),
                    Card("J", "hearts"),
                )
            )
        )
    }
}