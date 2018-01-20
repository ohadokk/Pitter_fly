package com.pushtest.ohad.pitter_fly;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.HashMap;


/*
Pit class manage all the views (vertexes ,edges ,axis ,button)
 Pit have a class called DataStructure that manage the responsivity of each edge
 based on vertexes X position

 */

public class Pit extends ViewGroup{

    //statics to align the bound of vertex layout
    //+++++++++++++++++++++++++++++++++++++++++++++++++
    final static int VERTEX_VIEW_LEFT_BOUND   = -100;
    final static int VERTEX_VIEW_RIGHT_BOUND  = 75;
    final static int VERTEX_VIEW_BUTTOM_BOUND = 175;
    //+++++++++++++++++++++++++++++++++++++++++++++++++

    //statics to align the bound of the button layout
    //+++++++++++++++++++++++++++++++++++++++++++++++++
    final static int VIEW_BOTTOM_BOUND = -150;
    //+++++++++++++++++++++++++++++++++++++++++++++++++

    Context Con;
    DataStructure Graph; // instance of DataStructure for managing the vertexes connectivity
    DisplayMetrics Metrics; // use to obtain screen height and width

    public Pit(Context context, AttributeSet attrs) {
        super(context, attrs);

        Con =context;

        Metrics = Con.getResources().getDisplayMetrics(); // get screen height and width
        this.measure(Metrics.widthPixels, Metrics.heightPixels); //set Pit boundaries on screen boundaries

        Graph =new DataStructure();

        addView(new Axis(Con)); //add to Pit the axis
    }

    /*on each screen touch the dispatchTouchEvent dispatch
    the event to the vertex that is touched
    Graph connect and ChangeX is called to change edges connectivity if needed*/
    //++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Graph.connect();
        Graph.ChangeX();

        return super.dispatchTouchEvent(ev);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++

    /*when Pit layout is changed (vertexes added to Pit , initialize Pit )
     onLayout is called. it set bounders of each child view*/
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    protected void onLayout (boolean changed, int l, int t, int r, int b) {
        View child;

        for (int i = 0 ; i < getChildCount() ; ++i) {
              child = getChildAt(i);

              /*if child is a vertex the layout of the child is sized to fit the vertex radius (currently 70) */
              //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
              if(child instanceof Vertex) {

                  ((Vertex) child).initBoundaries(b , t , l , r);

                  child.layout(Metrics.widthPixels / 2 + VERTEX_VIEW_LEFT_BOUND  , Metrics.heightPixels / 3,
                               Metrics.widthPixels / 2 + VERTEX_VIEW_RIGHT_BOUND , Metrics.heightPixels / 3 + VERTEX_VIEW_BUTTOM_BOUND);
              }
              //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

              else if(child instanceof Button)
                  child.layout(l ,b + VIEW_BOTTOM_BOUND, r , b); //bound the layout from bottom to VIEW_BOTTOM_BOUND above
              else if(child instanceof Edge)
                  child.layout(l , t , r , b);
              else
                  child.layout(l , t , r ,b + VIEW_BOTTOM_BOUND); // for axis view ,bound so it wont overlap the button
        }
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /*the DataStructure contains two HashMaps, one for vertexes and one for edges.
      the DataStructure is responsible for responsive connectivity
      it does it by swapping vertexes on the hash map if one vertex X is bigger or smaller
      then the one next to him on the map . each edge is connecting a static indexes in the vertex map
      (E(0) connect V(0) and V(1) an so on) regardless witch vertex is in that index*/
    //+++++++++++++++++++++++++++++++++++++++++++++++++
    public class DataStructure{
        HashMap<Integer, Vertex> V = new HashMap<Integer, Vertex>();
        HashMap<Integer, Edge> E = new HashMap<Integer, Edge>();
        /*connect the edges to the vertexes
        done by setting the line start and end of edge canvas draw to vertexes x,y positions*/
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        protected void connect(){
            for(int i=0 ; i<E.size() ; ++i) {
                E.get(i).setStart(V.get(i).getx()  ,V.get(i).gety()  );
                E.get(i).setStop (V.get(i+1).getx(),V.get(i+1).gety());
            }
        }
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        /*swap between vertex position in the HashMap by checking if vertex is touched (vertex have a flag for touch events)
          and this vertex X position is compered with vertex on the left and on the right of it
          if needed two of them are swap*/
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        protected  void ChangeX(){
            Vertex v;

            for(int i=0 ; i<V.size() ; ++i){
                if(V.get(i).isTouch()) {

                    if (i != V.size()-1 && V.get(i).getX() > V.get(i + 1).getX()) {

                        v = V.get(i);
                        V.put(i  , V.get(i+1));
                        V.put(i+1, v);
                    }

                    if(i!=0 && V.get(i).getX() < V.get(i-1).getX()){
                        v = V.get(i);
                        V.put(i  ,V.get(i-1));
                        V.put(i-1,v);
                    }

                }
            }
        }
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        /*adding to the Pit view group and to the DataStructure a new vertex and edge.
          edge is added only if there are two vertex already */
        //+++++++++++++++++++++++++++++
        public void addToPit(){
            Vertex v = new Vertex(Con);
            Edge e   = new Edge(Con);

            V.put(Graph.V.size(),v);

            addView(v);

            if(V.size() >= 2) {
                E.put(E.size(), e);

                addView(e);
            }

        }
        //+++++++++++++++++++++++++++++
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++

}
