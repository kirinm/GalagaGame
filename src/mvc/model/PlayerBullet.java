package _08final.src.mvc.model;

/**
 * Created by kirinmasood on 11/24/2016.
 */
import _08final.src.images.SpriteTexLoader;
import _08final.src.mvc.model.Bullet;

import java.awt.*;

public class PlayerBullet extends Bullet{

    private Point points;

    /**
     * Assigns properties to player bullet
     * @param initPos- position of bullet
     */
    public PlayerBullet(Point initPos, SpriteTexLoader.SpriteTex spriteTex) {

        super(initPos, spriteTex);
        points = initPos;
    }

    /**
     * Assigns positions to bullet movement
     */
    public void movement(double X, double Y)
    {
        this.points.x = (int)X+8;
        this.points.y = (int)Y;
    }

    /**
     * Method to count after fire
     * @return boolean if shot successful
     */
    public boolean Fire()
    {
        this.points.y -= 10;

        if(this.points.y < -20)
            return true;

        else return false;
    }
}
