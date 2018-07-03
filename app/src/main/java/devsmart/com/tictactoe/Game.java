package devsmart.com.tictactoe;

import com.google.common.base.Preconditions;

public class Game {



    public enum State {
        Blank,
        Player1,
        Player2
    }

    public interface WinningConfiguration {

        int player();
        String discription();
    }

    public static final int DIM = 4;
    private final State[][] mSquares = new State[DIM][DIM];


    public Game() {
        reset();
    }

    public void reset() {
        for(int i=0;i<DIM;i++) {
            for(int j=0;j<DIM;j++) {
                mSquares[i][j] = State.Blank;
            }
        }
    }

    public void set(int x, int y, State state) {
        Preconditions.checkArgument(0 <= x && x < DIM, "x not in range");
        Preconditions.checkArgument(0 <= y && y < DIM, "y not in range");
        mSquares[x][y] = state;
    }

    public State get(int x, int y) {
        Preconditions.checkArgument(0 <= x && x < DIM, "x not in range");
        Preconditions.checkArgument(0 <= y && y < DIM, "y not in range");
        return mSquares[x][y];
    }

    /**
     * @return instance of WinningConfiguration or null if no winner is found
     */
    public WinningConfiguration findWinner() {
        return null;

    }


}
