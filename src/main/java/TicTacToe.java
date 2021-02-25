import mongo.TicTacToeBean;

import java.net.UnknownHostException;

public class TicTacToe {
    private Character[][] board = {{'\0', '\0', '\0'}, {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};
    private char lastPlayer = '\0';
    private static final int SIZE = 3;
    private TicTacToeCollection ticTacToeCollection;
    private int turn = 0;

    protected TicTacToeCollection getTicTacToeCollection() {
        return ticTacToeCollection;
    }

    protected TicTacToe(TicTacToeCollection collection) {
        ticTacToeCollection = collection;
        if (!ticTacToeCollection.drop()) {
            throw new RuntimeException("Dropping DB collection failed");
        }
    }

    public TicTacToe() throws UnknownHostException {
        this(new TicTacToeCollection());
    }

    public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
        setBox(new TicTacToeBean(++turn, x, y, lastPlayer));
        if (isWin(x, y)) {
            return lastPlayer + " is the winner";
        } else if (isDraw()) {
            return "The result is draw";
        } else {
            return "No winner";
        }
    }

    private boolean isWin(int x, int y) {
        int playerTotal = lastPlayer * 3;
        char horizontal, vertical, diagonal1, diagonal2;
        horizontal = vertical = diagonal1 = diagonal2 = '\0';
        for (int i = 0; i < SIZE; i++) {
            horizontal += board[i][y - 1];
            vertical += board[x - 1][i];
            diagonal1 += board[i][i];
            diagonal2 += board[i][SIZE - i - 1];
        }
        return horizontal == playerTotal ||
                vertical == playerTotal ||
                diagonal1 == playerTotal ||
                diagonal2 == playerTotal;
    }

    private void setBox(TicTacToeBean bean) {
        if (board[bean.getX() - 1][bean.getY() - 1] != '\0') {
            throw new RuntimeException("Box is occupied");
        } else {
            board[bean.getX() - 1][bean.getY() - 1] = lastPlayer;
            if (!getTicTacToeCollection().saveMove(bean)) {
                throw new RuntimeException("Saving to DB failed");
            }
        }
    }

    public void checkAxis(int axis) {
        if (axis < 1 || axis > 3) {
            //X represents the place not the Axis x
            throw new RuntimeException("X is outside the board");
        }
    }

    public char nextPlayer() {
        if (lastPlayer == 'X') {
            return 'O';
        }
        return 'X';
    }

    private boolean isDraw() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (board[x][y] == '\0') return false;
            }
        }
        return true;
    }
}
