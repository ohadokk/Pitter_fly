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
  Metrics provide the screen height and width
 */

public class Axis extends View {

    final static int ALIGN_Y_AXIS=100;

    Paint P;
    Context Con;
    DisplayMetrics Metrics;


    public Axis(Context context) {
        super(context);

        Con =context;

        Metrics = Con.getResources().getDisplayMetrics();// get screen boundaries

        P = new Paint();
        P.setColor(Color.GRAY);

    }

    //draw axis by the boundaries of the screen
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine( Metrics.widthPixels/2 ,0                                   , Metrics.widthPixels/2 ,      Metrics.heightPixels                 , P);
        canvas.drawLine( 0                     , Metrics.heightPixels/3+ ALIGN_Y_AXIS,       Metrics.widthPixels   , Metrics.heightPixels/3+ ALIGN_Y_AXIS, P);
        this.invalidate();
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
