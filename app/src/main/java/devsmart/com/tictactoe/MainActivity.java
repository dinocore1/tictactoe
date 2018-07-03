package devsmart.com.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private BoardView mBoadView;
    private Game mGame;
    private Button mNewGameButton;
    private Game.State mCurrentPlayer = Game.State.Player2;
    private TextView mPlayerText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGame = new Game();

        setContentView(R.layout.main);

        mPlayerText = (TextView) findViewById(R.id.player);

        mNewGameButton = (Button) findViewById(R.id.reset);
        mNewGameButton.setOnClickListener(mOnNewGameClicked);

        mBoadView = (BoardView) findViewById(R.id.board);
        mBoadView.setGame(mGame);

        mBoadView.setOnTouchListener(mBoardTouchListener);

        togglePlayer();

    }

    private final View.OnClickListener mOnNewGameClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mGame.reset();
        }
    };

    private final View.OnTouchListener mBoardTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int[] point = mBoadView.getLocation(event);


            int action = event.getActionMasked();
            switch (action) {
                case MotionEvent.ACTION_UP:
                    mGame.set(point[0], point[1], mCurrentPlayer);
                    checkWinner();
                    togglePlayer();
                    break;
            }


            return true;
        }
    };

    private void togglePlayer() {
        switch (mCurrentPlayer) {
            case Player1:
                mCurrentPlayer = Game.State.Player2;
                break;

            case Player2:
                mCurrentPlayer = Game.State.Player1;
                break;
        }


        mPlayerText.setText("Current Player: " + (getPlayer(mCurrentPlayer.playerNum)));
    }

    private static String getPlayer(int player) {
        switch (player) {
            case 1:
                return "X";

            case 2:
                return "O";

            default:
                return "";
        }

    }

    private void checkWinner() {
        Game.WinningConfiguration winning = mGame.findWinner();

        if(winning != null) {
            String msg = String.format("Winner: Player %s. %s", getPlayer(winning.player), winning.description);
            new AlertDialog.Builder(this)
                    .setMessage(msg)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mGame.reset();
                        }
                    })
                    .show();
        }
    }
}
