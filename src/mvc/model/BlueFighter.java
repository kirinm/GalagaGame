package _08final.src.mvc.model;

import _08final.src.images.SpriteTexLoader;
import _08final.src.mvc.controller.Game;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kirinmasood on 11/25/2016.
 */
public class BlueFighter extends Sprite {

    private final static Dimension SHIP_DIM = new Dimension(25,39);
    int Xpos = Game.heightPanel;
    static int lifepoints= 20;
    private static Point p0;
    private static Point p1;
    private static Point p2;
    private static Point p3;
    int smallCount =0;
    public static ArrayList<Integer> count = new ArrayList<Integer>();
    public ArrayList<Point> vertices = new ArrayList<Point>();
    public ArrayList<Point> vertices2 = new ArrayList<Point>();

    /**
     * Assigns the bezier points for the curve
     */
    public void BezierPoints ()
    {
        this.p0 = new Point(Game.widthPanel-60,10);
        this.p1 = new Point(Game.widthPanel+150,220);
        this.p2 = new Point(Game.widthPanel-300,500);
        this.p3 = new Point(Game.widthPanel-120,100);
    }

    /**
     * Set bezier points for the curve
     * @param a,b,c,d - value of the position
     */
    public void setBezierPoints(Point a, Point b,Point c, Point d, int i)
    {
        jumpVertice(a,b,c,d, 0.02);
        smallCount =0;

    }

    /**
     * Gets the value of lifepoints
     * @return lifepoints
     */
    public int getLifepoints()
    {
        return this.lifepoints;
    }

    /**
     * Sets the value of lifepoints
     * @param lifepoints
     */
    public void setLifepoints(int lifepoints)
    {
        this.lifepoints = lifepoints;
    }

    private int index;

    /**
     * Assigns the properties to the blue fighter ship
     * @param index - the index number
     */
    public BlueFighter(int index) {
        super(new Point(Game.widthPanel-20, 0), SHIP_DIM, SpriteTexLoader.load(SpriteTexLoader.SpriteTex.BLUE_FIGHTER));
        this.index = index;
        BezierPoints ();
        curvedVertices(this.p0, this.p1, this.p2, this.p3, 0.02);
        for(int i=0; i<10; i++)
            count.add(0);
    }
    public void move(long deltaTime) {

    }
    public void ink()
    {
        this.mPos.x +=10;
    }

    /**
     * Sets the values of the curve
     * @param p0,p1,p2,p3 - the points assigned to the path
     */
    public void curvedVertices(Point p0, Point p1, Point p2, Point p3, double x1)
    {
        int x, y;
        if(vertices.size()>0)
            for(int i=0; i<vertices.size(); i++)
                vertices.remove(i);
        for(double t = 0.0; t < 1.0; t += x1){
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

    /**
     * Continues the curving vertices path
     * @param p0,p1,p2,p3 - the points assigned to the path
     */
    public void jumpVertice(Point p0, Point p1, Point p2, Point p3, double x1)
    {
        int x, y;
        if(vertices2.size()>0)
            vertices2.clear();
        for(double t = 0.0; t < 1.0; t += x1){
            x =(int)((1-t)*(1-t)*(1-t)*p0.x
                    + 3*(1-t)*(1-t)*t*p1.x
                    + 3*(1-t)*t*t*p2.x
                    + t*t*t*p3.x);
            y = (int)((1-t)*(1-t)*(1-t)*p0.y
                    + 3*(1-t)*(1-t)*t*p1.y
                    + 3*(1-t)*t*t*p2.y
                    + t*t*t*p3.y);

            vertices2.add(new Point(x,y));
        }
    }

    /**
     * Sets movement from blue fighters
     * @param i - int of fighter
     */
    public Boolean newMovePos(int i, Point d)
    {
        boolean comp = false;

        if(smallCount<vertices2.size())
        {
            this.mPos.y = vertices2.get(smallCount).y;
            this.mPos.x = vertices2.get(smallCount).x;
            smallCount+=1;
        }
        else
        {
            mPos.y = d.y;
            mPos.x = d.x;
            comp = true;
        }
        return comp;
    }

    /**
     * Sets movement from blue fighters moving backwards
     * @param d,i,a - int
     */
    public boolean movePos(boolean reverse,int d, int i, int a) {

        boolean go = false;
        if(count.get(i)<vertices.size())
        {

            this.mPos.y = vertices.get(count.get(i)).y;
            this.mPos.x = vertices.get(count.get(i)).x;
            count.set(i, count.get(i)+1);
        }
        else
        {
            if(i==(a-1)) {
                go= true;
            }

            if (reverse == true)
                    this.mPos.x -= d;

                else
                    this.mPos.x += d;
        }

        return go;
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
