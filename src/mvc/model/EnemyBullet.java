package _08final.src.mvc.model;

/**
 * Created by kirinmasood on 11/24/2016.
 */
import _08final.src.images.SpriteTexLoader;
import _08final.src.mvc.model.Bullet;

import java.awt.*;

public class EnemyBullet extends Bullet{

    private Point points;
    private int index;

    /**
     * Assigns properties to enemy bullet
     * @param initPos- position of bullet
     */
    public EnemyBullet(Point initPos, SpriteTexLoader.SpriteTex spriteTex, int index) {
        super(initPos, spriteTex);
        points = initPos;
        this.index = index;
    }

    public void movement(double X, double Y)
    {
        this.points.x = (int)X+8;
        this.points.y = (int)Y;
    }

    public void movement1(double X, double Y)
    {
        this.points.x = (int)X+8;
        this.points.y = (int)Y+8;
    }
    public void movement2(double X, double Y)
    {
        this.points.x = (int)X+8;
        this.points.y = (int)Y-8;
    }

    public int getIndex()
    {
        return index;
    }

    /**
     * Method to count after attack
     * @return boolean if attack successful
     */
    public boolean Attack()
    {
        this.points.y += 10;

        if(this.points.y > XposHeight-10)
            return true;

        else return false;
    }
}
