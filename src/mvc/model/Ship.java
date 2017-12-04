package _08final.src.mvc.model;

import _08final.src.images.SpriteTexLoader;
import java.awt.*;

import _08final.src.mvc.controller.Game;


/**
 * Created by lamont on 11/20/16.
 */
public class Ship extends Sprite {

    private final static Dimension SHIP_DIM = new Dimension(25,39);

    int Xpos = Game.widthPanel;
    boolean shield = false;


    public void setShield(boolean shield)
    {
        this.shield= shield;
    }
    public boolean getShield()
    {
        return this.shield;
    }
    public Dimension getShipDim(){
        return SHIP_DIM;
    }

    public Ship(Point initPos) {
        super(initPos, SHIP_DIM, SpriteTexLoader.load(SpriteTexLoader.SpriteTex.SHIP));

    }

    public Ship(Point initPos, SpriteTexLoader.SpriteTex spriteTex) {
        super(initPos, SHIP_DIM, SpriteTexLoader.load(spriteTex));

    }

    public Ship(Point initPos, int x, int y) {
        super(initPos, new Dimension(x,y), SpriteTexLoader.load(SpriteTexLoader.SpriteTex.SHIP));

    }
    public void move(long deltaTime) {

    }
    public void moveLeft() {

        if(this.mPos.getX()> 30)
        this.mPos.translate(-10,0);



    }
    public void moveRight() {
        if(this.mPos.getX()< Xpos-30)
        this.mPos.translate(10,0);


    }


    public Point getShipLoc (){return this.mPos;}



}
