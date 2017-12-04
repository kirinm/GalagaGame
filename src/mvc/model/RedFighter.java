package _08final.src.mvc.model;

import _08final.src.images.SpriteTexLoader;
import _08final.src.mvc.controller.Game;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kirinmasood on 11/25/2016.
 */
public class RedFighter extends Sprite {

    private final static Dimension SHIP_DIM = new Dimension(25,39);
    int Xpos = Game.heightPanel;
    private int index;
    private static Point p0;
    private static Point p1;
    private static Point p2;
    private static Point p3;
    public static ArrayList<Integer> count = new ArrayList<Integer>();
    public ArrayList<Point> vertices = new ArrayList<Point>();


    /**
     * Assigns the bezier points for the curve
     */
    public void BezierPoints ()
    {
        this.p0 = new Point(60,-10);
        this.p1 = new Point(-150,220);
        this.p2 = new Point(300,500);
        this.p3 = new Point(120,150);
    }


    /**
     * Assigns the properties to the red fighter ship
     * @param index - the index number
     */
    public RedFighter(int index) {
        super(new Point(20, 0), SHIP_DIM, SpriteTexLoader.load(SpriteTexLoader.SpriteTex.RED_FIGHTER));
        this.index = index;
        BezierPoints ();
        curvedVertices();
        for(int i=0; i<10; i++)
            count.add(0);
    }
    public void move(long deltaTime) {
    }

    /**
     * Sets the values of the curve
     */
    public void curvedVertices()
    {
       int x, y;

            for(double t = 0.0; t < 1.0; t += 0.02){
                x =(int)((1-t)*(1-t)*(1-t)*p0.x
                        + 3*(1-t)*(1-t)*t*p1.x
                        + 3*(1-t)*t*t*p2.x
                        + t*t*t*p3.x);
                y = (int)((1-t)*(1-t)*(1-t)*p0.y
                        + 3*(1-t)*(1-t)*t*p1.y
                        + 3*(1-t)*t*t*p2.y
                        + t*t*t*p3.y);

                vertices.add(new Point(x,y));
            }
    }

    public void ink()
    {
        this.mPos.x -=10;
    }


    /**
     * Sets movement from blue fighters moving backwards
     * @param d,i,a - int
     */
    public void movePos(boolean reverse, int d, int i) {
        if(count.get(i)<vertices.size())
        {
            this.mPos.y = vertices.get(count.get(i)).y;
            this.mPos.x = vertices.get(count.get(i)).x;
            count.set(i, count.get(i)+1);
        }
        else
        {
            if(reverse==true)
                this.mPos.x -= d;
            else
                this.mPos.x +=d;
        }
    }

    public int getIndex()
    {
        return index;
    }

    /**
     * Sets point position
     * @return position point
     */
    public Point getPoint()
    {
        return new Point(mPos);
    }

    public Point getShipLoc (){return this.mPos;}

}
