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

public class Vertex extends View {
    final static float RADIUS  = 70;//set in onDraw circle radius

    final static float ALIGN_TO_CENTER  = 100;//align the circle to the center of vertex layout
    //align the boundaries of vertex mobility on screen
    //+++++++++++++++++++++++++++++++++++++++++
    final static int ALIGN_BOTTOM_BOUND = -300;
    final static int ALIGN_TOP_BOUND    = -30;
    final static int ALIGN_LEFT_BOUND   = -30;
    final static int ALIGN_RIGHT_BOUND  = -170;
    //+++++++++++++++++++++++++++++++++++++++++

    //align the touch event to be under the finger
    //++++++++++++++++++++++++++++++++++++++++++++
    final static int ALIGN_FINGER_TOUCH_X = -125;
    final static int ALIGN_FINGER_TOUCH_Y = -200;
    //++++++++++++++++++++++++++++++++++++++++++++

    //variables to get the screen size for the vertex mobility boundaries
    //++++++++++++++++++++++++
    static float Bottom = -1;
    static float Top = -1;
    static float Left = -1;
    static float Right = -1;
    //++++++++++++++++++++++++

    DisplayMetrics Metrics;

    Context Con;

    Paint P;

    //variables to save touch event x,y position
    //++++++++++++
    float TouchX;
    float TouchY;
    //++++++++++++

    //flag for on touch event
    //++++++++++++++++++
    boolean touch=false;
    //++++++++++++++++++

    /*when screen boundaries is given from the Pit the function
      save them for all vertexes for mobility boundaries*/
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void initBoundaries(float initBottom, float initTop, float initLeft, float initRight){
        if(Bottom == -1)
            Bottom = initBottom + ALIGN_BOTTOM_BOUND;
        if(Top == -1)
            Top = initTop + ALIGN_TOP_BOUND;
        if(Left == -1)
            Left = initLeft + ALIGN_LEFT_BOUND;
        if(Right == -1)
            Right = initRight + ALIGN_RIGHT_BOUND;
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public float getx() {
        return TouchX;
    }

    public float gety() {
        return TouchY;
    }

    public boolean isTouch() {
        return touch;
    }

    public Vertex(Context context) {
        super(context);

        Con = context;

        P = new Paint();
        P.setColor(Color.BLUE);
        P.setStyle(Paint.Style.FILL);

        Metrics = Con.getResources().getDisplayMetrics();

        //for initialization the touch variables are positioned on the screen center
        //+++++++++++++++++++++++++++++++++++++++++++++
        TouchX = Metrics.widthPixels/2-ALIGN_TO_CENTER;
        TouchY = Metrics.heightPixels/3;
        //+++++++++++++++++++++++++++++++++++++++++++++
    }
    //draw the circle of the vertex on the view layout center
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(ALIGN_TO_CENTER, ALIGN_TO_CENTER, RADIUS, P);
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /*when called set the vertex view to the correct position*/
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                touch=true;//turn true to indicate this vertex is been touched

                break;
            case MotionEvent.ACTION_MOVE:
                touch=true;//turn true to indicate this vertex is been touched

                //get the touch x,y position
                //+++++++++++++++++++++++++++++++++++++++++++++
                TouchX =event.getRawX() + ALIGN_FINGER_TOUCH_X;
                TouchY =event.getRawY() + ALIGN_FINGER_TOUCH_Y;
                //+++++++++++++++++++++++++++++++++++++++++++++

                /*check the touch position bound
                 if outbound set it to the border*/
                //++++++++++++++++++
                if(TouchY >= Bottom)
                    TouchY = Bottom;
                if(TouchY <= Top)
                    TouchY = Top;
                if(TouchX <= Left)
                    TouchX = Left;
                if(TouchX >= Right)
                    TouchX = Right;
                //++++++++++++++++++

                //set view to new position
                //++++++++++++++++
                this.setX(TouchX);
                this.setY(TouchY);
                //++++++++++++++++

                this.invalidate();

                break;
            case MotionEvent.ACTION_UP:
                touch=false;//turn false for touch event is over

                break;

            default:

                return false;
        }

        return true;
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
