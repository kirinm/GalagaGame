package _08final.src.mvc.model;

import _08final.src.images.SpriteTexLoader;
import _08final.src.mvc.controller.Game;

import java.awt.*;

/**
 * Created by kirinmasood on 11/24/2016.
 */
public class Bullet  extends Sprite {

    private final static Dimension Player_Bullet = new Dimension(10,15);

    int Xpos = Game.widthPanel;
    int XposHeight= Game.heightPanel;

    /**
     * Assigns properties/positions to player bullet
     * @param initPos - int position
     */
    public Bullet(Point initPos, SpriteTexLoader.SpriteTex spriteTex) {
        super(initPos, Player_Bullet, SpriteTexLoader.load(spriteTex));

    }
    public void move(long deltaTime) {

    }


}
