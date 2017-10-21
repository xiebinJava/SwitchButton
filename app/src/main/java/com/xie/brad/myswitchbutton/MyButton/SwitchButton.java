package com.xie.brad.myswitchbutton.MyButton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xie.brad.myswitchbutton.R;

import java.util.Date;

/**
 * Created by dell on 2017/10/20.
 */

public class SwitchButton extends View {


    private Bitmap switchButton;
    private Bitmap switchCloseBG;
    private Bitmap switchOpenBG;
    private int bgWitch;
    private int buttonWitch;
    private int height;
    private Paint paint;
    private float downX;
    private float moveX;
    private float BTmoveX;
    private float dx;
    private float maxX;
    private boolean isOpen;
    private float paintWith;
    private Date date;
    private long downTime;
    private long upTime;
    private int widthMeasureSpec;
    private int heightMeasureSpec;
    private int mWidth;
    private int mHeight;
    private int sRight;
    private float sBottom;
    private float sLeft;
    private int sTop;
    private float sWidth;
    private float sHeight;
    private float sCenterX;
    private float sCenterY;
    private Path sPath = new Path();
    private int bLeft;
    private int bTop;
    private float bBottom;
    private float bRight;
    private float bWidth;
    private float bRadius;
    private float bStrokWidth;
    private float sScale;
    private float sScaleCenterX;

    public SwitchButton(Context context) {
        super(context, null);

    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 获取到图片的Bitmap
     *
     * @param context
     */
    private void init(Context context) {
        paint = new Paint();
        date = new Date();
    }

    //自己确定大小~  截图量了一下 算上阴影宽高比例是 149:92 。即 height = width * 0.65 左右
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = (int) (widthSize * 0.65f);
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }



    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w; // 视图自身宽度
        mHeight = h; // 视图自身高度
        sLeft = sTop = 0; // 田径场 左和上的坐标
        sRight = mWidth; // 田径场 右占自身的全部
        sBottom = mHeight * 0.8f; // 田径场底部 占全身的百分之八十， 下面预留百分之二十的空间画按钮阴影。
        sWidth = sRight - sLeft; // 田径场的宽度
        sHeight = sBottom - sTop; // 田径场的高度
        sCenterX = (sRight + sLeft) / 2; // 田径场的X轴中心坐标
        sCenterY = (sBottom + sTop) / 2; // 田径场的Y轴中心坐标
        RectF sRectF = new RectF(sLeft, sTop, sBottom, sBottom);
        sPath.arcTo(sRectF, 90, 180);
        sRectF.left = sRight - sBottom;
        sRectF.right = sRight;
        sPath.arcTo(sRectF, 270, 180);
        sPath.close();    // path准备田径场的路径

        bLeft = bTop = 0;
        bRight = bBottom = sBottom; // 和田径场同高，同宽的节奏， 没错包裹圆形的肯定是个正方形是小孩子都知道的。
        bWidth = bRight - bLeft;
        final float halfHeightOfS = (sBottom - sTop) / 2;
        bRadius = halfHeightOfS * 0.9f; // 按钮的半径
        bStrokWidth = 2 * (halfHeightOfS - bRadius); // 按钮的边框
        sScale = 1 - bStrokWidth / sHeight; //替换之前的0.98<
        sScaleCenterX = sWidth - halfHeightOfS;
        BTmoveX = bWidth / 2;
        maxX = sWidth - bWidth;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        if (isOpen){
            paint.setColor(getResources().getColor(R.color.colorPrimary));
        }else {
            paint.setColor(0xffcccccc);
        }
        canvas.drawPath(sPath, paint); // 画出田径场
        canvas.save();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.colorAccent));
        canvas.drawCircle(BTmoveX, bWidth / 2, bRadius, paint); // 按钮白底
        canvas.restore();
        paint.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downTime = date.getTime();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = event.getX();
                dx = moveX - downX;
                if (!isOpen) {
                    if (dx < 0) {
                        BTmoveX =  bWidth / 2;
                        isOpen = false;
                    } else if (dx > maxX) {
                        BTmoveX = maxX+ (bWidth / 2);
                        isOpen = true;
                    } else {
                        BTmoveX = dx+(bWidth / 2);
                    }
                } else {
                    if (dx > 0) {
                        BTmoveX = maxX+ bWidth / 2;
                        isOpen = true;
                    } else if (Math.abs(dx) > maxX) {
                        BTmoveX =  bWidth / 2;
                        isOpen = false;
                    } else {
                        BTmoveX = maxX-Math.abs(dx)+(bWidth / 2);
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTime = date.getTime();
                if (Math.abs(dx) < 3 && upTime - downTime < 1000) {
                    if (isOpen) {
                        isOpen = false;
                        BTmoveX =  bWidth / 2;
                    } else {
                        isOpen = true;
                        BTmoveX = maxX+bWidth / 2;
                    }
                } else {
                    if (!isOpen) {
                        if (dx < maxX / 2) {
                            BTmoveX =  bWidth / 2;
                            isOpen = false;
                        } else {
                            BTmoveX = maxX+bWidth / 2;
                            isOpen = true;
                        }
                    }else {
                        if (Math.abs(dx) < maxX / 2|| dx>maxX/2 ) {
                            BTmoveX = maxX+bWidth / 2;
                            isOpen = true;
                        } else {
                            BTmoveX =  bWidth / 2;
                            isOpen = false;
                        }
                    }
                }
                invalidate();
                break;
        }
        return true;
    }



}
