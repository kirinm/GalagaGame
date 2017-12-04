package _08final.src.mvc.model;

import _08final.src.images.SpriteTexLoader;
import _08final.src.mvc.controller.Game;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by kirinmasood on 11/24/2016.
 */
public class DisplayValues  extends Sprite {


    private final static Dimension Player_Bullet = new Dimension(10,15);
    private int[] j= new int[10];

    /**
     * Arranges values on screen
     * @param initPos - position
     */
    public DisplayValues(Point initPos,SpriteTexLoader.SpriteTex spriteTex ){
        super(initPos, Player_Bullet, SpriteTexLoader.load(spriteTex));

    }

    /**
     * Sets background image in game
     */
    public DisplayValues(int val) {
        super(new Point(Game.widthPanel/2-200, Game.heightPanel/2 -100), new Dimension(400,200), SpriteTexLoader.load(SpriteTexLoader.SpriteTex.BACKGROUND));

    }

    /**
     * Allows high score image to appear on screen
     */
    public DisplayValues() {
        super(new Point(Game.widthPanel/2-50, 5), new Dimension(110,25), SpriteTexLoader.load(SpriteTexLoader.SpriteTex.HIGH_SCORE));

    }

    /**
     * Sets size of the panel
     * @param x,y,dimX,dimY - int of position
     */
    public DisplayValues(SpriteTexLoader.SpriteTex spriteTex, int x, int y, int dimX, int dimY) {
        super(new Point((Game.widthPanel/2-200)-x, (Game.heightPanel/2 -100)-y ), new Dimension(400-dimX,150-dimY), SpriteTexLoader.load(spriteTex));

    }

    public DisplayValues(SpriteTexLoader.SpriteTex spriteTex, int x) {
        super(new Point(x, 0), new Dimension(20,30), SpriteTexLoader.load(spriteTex));

    }

    /**
     * move on display value screen
     */
    public void move()
    {
        this.mPos.y+=3;

    }

    /**
     * Select option menu
     */
    public void SelectMenu()
    {
        if(mPos.y==280)
        { //System.out.println(mPos);
        mPos.y +=40;}

        else if(mPos.y == 320)
        {
            mPos.y -=40;
        }
    }

    /**
     * Inserts score into file/game
     */
    public void insertScores(int z)throws Exception
    {
        Arrays.sort(j);


        int count=0;
        int k[]= new int[10];
        for(int i=j.length-1; i>=0; i--)
        {
            k[count]=j[i];

           count++;
        }


        for(int i=0; i<10; i++)
        {
            if(z>= k[i])
            {
                k[i]=z;
                for(int x=i+1; x<10; x++)
                {
                    if(x+1!=10)
                    k[x]= k[x+1];
                }

                break;
            }

        }

        File outFile = SpriteTexLoader.loadFile(SpriteTexLoader.SpriteTex.INPUT_FILE);

        FileWriter fWriter = new FileWriter (outFile);
        PrintWriter pWriter = new PrintWriter (fWriter);
       for(int x=0; x<10; x++)
           pWriter.println (k[x]);

        pWriter.close();
    }


    /**
     * Sets highscore in game
     */
    public int[] HighScore() throws IOException
    {

        File inFile = SpriteTexLoader.loadFile(SpriteTexLoader.SpriteTex.INPUT_FILE);

        Scanner sc = new Scanner (inFile);
        String line ="";

        int i=0;
        while (sc.hasNextLine())
        {
            line = sc.nextLine();
            try{

                if(i<10)
                j[i]= Integer.valueOf(line);
            }catch(Exception e){

                j[i]=0;
            }
            i++;
        }
        sc.close();
        return j;
    }

    /**
     * Getter method for points
     * * @return position
     */
    public Point getPoints()
    {
        return this.mPos;
    }

}
