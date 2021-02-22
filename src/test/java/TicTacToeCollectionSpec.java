import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeCollectionSpec {
    TicTacToeCollection collection;

    @BeforeEach
    public void before() throws UnknownHostException {
        collection = new TicTacToeCollection();
    }

    @Test
    public void whenInstantiatedThenMongoHasDbNameTicTacToe() {
        assertEquals("tic-tac-toe", collection.getMongoCollection().getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame() throws UnknownHostException {
        assertEquals("game", collection.getMongoCollection().getDBCollection().getName());
    }
}