package com.aoeng.views.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.aoeng.views.R;

/**
 * TODO: document your custom view class.
 */
public class GuaGuaView extends View {
    private boolean mTextBold;
    private Paint mOutterPaint;
    private Path mPath;
    private Bitmap mBitMap;
    private Canvas mCanvas;
    private Bitmap mOutImage;
    private String mText;
    private Rect mTextBound;
    private Paint mBackPaint;
    private int mTextSize;
    private int mTextColor;
    private int mLastX;
    private int mLastY;

    private volatile boolean isComplate = false;
    private OnClearPercentListener onClearPercentListener;
    private volatile int mClearPercent = 0;


    public GuaGuaView(Context context) {
        this(context, null);

    }

    public GuaGuaView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public GuaGuaView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GuaGuaView, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.GuaGuaView_textSize:
                    mTextSize = (int) a.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 22, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.GuaGuaView_text:
                    mText = a.getString(attr);
                    break;
                case R.styleable.GuaGuaView_textBold:
                    mTextBold = a.getBoolean(attr, false);
                    break;
                case R.styleable.GuaGuaView_textColor:
                    mTextColor = a.getColor(attr, 0x000000);
                    break;
            }
        }


        a.recycle();


    }

    private void init() {
        mOutterPaint = new Paint();
        mPath = new Path();

        mOutImage = BitmapFactory.decodeResource(getResources(), R.drawable.fg_guaguaka);

        mText = "谢谢惠顾";
        mTextBound = new Rect();
        mBackPaint = new Paint();

        mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 40,
                getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mBitMap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(mBitMap);

        setOutPutPaint();
        setupBackPaint();

        mCanvas.drawRoundRect(new RectF(0, 0, width, height), 30, 30, mOutterPaint);
        mCanvas.drawBitmap(mOutImage, null, new Rect(0, 0, width, height), null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mText, getWidth() / 2 - mTextBound.width() / 2, getHeight() / 2 + mTextBound.height() / 2,
                mBackPaint);


        if (isComplate) {
            if (null != onClearPercentListener) {
                onClearPercentListener.loadClearPercent(mClearPercent);
            }
        } else {
            drawPath();
            canvas.drawBitmap(mBitMap, 0, 0, null);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int startX = (int) event.getX();
        int startY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = startX;
                mLastY = startY;
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getX();
                int dy = (int) event.getY();
                mLastX = dx;
                mLastY = dy;
                mPath.lineTo(dx, dy);
                break;
            case MotionEvent.ACTION_UP:
                new Thread(mClearPercentRunnable).start();
                break;
        }
        invalidate();
        return true;
    }

    private void drawPath() {
        mOutterPaint.setStyle(Paint.Style.STROKE);
        mOutterPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        mCanvas.drawPath(mPath, mOutterPaint);
    }

    private void setOutPutPaint() {
        mOutterPaint.setColor(Color.parseColor("#c0c0c0"));
        mOutterPaint.setAntiAlias(true);
        mOutterPaint.setDither(true);
        mOutterPaint.setStrokeJoin(Paint.Join.ROUND);
        mOutterPaint.setStrokeCap(Paint.Cap.ROUND);
        mOutterPaint.setStyle(Paint.Style.FILL);
        mOutterPaint.setStrokeWidth(20);
    }

    private void setupBackPaint() {
        mBackPaint.setColor(mTextColor);
        mBackPaint.setStyle(Paint.Style.FILL);
        mBackPaint.setTextSize(mTextSize);
        mBackPaint.setFakeBoldText(mTextBold);
        mBackPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
    }

    public void setOnClearPercentListener(OnClearPercentListener onClearPercentListener) {
        this.onClearPercentListener = onClearPercentListener;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public interface OnClearPercentListener {
        public void loadClearPercent(int percent);
    }

    private Runnable mClearPercentRunnable = new Runnable() {
        @Override
        public void run() {
            int w = getWidth();
            int h = getHeight();
            float totalArea = w * h;
            float wipeArea = 0;
            Bitmap bitmap = mBitMap;
            int[] mPiexls = new int[w * h];
            bitmap.getPixels(mPiexls, 0, w, 0, 0, w, h);
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int index = i + j * w;

                    if (mPiexls[index] == 0) {
                        wipeArea++;
                    }
                }
            }
            if (wipeArea > 0 && totalArea > 0) {
                mClearPercent = (int) (wipeArea * 100 / totalArea);
                if (mClearPercent >= 50) {
                    isComplate = true;
                }
                postInvalidate();
            }
        }
    };
}
