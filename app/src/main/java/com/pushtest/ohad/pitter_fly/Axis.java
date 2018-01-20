package com.pushtest.ohad.pitter_fly;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;


/*
Axis is a class to draw the axis on the screen
 it use onDraw to draw two lines
  metrics provide the screen height and width
 */

public class Axis extends View {

    final static int ALIGN_Y_AXIS=100;

    Paint p;
    DisplayMetrics metrics;


    public Axis(Context context) {
        super(context);

        metrics = context.getResources().getDisplayMetrics();// get screen boundaries

        p = new Paint();
        p.setColor(Color.GRAY);

    }

    //draw axis by the boundaries of the screen
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine( metrics.widthPixels/2 ,0                                   ,metrics.widthPixels/2 ,      metrics.heightPixels                 , p);
        canvas.drawLine( 0                     ,metrics.heightPixels/3+ ALIGN_Y_AXIS,       metrics.widthPixels   ,metrics.heightPixels/3+ ALIGN_Y_AXIS, p);
        this.invalidate();
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
