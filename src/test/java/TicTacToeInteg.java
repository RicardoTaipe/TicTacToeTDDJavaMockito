import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicTacToeInteg {
    @Test
    public void givenMongoDbIsRunningWhenPlayThenNoException()
            throws UnknownHostException {
        TicTacToe ticTacToe = new TicTacToe();
        assertEquals("No winner", ticTacToe.play(1, 1));
    }
}
