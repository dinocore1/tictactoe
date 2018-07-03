package devsmart.com.tictactoe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;




public class BoardView extends View implements Game.Listener {

    private Game mGame;

    private Paint mPaint = new Paint();

    public BoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.board, 0, 0);
        try {
            mPaint.setColor(a.getColor(R.styleable.board_color, Color.BLACK));
            int thickness = a.getDimensionPixelSize(R.styleable.board_thickness, 10);
            mPaint.setStrokeWidth(thickness);
        } finally {
            a.recycle();
        }

        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.WHITE);

        int size = getWidth() - (getPaddingTop() + getPaddingBottom() + getPaddingLeft() + getPaddingRight());
        int quarter = size / 4;

        for(int i=0;i<Game.DIM-1;i++) {
            canvas.drawLine(getPaddingLeft(), getPaddingTop() + quarter*(i+1), getWidth() - getPaddingRight(), getPaddingTop() + quarter*(i+1), mPaint);
            canvas.drawLine(getPaddingLeft() + quarter*(i+1), getPaddingTop(), getPaddingLeft() + quarter*(i+1), getHeight() - getPaddingBottom(), mPaint);
        }

        for(int i=0;i<Game.DIM;i++) {
            for(int j=0;j<Game.DIM;j++) {

                Game.State s = mGame.get(i, j);

                int pointX = i*(quarter) + quarter/2;
                int pointY = j*(quarter) + quarter/2;
                int smallerSize = quarter/2 - (quarter/4);

                switch (s) {
                    case Player1:
                        canvas.drawLine(pointX-smallerSize, pointY-smallerSize, pointX+smallerSize, pointY+smallerSize, mPaint);
                        canvas.drawLine(pointX-smallerSize, pointY+smallerSize, pointX+smallerSize, pointY-smallerSize, mPaint);
                        break;

                    case Player2:
                        canvas.drawCircle(pointX, pointY, smallerSize, mPaint);
                        break;
                }

            }
        }

    }

    public void setGame(Game game) {
        mGame = game;
        mGame.addListener(this);
    }

    public int[] getLocation(MotionEvent event) {
        int size = getWidth() - (getPaddingTop() + getPaddingBottom() + getPaddingLeft() + getPaddingRight());
        int quarter = size / Game.DIM;

        int x = ((int)event.getX()) / quarter;
        int y = ((int)event.getY()) / quarter;

        return new int[]{x, y};
    }

    @Override
    public void onStateChanged() {
        invalidate();
    }
}
