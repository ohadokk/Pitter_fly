package com.pushtest.ohad.pitter_fly;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ohad on 1/19/2018.
 */

public class vertex extends View {
    final static float ALIGN_TO_CENTER =100;
    static float buttom= -1;
    static float top= -1;
    static float left= -1;
    static float right= -1;
    DisplayMetrics metrics;

    private Paint p;

    private float x;
    private float y;

    private boolean touch=false;
    public void initButtom(float Buttom,float Top,float Left,float Right){
        if(buttom==-1)
            buttom=Buttom-300 ;
        if(top==-1)
            top=Top-30 ;
        if(left==-1)
            left=Left-30 ;
        if(right==-1)
            right=Right-170 ;
    }
    public float getx() {
        return x;
    }


    public float gety() {
        return y;
    }

    public boolean isTouch() {
        return touch;
    }

    public vertex(Context context) {
        super(context);

        p = new Paint();
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL);

        metrics = context.getResources().getDisplayMetrics();

        x=metrics.widthPixels/2-ALIGN_TO_CENTER;
        y=metrics.heightPixels/3;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(ALIGN_TO_CENTER, ALIGN_TO_CENTER, 70, p);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                touch=true;

                break;
            case MotionEvent.ACTION_MOVE:
                touch=true;

                x=event.getRawX()-125;
                y=event.getRawY()-200;
                if(y>=buttom)
                    y=buttom;
                if(y<=top)
                    y=top;
                if(x<=left)
                    x=left;
                if(x>=right)
                    x=right;
                this.setX(x);
                this.setY(y);

                this.invalidate();

                break;
            case MotionEvent.ACTION_UP:
                touch=false;

                break;

            default:
                return false;
        }
        return true;
    }
}
