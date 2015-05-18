package com.aoeng.views.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.aoeng.views.R;

/**
 * TODO: document your custom view class.
 */
public class GuaGuaKa extends View {

    private Drawable mBackDrawable;
    private Drawable mFrontDrawable;


    private Path mPath;
    private int frontColor;

    private Canvas mCanvas;
    //设置画笔
    private Paint mFrontPenPaint;

    private OnClearComplateListener onClearComplateListener;
    private Bitmap mBackBitmap;
    private Bitmap mFrontBitmap;
    private int mLastX;
    private int mLastY;
    private Bitmap mBitmap;

    private int[] mFrontColor;
    private volatile boolean isComplate = false;

    public GuaGuaKa(Context context) {
        this(context, null);
    }

    public GuaGuaKa(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuaGuaKa(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

        mFrontPenPaint = new Paint();


        mPath = new Path();


//        mBackBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fg_guagua);
        setBackgroundResource(R.drawable.fg_guagua);
        mFrontBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fg_guaguaka);
//        mFrontBitmap = Bitmap.createbitmap


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        setFrotPenPaint();
//        mFrontColor = new int[getMeasuredWidth() * getMeasuredHeight()];
//        for (int i = 0; i < getMeasuredWidth() * getMeasuredHeight(); i++) {
//            mFrontColor[i] = Color.parseColor("#c0c0c0");
//        }
//        mFrontBitmap = Bitmap.createBitmap(mFrontColor, getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_4444);
        //绘制表层 颜色
        mFrontBitmap = mBitmap;
//        mCanvas.drawBitmap(mFrontBitmap, 0, 0, null);
        mCanvas.drawColor(Color.parseColor("#c0c0c0"));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mCanvas.drawBitmap(mBackBitmap, getWidth(), getHeight(), null);

        if (isComplate) {
            this.onClearComplateListener.onClearComplate();
        } else {
            canvas.drawBitmap(mBitmap, 0, 0, null);

            drawPath();
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
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                mPath.lineTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_UP:
                new Thread(mClearComplateRunnable).start();
                break;
        }
        invalidate();
        return true;
    }

    private void setFrotPenPaint() {
//        mFrontPenPaint.setColor(Color.parseColor("#c0c0c0"));
        mFrontPenPaint.setColor(Color.WHITE);
        mFrontPenPaint.setAntiAlias(true);
        mFrontPenPaint.setDither(true);
        mFrontPenPaint.setStrokeJoin(Paint.Join.ROUND);
        mFrontPenPaint.setStrokeCap(Paint.Cap.ROUND);
        mFrontPenPaint.setStyle(Paint.Style.FILL);
        mFrontPenPaint.setStrokeWidth(40);
    }


    private void drawPath() {
        mFrontPenPaint.setStyle(Paint.Style.STROKE);
        mFrontPenPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));


        mCanvas.drawPath(mPath, mFrontPenPaint);
    }

    public void setFrontColor(int frontColor) {
        this.frontColor = frontColor;
    }

    public void setOnClearComplateListener(OnClearComplateListener onClearComplateListener) {
        this.onClearComplateListener = onClearComplateListener;
    }

    public interface OnClearComplateListener {
        void onClearComplate();
    }

    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);

    }


    private Runnable mClearComplateRunnable = new Runnable() {
        @Override
        public void run() {
            if (isComplate) {
                return;
            }
            int w = getWidth();
            int h = getHeight();
            float totalArea = w * h;
            float wipeArea = 0;
            int[] pixels = new int[w * h];
            mFrontBitmap.getPixels(pixels, 0, w, 0, 0, w, h);

            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int index = i + j * w;
                    if (pixels[index] == 0) {
                        wipeArea++;
                    }
                }
            }
            if (wipeArea > 0 && totalArea > 0) {
                if (wipeArea * 100 / totalArea > 30) {
                    isComplate = true;
                    postInvalidate();
                }
            }

        }
    };
}
