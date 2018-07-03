package devsmart.com.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;


public class MainActivity extends Activity {

    private BoardView mBoadView;
    private Game mGame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGame = new Game();

        setContentView(R.layout.main);

        mBoadView = (BoardView) findViewById(R.id.board);
        mBoadView.setGame(mGame);

    }
}
