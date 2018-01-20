package com.pushtest.ohad.pitter_fly;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by ohad on 1/19/2018.
 */

public class edge extends View {


    public void setStart(float startx,float starty) {
        Startx = startx;
        Starty = starty;
    }

    public void setStop(float stopx,float stopy) {
        Stopx = stopx;
        Stopy = stopy;
    }

    float Startx;
    float Starty;
    float Stopx;
    float Stopy;
    Paint p;
    public edge(Context context) {
        super(context);
        p = new Paint();
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(Startx+100,Starty+100,Stopx+100,Stopy+100,p);
        this.invalidate();
    }
}
