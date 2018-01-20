package com.pushtest.ohad.pitter_fly;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.HashMap;


/**
 * Created by ohad on 1/19/2018.
 */

public class Pit extends ViewGroup{

    public class DataStructure{
        HashMap<Integer,vertex> V = new HashMap<Integer,vertex>();
        HashMap<Integer,edge> E = new HashMap<Integer,edge>();

        protected void connect(){
            for(int i=0;i<E.size();++i) {
                E.get(i).setStart(V.get(i).getx(), V.get(i).gety());
                E.get(i).setStop(V.get(i+1).getx(), V.get(i+1).gety());
            }
        }
        protected  void ChangeX(){
            vertex v;
            for(int i=0;i<V.size();++i){
                if(V.get(i).isTouch()) {
                    if (i!=V.size()-1 && V.get(i).getX() > V.get(i + 1).getX()) {
                        v=V.get(i);
                        V.put(i,V.get(i+1));
                        V.put(i+1,v);

                    }

                    if(i!=0 && V.get(i).getX() < V.get(i-1).getX()){
                        v=V.get(i);
                        V.put(i,V.get(i-1));
                        V.put(i-1,v);
                    }

                }
            }
        }
        public void addToPit(){
            vertex v = new vertex(con);
            edge e   = new   edge(con);
            V.put(graph.V.size(),v);

            addView(v);

            if(V.size()>=2) {
                E.put(E.size(), e);

                addView(e);
            }

        }
    }

    DataStructure graph;

    Context con;
    DisplayMetrics metrics;
    public Pit(Context context, AttributeSet attrs) {
        super(context, attrs);

        con=context;
        metrics = con.getResources().getDisplayMetrics();
        this.measure(metrics.widthPixels,metrics.heightPixels);
        graph =new DataStructure();


        addView(new axis(con));
    }





    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        graph.connect();
        graph.ChangeX();

        return super.dispatchTouchEvent(ev);
    }



    @Override
    protected void onLayout (boolean changed, int l, int t, int r, int b) {
        View child;
        for (int i = 0; i < getChildCount(); ++i) {
              child = getChildAt(i);

              if(child instanceof vertex) {
                  ((vertex) child).initButtom(b,t,l,r);
                  child.layout(metrics.widthPixels / 2 - 100, metrics.heightPixels / 3 , metrics.widthPixels / 2 + 75, metrics.heightPixels / 3 +175);

              }
              else if(child instanceof Button)
                  child.layout(l,b-150,r, b);
              else if(child instanceof edge)
                  child.layout(l,t,r,b);
              else child.layout(l,t,r,b-150);
        }
    }

}
