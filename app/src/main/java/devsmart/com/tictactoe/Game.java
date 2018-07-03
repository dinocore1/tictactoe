package devsmart.com.tictactoe;

import com.google.common.base.Preconditions;

public class Game {



    public enum State {

        Blank(0),
        Player1(1),
        Player2(2);

        public final int playerNum;

        State(int player) {
            this.playerNum = player;
        }

    }

    public static class WinningConfiguration {

        public WinningConfiguration(int player, String desc) {
            this.player = player;
            this.description = desc;
        }

        public final int player;
        public final String description;

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
        WinningConfiguration retval = null;



        if((retval = testWinner(State.Player1)) != null) {
            return retval;
        }

        if((retval = testWinner(State.Player2)) != null) {
            return retval;
        }


        return retval;
    }

    private WinningConfiguration testWinner(State s) {
        WinningConfiguration retval = null;

        if((retval = testCorners(s)) != null) {
            return retval;
        }

        if((retval = testRows(s)) != null) {
            return retval;
        }

        if((retval = testColumns(s)) != null) {
            return retval;
        }

        if((retval = testDiagonal1(s)) != null) {
            return retval;
        }

        if((retval = testDiagonal2(s)) != null) {
            return retval;
        }

        for(int i=0;i<DIM-1;i++) {
            for(int j=0;j<DIM-1;j++) {
                if((retval = testBox(i, j, s)) != null) {
                    return retval;
                }
            }
        }

        return retval;

    }

    private WinningConfiguration testCorners(State s) {
        if(mSquares[0][0] == s &&
                mSquares[0][DIM-1] == s &&
                mSquares[DIM-1][0] == s &&
                mSquares[DIM-1][DIM-1] == s) {
            return new WinningConfiguration(s.playerNum, "four corners");
        } else {
            return null;
        }
    }

    private WinningConfiguration testRows(State s) {
        WinningConfiguration retval = null;
        for(int i=0;i<DIM;i++) {
            if((retval = testRow(s, i)) != null) {
                return retval;
            }
        }

        return retval;
    }

    private WinningConfiguration testRow(State s, int row) {

        for(int i=0;i<DIM;i++) {
            if(mSquares[row][i] != s) {
                return null;
            }
        }

        return new WinningConfiguration(s.playerNum, "row " + row);
    }

    private WinningConfiguration testColumns(State s) {
        WinningConfiguration retval = null;
        for(int i=0;i<DIM;i++) {
            if((retval = testColumn(s, i)) != null) {
                return retval;
            }
        }

        return retval;
    }

    private WinningConfiguration testColumn(State s, int column) {

        for(int i=0;i<DIM;i++) {
            if(mSquares[i][column] != s) {
                return null;
            }
        }

        return new WinningConfiguration(s.playerNum, "column " + column);
    }

    private WinningConfiguration testDiagonal1(State s) {
        for(int i=0;i<DIM;i++) {
            if(mSquares[i][i] != s) {
                return null;
            }
        }

        return new WinningConfiguration(s.playerNum, "diagonal descending ");
    }

    private WinningConfiguration testDiagonal2(State s) {
        for(int i=0;i<DIM;i++) {
            if(mSquares[i][DIM-(i+1)] != s) {
                return null;
            }
        }

        return new WinningConfiguration(s.playerNum, "diagonal ascesing ");
    }

    private WinningConfiguration testBox(int x, int y, State s) {
        if(mSquares[x][y] == s &&
                mSquares[x+1][y] == s &&
                mSquares[x][y+1] == s &&
                mSquares[x+1][y+1] == s) {
            return new WinningConfiguration(s.playerNum, "box at " + x + " " + y);
        } else {
            return null;
        }
    }


}
