package com.pushtest.ohad.pitter_fly;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/*
Edge is a class for storing and drawing the line connecting the vertexes
each class instance is a line between two vertexes
 */

public class Edge extends View {

    final static int ALIGN_EDGE_TO_VERTEX_CENTER = 100;

    //float to save the vertexes the edge connect
    //+++++++++++++
    float Startx;
    float Starty;
    float Stopx;
    float Stopy;
    //+++++++++++++

    Paint P;

    public Edge(Context context) {
        super(context);

        P = new Paint();
        P.setColor(Color.BLUE);
        P.setStyle(Paint.Style.FILL);

    }

    /*set vertex x,y position that the edge need to connect ,
      each function for different edge*/
    //+++++++++++++++++++++++++++++++++++++++++++++++
    public void setStart(float startx,float starty) {
        Startx = startx;
        Starty = starty;
    }

    public void setStop(float stopx,float stopy) {
        Stopx = stopx;
        Stopy = stopy;
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++

    //draw the line between two vertexes
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(Startx+ALIGN_EDGE_TO_VERTEX_CENTER ,Starty+ALIGN_EDGE_TO_VERTEX_CENTER ,
                        Stopx+ALIGN_EDGE_TO_VERTEX_CENTER  ,Stopy+ALIGN_EDGE_TO_VERTEX_CENTER  , P);
        this.invalidate();
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
