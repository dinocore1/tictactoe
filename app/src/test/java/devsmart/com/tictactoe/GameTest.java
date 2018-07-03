package devsmart.com.tictactoe;

import org.junit.Test;

import static junit.framework.Assert.*;

public class GameTest {

    @Test(expected = RuntimeException.class)
    public void testOutOfBoundsNegitive() {
        Game g = new Game();
        g.set(-1, 2, Game.State.Player1);
    }

    @Test(expected = RuntimeException.class)
    public void testOutOfBoundsPositive() {
        Game g = new Game();
        g.set(Game.DIM, 1, Game.State.Player1);
    }

    @Test
    public void testSetState() {
        Game g = new Game();
        g.set(2, 1, Game.State.Player1);
        assertEquals(Game.State.Player1, g.get(2, 1));
        assertEquals(Game.State.Blank, g.get(0, 0));
    }

    @Test
    public void testReset() {
        Game g = new Game();

        g.set(0, 1, Game.State.Player1);
        g.reset();

        for(int i=0;i<Game.DIM;i++){
            for(int j=0;j<Game.DIM;j++) {
                assertEquals(Game.State.Blank, g.get(i, j));
            }
        }
    }

    @Test
    public void testNoWinner() {
        Game.WinningConfiguration winning;
        Game g = new Game();
        g.reset();
        winning = g.findWinner();
        assertNull(winning);

        g.set(0, 0, Game.State.Player1);
        winning = g.findWinner();
        assertNull(winning);

        g.set(0, 1, Game.State.Player2);
        winning = g.findWinner();
        assertNull(winning);
    }

    @Test
    public void testWinColumn() {
        Game g = new Game();

        for(int i=0;i<Game.DIM;i++) {
            g.set(0, i, Game.State.Player1);
        }

        Game.WinningConfiguration winning = g.findWinner();
        assertNotNull(winning);
        assertEquals(1, winning.player());

    }

    @Test
    public void testWinRow() {
        Game g = new Game();

        for(int i=0;i<Game.DIM;i++) {
            g.set(i, 0, Game.State.Player1);
        }

        Game.WinningConfiguration winning = g.findWinner();
        assertNotNull(winning);
        assertEquals(1, winning.player());

    }

    @Test
    public void testWinCorners() {
        Game g = new Game();

        g.set(0, 0, Game.State.Player1);
        g.set(0, 3, Game.State.Player1);
        g.set(3, 0, Game.State.Player1);
        g.set(3, 3, Game.State.Player1);

        Game.WinningConfiguration winning = g.findWinner();
        assertNotNull(winning);
        assertEquals(1, winning.player());

    }

    @Test
    public void testWinDiagonal1() {
        Game g = new Game();

        for(int i=0;i<Game.DIM;i++) {
            g.set(i, i, Game.State.Player1);
        }

        Game.WinningConfiguration winning = g.findWinner();
        assertNotNull(winning);
        assertEquals(1, winning.player());

    }

    @Test
    public void testWinDiagonal2() {
        Game g = new Game();

        for(int i=0;i<Game.DIM;i++) {
            g.set(i, Game.DIM-(i+1), Game.State.Player1);
        }

        Game.WinningConfiguration winning = g.findWinner();
        assertNotNull(winning);
        assertEquals(1, winning.player());

    }

    private static void fillBoxAt(Game g, int x, int y, Game.State state) {
        g.set(x, y, state);
        g.set(x+1, y, state);
        g.set(x, y+1, state);
        g.set(x+1, y+1, state);
    }

    @Test
    public void testWinBox() {

        for(int i=0;i<Game.DIM-1;i++) {
            for(int j=0;j<Game.DIM-1;j++) {

                Game g = new Game();

                fillBoxAt(g, i, j, Game.State.Player1);
                Game.WinningConfiguration winning = g.findWinner();
                assertNotNull(winning);
                assertEquals(1, winning.player());

            }
        }

    }

}
