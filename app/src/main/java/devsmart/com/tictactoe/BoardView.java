package devsmart.com.tictactoe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;




public class BoardView extends View {

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
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int size = getWidth() - (getPaddingTop() + getPaddingBottom() + getPaddingLeft() + getPaddingRight());
        int quarter = size / 4;

        for(int i=0;i<Game.DIM-1;i++) {
            canvas.drawLine(getPaddingLeft(), getPaddingTop() + quarter*i, getPaddingRight(), getPaddingTop() + quarter*i, mPaint);
        }

    }

    public void setGame(Game game) {
        mGame = game;
    }
}
