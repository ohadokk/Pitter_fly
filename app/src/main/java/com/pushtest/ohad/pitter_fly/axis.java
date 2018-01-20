package com.pushtest.ohad.pitter_fly;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by ohad on 1/19/2018.
 */

public class axis extends View {
    DisplayMetrics metrics;
    Paint p;
    public axis(Context context) {
        super(context);
        metrics = context.getResources().getDisplayMetrics();
        p=new Paint();
        p.setColor(Color.GRAY);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine( metrics.widthPixels/2,0, metrics.widthPixels/2,metrics.heightPixels,p);
        canvas.drawLine( 0,metrics.heightPixels/3+ 100, metrics.widthPixels,metrics.heightPixels/3+ 100,p);
        this.invalidate();
    }
}
