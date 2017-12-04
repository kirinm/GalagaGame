package _08final.src.mvc.model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by lamont on 11/20/16.
 */
public abstract class Sprite {

    /** The dimensions of the sprite */
    private Dimension mDim;


    Point left_bottom, left_top, right_bottom, right_top;

    /** The position of the sprite */
    protected Point mPos;

    /** The texture for the sprite */
    private BufferedImage mTex;

    public Sprite(Point initPos, Dimension dim, BufferedImage texture) {
        this.mPos = initPos;
        this.mDim = dim;
        this.mTex = texture;
    }

    public Sprite ()
    {

    }

    /** The position for drawing the Sprites */
    public void draw (Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        g2d.drawImage(this.mTex, (int) this.mPos.getX(), (int) this.mPos.getY(), (int) this.mDim.getWidth(),
                                 (int) this.mDim.getHeight(), null);
    }

    /** Method to retreive position of bottom left */
    public Point getleft_bottom(){
        int lb_1 = mPos.x - (mDim.width/2);
        int lb_2 = mPos.y - (mDim.height/2);
        left_bottom = new Point(lb_1,lb_2);
        return left_bottom;
    }

    /** Method to retreive position of top right */
    public Point getleft_top(){
        int lt_1 = mPos.x - (mDim.width/2);
        int lt_2 = mPos.y + (mDim.height/2);
        left_top = new Point(lt_1,lt_2);
        return left_top;
    }

    /** Method to retreive position of bottom right */
    public Point getright_bottom(){

        int rb_1 = mPos.x + (mDim.width/2);
        int rb_2 = mPos.y - (mDim.height/2);
        right_bottom = new Point(rb_1,rb_2);
        return right_bottom;
    }

    /** Method to retreive position of right top */
    public Point getright_top(){
        int rt_1 = mPos.x + (mDim.width/2);
        int rt_2 = mPos.y + (mDim.height/2);
        right_top = new Point(rt_1,rt_2);
        return right_top;

    }

    public Point[] getBorders ()
    {
        Point borders[] = new Point[4];
        int lb_1 = mPos.x - (mDim.width/2);
        int lb_2 = mPos.y - (mDim.height/2);
        left_bottom = new Point(lb_1,lb_2);
        borders[0]= left_bottom;

        int rb_1 = mPos.x + (mDim.width/2);
        int rb_2 = mPos.y - (mDim.height/2);
        right_bottom = new Point(rb_1,rb_2);
        borders[1]= right_bottom;

        int rt_1 = mPos.x + (mDim.width/2);
        int rt_2 = mPos.y + (mDim.height/2);
        right_top = new Point(rt_1,rt_2);
        borders[2]= right_top;

        int lt_1 = mPos.x - (mDim.width/2);
        int lt_2 = mPos.y + (mDim.height/2);
        left_top = new Point(lt_1,lt_2);
        borders[3]= left_top;


        return borders;
    }


}
