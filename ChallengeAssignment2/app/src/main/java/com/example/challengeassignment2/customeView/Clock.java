package com.example.challengeassignment2.customeView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Calendar;

public class Clock extends View {


    private int mheight, mwidth = 0;
    private int mpadding = 0;
    private int mfontsize = 0;
    private int mnumeralSapacing = 0;
    private int mhandTruncation, mhourHandTruncation = 0;
    private int mradius = 0;
    private Paint mpaint;
    private boolean misInit;
    private int[] mnumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private Rect mrect = new Rect();

    public Clock(Context context) {
        super(context);
    }

    public Clock(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Clock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * TO INITIALIZED VARIABLE
     */
    private void initClock() {
        mheight = getHeight();
        mwidth = getWidth();
        mpadding = mnumeralSapacing + 60;
        mfontsize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 22,
                getResources().getDisplayMetrics());
        int min = Math.min(mheight, mwidth);
        mradius = min / 2 - mpadding;
        mhandTruncation = min / 20;
        mhourHandTruncation = min / 7;
        mpaint = new Paint();
        misInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!misInit) {
            initClock();
        }
        canvas.drawColor(Color.BLACK);
        drawCircle(canvas);
        drawCenter(canvas);
        drawNumber(canvas);
        drawHands(canvas);
        postInvalidateDelayed(500);
        invalidate();
    }

    /**
     * TO DRAW A CIRCLE FOR CLOCK
     *
     * @param canvas WHICH IS USE TO  DRAW SHAPE
     */
    private void drawCircle(Canvas canvas) {
        mpaint.reset();
        mpaint.setColor(getResources().getColor(android.R.color.black));
        mpaint.setStrokeWidth(5);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setAntiAlias(true);
        canvas.drawCircle(mwidth / 2, mheight / 2, mradius + mpadding - 10, mpaint);
    }

    /**
     * TO DRAW A CENTER OF CLOCK
     *
     * @param canvas WHICH IS USE TO  DRAW SHAPE
     */
    private void drawCenter(Canvas canvas) {
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        canvas.drawCircle(mwidth / 2, mheight / 2, 19, mpaint);
    }

    /**
     * TO   DRAW A NUMBER IN CIRCLE
     *
     * @param canvas WHICH IS USE TO  DRAW SHAPE
     */
    private void drawNumber(Canvas canvas) {
        mpaint.setTextSize(mfontsize);

        for (int number : mnumbers) {
            String tmp = String.valueOf(number);
            mpaint.getTextBounds(tmp, 0, tmp.length(), mrect);
            double angle = Math.PI / 6 * (number - 3);
            int x = (int) (mwidth / 2 + Math.cos(angle) * mradius - mrect.width() / 2);
            int y = (int) (mheight / 2 + Math.sin(angle) * mradius + mrect.height() / 2);
            canvas.drawText(tmp, x, y, mpaint);
        }
    }

    /**
     * MINITE CODE TO DRAW A HANDS
     *
     * @param canvas WHICH IS USE TO  DRAW SHAPE
     */
    private void drawHands(Canvas canvas) {
        Calendar c = Calendar.getInstance();
        float hour = c.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;
        drawHand(canvas, (hour + c.get(Calendar.MINUTE) / 60.0) * 5f, true);
        drawHand(canvas, c.get(Calendar.MINUTE), false);
        drawHand(canvas, c.get(Calendar.SECOND), false);
    }

    private void drawHand(Canvas canvas, double loc, boolean isHour) {
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        int handRadius = isHour ? mradius - mhandTruncation - mhourHandTruncation : mradius - mhandTruncation;
        canvas.drawLine(mwidth / 2, mheight / 2,
                (float) (mwidth / 2 + Math.cos(angle) * handRadius),
                (float) (mheight / 2 + Math.sin(angle) * handRadius),
                mpaint);
    }


}
