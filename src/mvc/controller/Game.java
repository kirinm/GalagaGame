package _08final.src.mvc.controller;

import _08final.src.images.SpriteTexLoader;
import _08final.src.mvc.model.*;
import _08final.src.mvc.view.GameFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by lamont on 11/20/16.
 */
public class Game implements Runnable, KeyListener {

    /** Represents the JFrame for the game */
    private GameFrame mGameFrame;

    /** Represents the Animation delay between frames */
    public final static int DRAW_DELAY = 45;

    /** The thread that handles the render loop for the game */
    private Thread mRenderThread;

    /** Represents the ship in the game */
    private Ship mShip;
    private static int change=20;
    private ArrayList<PlayerBullet> playerBullet;
    private static ArrayList<RedFighter> fighters;
    private static ArrayList<BlueFighter> blueFighters;
    private static ArrayList<EnemyBullet> enemyAttack;
    private boolean fire = false;
    private int count= 0;
    private int countblue2 = 0;
    private static boolean reverse;
    private static boolean anti_reverse = true;
    private static boolean blue_reverse;
    private static boolean blue_anti_reverse = true;
    public static int enemy=10;
    public static int attackCount =0;
    public static int playerLifepoints = 3;
    public static boolean shipVisible = false;
    public int visibleFlag =0;
    public int points=0;
    public int highscore =0;
    public static int countRan=50;

    public static int special_Shield_time= 0;


    public final static int widthPanel= 1100;
    public final static int heightPanel= 500;
    public static ArrayList<Integer> nonRan ;
    public static ArrayList<Ship> scoreDisplay;
    public static ArrayList<DisplayValues> pointsDisplay;
    public static ArrayList<DisplayValues> highScoresArray;
    public static ArrayList<DisplayValues> gameOver;
    public static DisplayValues won;
    public static DisplayValues shield;
    public static DisplayValues heart;
    public DisplayValues values;
    public boolean newGame = false;


    /** List of Sprites that need to be rendered  */
    private static ArrayList<Sprite> _sprites = new ArrayList<Sprite>();

    public Game() throws Exception {

        this.mGameFrame = new GameFrame(this);

        this.mShip = new Ship(new Point(mGameFrame.getWidth()/2,mGameFrame.getHeight()-100));
        this.scoreDisplay = new ArrayList<Ship>();
        this.playerBullet = new ArrayList<PlayerBullet>();
        this.fighters =  new ArrayList<RedFighter>();
        this.blueFighters = new ArrayList<BlueFighter>();
        this.enemyAttack = new ArrayList<EnemyBullet>();
        this.nonRan = new ArrayList<Integer>();
        this.pointsDisplay = new ArrayList<DisplayValues>();
        this.gameOver = new ArrayList<DisplayValues>();
        this.values= new DisplayValues();
        this.highscore=values.HighScore()[0];
        this.highScoresArray = new ArrayList<DisplayValues>();
        this.gameOver.add(new DisplayValues(SpriteTexLoader.SpriteTex.GAME_OVER, 0,0, 0,30));
        this.gameOver.add(new DisplayValues(SpriteTexLoader.SpriteTex.Game_LOGO, 10,50, 10,10));
        this.gameOver.add(new DisplayValues(SpriteTexLoader.SpriteTex.PLAY_GAME, -20,-130, 65,130));
        this.gameOver.add(new DisplayValues(SpriteTexLoader.SpriteTex.HIGH_SCORE, -45,-170, 115,120));
        this.gameOver.add(new DisplayValues(SpriteTexLoader.SpriteTex.BLUE_FIGHTER, 45,-130, 335,130));
        this.gameOver.add(new DisplayValues(SpriteTexLoader.SpriteTex.BLUE_FIGHTER, -355,-130, 335,130));
        this.gameOver.add(new DisplayValues(SpriteTexLoader.SpriteTex.HS_REPORT, 40,140, -100,-330));
        this.won=(new DisplayValues(SpriteTexLoader.SpriteTex.YOU_WIN, 0,0, 0,30));
        updateHighScore(highscore);

        for (int i = 0; i < enemy; i++)
            nonRan.add(i);

        Game._sprites.add(new DisplayValues());

        for(int i=0; i<4; i++)
        {
            pointsDisplay.add(getScore(i+"",0,"score"));
            Game._sprites.add(pointsDisplay.get(i));
        }

        int count = 0;
        for(int i = 10; i<=70; i=i+30) {
            scoreDisplay.add(new Ship(new Point(i, Game.heightPanel-50), 20,30));
            Game._sprites.add(scoreDisplay.get(count));
            count++;
        }

        Game._sprites.add(mShip);
        for(int i = 1; i<6; i++)
        {
            Game._sprites.add(gameOver.get(i));}
    }

    /**
     * Starts the thread that will handle the render loop for the game
     */
    private void startRenderLoopThread() {
        //Check to make sure the render loop thread has not begun
        if (this.mRenderThread == null) {
            //All threads that are created in java need to be passed a Runnable object.
            //In this case we are making the "Runnable Object" the actual game instance.
            this.mRenderThread = new Thread(this);
            //Start the thread
            this.mRenderThread.start();
        }
    }

    /**
     * This represents the method that will be called for a Runnable object when a thread starts.
     * In this case, this run method represents the render loop.
     */
    public static int sync=0;
    public static int syncSpeed=4;
    public static boolean swirlAttack= false;
    public static int replacement=0;
    public static int replacement1=0;
    public static int swirlAttackRandom =1;
    public static int swirlAttackRandom_rev =1;
    public static int shield_count =0;
    public static int heart_count=0;

    public void run() {

        //Make this thread a low priority such that the main thread of the Event Dispatch is always is
        //running first.



        this.mRenderThread.setPriority(Thread.MIN_PRIORITY);

        //Get the current time of rendering this frame
        long elapsedTime = System.currentTimeMillis();

        long currentTime = 0;
        long lastTime = 0;
        long deltaTime = 0;


        // this thread animates the scene
        while (Thread.currentThread() == this.mRenderThread) {

            // Game._sprites.add(gameOver.get(0));


            if(blueAttacktime==200)
            { syncSpeed=syncSpeed+1;
                attackCount=0;
                countRan=countRan-20;

                if (countRan<=0)
                    countRan=10;
                }



            if (playerLifepoints <= 0) {
                Game._sprites.remove(mShip);
                for (int i = 0; i < fighters.size(); i++)
                    Game._sprites.remove(fighters.get(i));

                for (int i = 0; i < blueFighters.size(); i++)
                    Game._sprites.remove(blueFighters.get(i));

                Game._sprites.remove(shield);
                Game._sprites.remove(heart);
                Game._sprites.add(gameOver.get(0));
                try {
                    values.insertScores(points);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            currentTime = System.currentTimeMillis();

            if (lastTime == 0) {
                lastTime = currentTime;
                deltaTime = 0;
            } else {
                deltaTime = currentTime - lastTime;
                lastTime = currentTime;
            }

            /************* Update game HERE
             * - Move the game models
             * - Check for collisions between the bullet, or fighters and the ship
             * - Check whether we should move to a new level potentially.
             */


            if (newGame) {

            special_Shield_time++;
            heart_count++;
                /** Organizes information about shields and extra lives the player can periodically receive */
                if(special_Shield_time>=300 && (playerLifepoints==2 || playerLifepoints ==1))
            {

                Random ran = new Random();
                int r= ran.nextInt(widthPanel-100);
                this.shield = new DisplayValues(SpriteTexLoader.SpriteTex.SHIELD,r);
                Game._sprites.add(shield);
                special_Shield_time=-300;
            }

                if(heart_count>=350 && (playerLifepoints==2 || playerLifepoints ==1))
                {

                    Random ran = new Random();
                    int r= ran.nextInt(widthPanel-100);
                    this.heart = new DisplayValues(SpriteTexLoader.SpriteTex.HEART,r);
                    Game._sprites.add(heart);
                    heart_count=-300;
                }

            if(Game._sprites.contains(shield) && !shield.equals(null))
                shield.move();
                if(Game._sprites.contains(heart) && !heart.equals(null))
                    heart.move();

            EnemyAttack();
                /** Organizes red fighters */

                for (int i = 0; i < fighters.size(); i++) {
                if (fighters.get(fighters.size() - 1).getPoint().x <= 10) {
                    anti_reverse = true;
                    reverse = false;
                }
                if (anti_reverse == true) {
                    if (fighters.get(0).getPoint().x > Game.widthPanel - 30) {
                        reverse = true;
                        anti_reverse = false;
                        fighters.get(0).ink();

                    }
                }
                fighters.get(i).movePos(reverse, syncSpeed,i);

            }

            BlueEnemyAttack();
                /** organizes the blue fighters */
                for (int i = 0; i < blueFighters.size(); i++) {

                if (blueFighters.get(0).getPoint().x <= 10) {
                    blue_anti_reverse = true;
                    blue_reverse = false;
                    blueFighters.get(0).ink();
                }
                if (blue_anti_reverse == true) {
                    if (blueFighters.get(blueFighters.size() - 1).getPoint().x >= Game.widthPanel - 30) {
                        blue_reverse = true;
                        blue_anti_reverse = false;
                    }
                }
                if (sync == 0) {
                    try {
                        if (blueFighters.size() - 1 >= 0)
                            if (fighters.get(0).getPoint().x == blueFighters.get(blueFighters.size() - 1).getPoint().x) {
                                blue_anti_reverse = true;
                                blue_reverse = false;
                            }
                      //  syncSpeed = 4;
                    } catch (IndexOutOfBoundsException e) {

                    }

                }
               swirlAttack= blueFighters.get(i).movePos(blue_reverse, syncSpeed,i, blueFighters.size());
                // sync++;
            }

                /** Represents red/blue fighter bullets and formation and functionality */
                ArrayList<EnemyBullet> eb = EnemyFire(fighters, blueFighters);
              //
                if( swirlAttack== true)
                {
                    if(swirlAttackRandom_rev==1)
                    {
                        Random ran = new Random();

                        swirlAttackRandom = ran.nextInt(10);

                        if(swirlAttackRandom==0)
                            swirlAttackRandom=1;
                        if(swirlAttackRandom>=blueFighters.size())
                            swirlAttackRandom=blueFighters.size()-1;

                        swirlAttackRandom_rev+=1;
                    }
                    if(blue_reverse==true && replacement ==0) {
                        int k=swirlAttackRandom;
                        if(k>=blueFighters.size())
                            k=blueFighters.size()-1;

                        if(blueFighters.size()>1)
                        ThriceAttack(new Point(blueFighters.get(k).getPoint().x,blueFighters.get(k).getPoint().y ));
                        try{
                            if(blueE_moveAttack(blueFighters.get(k), blueFighters.get(k-1), k, true))
                            {
                                blueAttacktime=0;
                                replacement++;
                                swirlAttackRandom_rev=1;
                                x=0;
                            }
                        }
                        catch(Exception e){

                        }

                    }

                    if(blue_reverse==false && replacement1 ==0) {
                        int k=swirlAttackRandom;
                        if(swirlAttackRandom>=blueFighters.size())
                            k=blueFighters.size()-1;


                        if(blueFighters.size()>1)
                        ThriceAttack(new Point(blueFighters.get(k).getPoint().x,blueFighters.get(k).getPoint().y ));
                       try{
                           if(blueE_moveAttack(blueFighters.get(k), blueFighters.get(k-1), k, false))
                           {
                               blueAttacktime=0;
                               replacement1++;
                               x=0;
                           }
                       }
                       catch(Exception e){}


                    }



                }
                if(blueAttacktime==200) {
                    replacement = 0;
                    replacement1 = 0;
                }

                blueAttacktime++;

                /** Implements shield functionality */
                if(Game._sprites.contains(shield) && !shield.equals(null)){
                    if (CollisionDetection1(shield,mShip))
                            {
                                Game._sprites.remove(shield);
                               // mShip.setShield(true);
                                Game._sprites.remove(mShip);
                                mShip= new Ship(mShip.getShipLoc(), SpriteTexLoader.SpriteTex.SAFE_SHIP);
                                mShip.setShield(true);
                                Game._sprites.add(mShip);
                                shield=null;

                            }
                }

                /** Implements extra life functionality which allows the user an extra life once it receives a heart image */

                if(Game._sprites.contains(heart) && !heart.equals(null) && !mShip.equals(null)){
                    if (CollisionDetection1(heart,mShip))
                    {
                        if(playerLifepoints==2)
                        {
                                scoreDisplay.add(new Ship(new Point(70, Game.heightPanel-50), 20,30));
                                Game._sprites.add(scoreDisplay.get(2));

                            } else if(playerLifepoints==1)
                             {
                            scoreDisplay.add(new Ship(new Point(40, Game.heightPanel-50), 20,30));
                            Game._sprites.add(scoreDisplay.get(1));

                             }

                       playerLifepoints= playerLifepoints+1;

                       Game._sprites.remove(heart);
                       heart=null;
                    }
                }
            for (int i = 0; i < eb.size(); i++) {

                if ((eb.get(i).getleft_bottom().x >= mShip.getleft_top().x && eb.get(i).getleft_bottom().x <=
                        mShip.getright_top().x && eb.get(i).getleft_bottom().y <= mShip.getleft_top().y &&
                        eb.get(i).getleft_bottom().y >= mShip.getleft_bottom().y) ||
                        (eb.get(i).getright_bottom().x <= mShip.getright_top().x && eb.get(i).getleft_bottom().x >=
                                mShip.getleft_top().x) && eb.get(i).getright_bottom().y <= mShip.getright_top().y &&
                                eb.get(i).getright_bottom().y >= mShip.getright_bottom().y) {

                    if (!shipVisible) {

                        if(!mShip.getShield()) {
                            Game._sprites.remove(mShip);
                            shipVisible = true;
                            visibleFlag = 1;
                        }


                    }


                }



                boolean check = eb.get(i).Attack();
                if (check)
                    Game._sprites.remove(eb.remove(i));
            }

                if(mShip.getShield())
                    shield_count++;


                if(shield_count==100)
                {
                    Game._sprites.remove(mShip);
                    mShip = new Ship(new Point(mShip.getShipLoc().x, mShip.getShipLoc().y));
                    Game._sprites.add(mShip);
                    shield_count=0;
                }

            if (shipVisible == true) {
                shipVisible = false;
                Game._sprites.remove(mShip);
                mShip = new Ship(new Point(mShip.getShipLoc().x+20, mShip.getShipLoc().y));
                mShip.setShield(true);
                Game._sprites.add(mShip);
                visibleFlag = 0;
                scoreCount();
            }

                /** Implements functionality to fire ships and increment points */
            attackCount++;
            if (fire == true)
                for (int i = 0; i < playerBullet.size(); i++) {
                    boolean flag = playerBullet.get(i).Fire();

                    for (int j = 0; j < fighters.size(); j++) {
                        if (collisionDetection(playerBullet.get(i), fighters.get(j))) {
                            Game._sprites.remove(fighters.remove(j));
                            Game._sprites.remove(playerBullet.get(i));

                            points += 10;

                            try {
                                values.insertScores(points);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ArrayList<Integer> digits = splitNumbers(points);
                            for (int k = 0; k < digits.size(); k++)
                                Game._sprites.add(getScore(k + "", digits.get(k), "score"));

                            if (points > highscore) {
                                highscore = points;
                                try {

                                    updateHighScore(highscore);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                    if (flag == true) {
                        Game._sprites.remove(playerBullet.get(0));
                        playerBullet.remove(0);
                    }

                }


                /** Implements functionality to attack blue enemy fighters and increment points  */
            if (fire == true)
                for (int i = 0; i < playerBullet.size(); i++) {

                    for (int j = 0; j < blueFighters.size(); j++) {
                        if (collisionDetection(playerBullet.get(i), blueFighters.get(j))) {

                            blueFighters.get(j).setLifepoints(blueFighters.get(j).getLifepoints()-1);

                            if(blueFighters.get(j).getLifepoints()<0)
                            {
                                Game._sprites.remove(blueFighters.remove(j));
                            }

                            Game._sprites.remove(playerBullet.get(i));

                            points += 10;

                            try {
                                values.insertScores(points);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ArrayList<Integer> digits = splitNumbers(points);
                            for (int k = 0; k < digits.size(); k++)
                                Game._sprites.add(getScore(k + "", digits.get(k), "score"));

                            if (points > highscore) {
                                highscore = points;
                                try {

                                    updateHighScore(highscore);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }


                }


        }
            /** Ends game when there are no fighters left */

            if(newGame==true && fighters.size()==0 && blueFighters.size()==0)
            {
                for(int i=0; i<enemyAttack.size(); i++)
                    Game._sprites.remove(enemyAttack.remove(i));

                EndGame();
            }

            this.mGameFrame.draw();

            try {
                /** We want to ensure that the drawing time is at least the DRAW_DELAY we specified. */
                elapsedTime += DRAW_DELAY;
                Thread.sleep(Math.max(0, elapsedTime - currentTime));
            } catch (InterruptedException e) {
                //If an interrupt occurs then you can just skip this current frame.
                continue;
            }


        }
    }

    /**
     * Allows score to be calculated
     * @param num - the number
     */
    public ArrayList<Integer> splitNumbers(int num)
    {
        ArrayList<Integer> digits = new ArrayList<Integer>();
        int h=num;
        if(h==0) {
            digits.add(0);
            return digits;
        }

        else {
            while (h > 0) {
                digits.add(h % 10);
                h /= 10;
            }
            return digits;
        }
    }

    /**
     * Stores scores and reports it with top 10 scores
     */
    public void ScoreReport() throws IOException {
       int[] tenScores= values.HighScore();
        int pos=0;
       for(int i=0; i<tenScores.length; i++)
       {
           DisplayValues dv;
           ArrayList<Integer> digits = splitNumbers(tenScores[i]);
           for(int j=0; j<digits.size(); j++)
           {
               String sprVal = "_"+digits.get(j);
               SpriteTexLoader.SpriteTex spriteTex = SpriteTexLoader.SpriteTex.valueOf(sprVal) ;

               if (j==3)
                   gameOver.add(new DisplayValues(new Point(Game.widthPanel/3+135, 110+pos), spriteTex));
               else if (j==2)
                   gameOver.add(new DisplayValues(new Point(Game.widthPanel/3+155, 110+pos), spriteTex));
               else if (j==1)
                   gameOver.add(new DisplayValues(new Point(Game.widthPanel/3+175, 110+pos), spriteTex));
               else
                   gameOver.add(new DisplayValues(new Point(Game.widthPanel/3+195, 110+pos), spriteTex));


               Game._sprites.add(gameOver.get(gameOver.size()-1));

           }

        pos=pos+30;
       }

    }


    /**
     * Allows score to be displayed
     */
    public DisplayValues getScore(String val, int spr, String str){
        DisplayValues dv;
        String sprVal = "_"+spr;
        SpriteTexLoader.SpriteTex spriteTex = SpriteTexLoader.SpriteTex.valueOf(sprVal) ;

       if(str.equalsIgnoreCase("score")) {
           if (val.equalsIgnoreCase("3"))
               dv = new DisplayValues(new Point(15, 10), spriteTex);
           else if (val.equalsIgnoreCase("2"))
               dv = new DisplayValues(new Point(35, 10), spriteTex);
           else if (val.equalsIgnoreCase("1"))
               dv = new DisplayValues(new Point(55, 10), spriteTex);
           else //(val.equalsIgnoreCase("3"))
               dv = new DisplayValues(new Point(75, 10), spriteTex);
       }
       else
       {
           if (val.equalsIgnoreCase("3"))
               dv = new DisplayValues(new Point(Game.widthPanel/2-30, 30), spriteTex);
           else if (val.equalsIgnoreCase("2"))
               dv = new DisplayValues(new Point(Game.widthPanel/2-10, 30), spriteTex);
           else if (val.equalsIgnoreCase("1"))
               dv = new DisplayValues(new Point(Game.widthPanel/2+15, 30), spriteTex);
           else //(val.equalsIgnoreCase("3"))
               dv = new DisplayValues(new Point(Game.widthPanel/2+35, 30), spriteTex);

       }

        return dv;
    }

    /**
     * Ends game by removing sprites and displaying status
     */
    public void EndGame()
    {
        Game._sprites.remove(mShip);
        for (int i = 0; i < fighters.size(); i++)
            Game._sprites.remove(fighters.remove(i));

        for (int i = 0; i < blueFighters.size(); i++)
            Game._sprites.remove(blueFighters.remove(i));
        Game._sprites.remove(shield);
        Game._sprites.remove(heart);
        Game._sprites.add(won);
        try {
            values.insertScores(points);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Implements enemy attack style
     * @param rd - value of the target position
     */
    public ArrayList<EnemyBullet> ThriceAttack (Point rd)
    {
        enemyAttack.add(new EnemyBullet(new Point(rd.x, rd.y), SpriteTexLoader.SpriteTex.ENEMY_BULLET, 1));
        Game._sprites.add(enemyAttack.get(enemyAttack.size()-1));

        enemyAttack.add(new EnemyBullet(new Point(rd.x, rd.y), SpriteTexLoader.SpriteTex.ENEMY_BULLET, 100));
        Game._sprites.add(enemyAttack.get(enemyAttack.size()-1));

        enemyAttack.add(new EnemyBullet(new Point(rd.x, rd.y), SpriteTexLoader.SpriteTex.ENEMY_BULLET, 101));
        Game._sprites.add(enemyAttack.get(enemyAttack.size()-1));

        return enemyAttack;
    }

    /**
     * Organizes attacks and bullet movement of all fighters including bezier movement
     * @param rd - values of the targets
     */
    public ArrayList<EnemyBullet> EnemyFire(ArrayList<RedFighter> rd, ArrayList<BlueFighter> rd1){

        Random rand = new Random();
        if(attackCount==countRan)
       if(rd.size()>0)
        {

            int ranNum = rand.nextInt(rd.size());
           enemyAttack.add(new EnemyBullet(new Point(rd.get(ranNum).getPoint()), SpriteTexLoader.SpriteTex.ENEMY_BULLET, ranNum));
            Game._sprites.add(enemyAttack.get(enemyAttack.size() - 1));
        attackCount=0;
        }

        return enemyAttack;
    }
    public static int blueAttacktime =0;
    public boolean blueAttack= false;
    public static int x=0;
    public boolean blueE_moveAttack(BlueFighter rd1, BlueFighter  rd2, int i, boolean rev)
    {

        if(x==0 && rev==true)
        {
            Point p0 = new Point(rd1.getPoint().x, 123);
            Point p1 = new Point(rd1.getPoint().x+150, 450);
            Point p2 = new Point(rd1.getPoint().x-300, 450);
            Point p3 = new Point(rd1.getPoint().x-180, 120);
            rd1.setBezierPoints(p0, p1, p2, p3, i);
          //  System.out.println("testing");
            x=1;

          //  x= rd1.getPoint().x;
        }

        else if(x==0 && rev == false)
        {
            Point p0 = new Point(rd1.getPoint().x, 123);
            Point p1 = new Point(rd1.getPoint().x-150, 450);
            Point p2 = new Point(rd1.getPoint().x+300, 450);
            Point p3 = new Point(rd1.getPoint().x+180, 120);
            rd1.setBezierPoints(p0, p1, p2, p3, i);
         //   System.out.println("testing2");
            x=1;
            //  x= rd1.getPoint().x;
        }

       blueAttack= rd1.newMovePos(i, new Point(rd2.getPoint().x+40,rd2.getPoint().y));
       // rd1
        return blueAttack;
    }



    /**
     * Implements collision detection for the red fighters
     * @param pb - player bullet and position
     * @param fighters - red fighter properties
     */
    public boolean collisionDetection(PlayerBullet pb, RedFighter fighters)
    {
        if((pb.getleft_top().x >= fighters.getleft_bottom().x && pb.getleft_top().x
                <= fighters.getright_bottom().x && pb.getleft_top().y <= fighters.getleft_top().y
                && pb.getleft_top().y >= fighters.getleft_bottom().y)  ||
                pb.getright_top().x <= fighters.getright_bottom().x && pb.getright_top().x
                        >= fighters.getleft_bottom().x && pb.getright_top().y <= fighters.getright_bottom().y &&
                        pb.getright_top().y >= fighters.getleft_top().y)
                {
                   return true;
                }

                else return false;
     }

    /**
     * Implements collision detection for the ship
     * @param shields - shield status
     * @param val - ship fighter properties
     */
     public boolean CollisionDetection1(DisplayValues shields, Ship val)
     {
         try{
             if ((shields.getleft_bottom().x >= val.getleft_top().x && shields.getleft_bottom().x <=
                     val.getright_top().x && shields.getleft_bottom().y <= val.getleft_top().y &&
                     shields.getleft_bottom().y >= val.getleft_bottom().y) ||
                     (shields.getright_bottom().x <= val.getright_top().x && shield.getleft_bottom().x >=
                             val.getleft_top().x) && shields.getright_bottom().y <= val.getright_top().y &&
                             shields.getright_bottom().y >= val.getright_bottom().y)
             {
                 return true;
             }
         }
         catch (Exception e){
             return false;
         }


          return false;
     }

    /**
     * Implements collision detection for the blue fighters
     * @param pb - player bullet and position
     * @param fighters - red fighter properties
     */
    public boolean collisionDetection(PlayerBullet pb, BlueFighter fighters)
    {
        if((pb.getleft_top().x >= fighters.getleft_bottom().x && pb.getleft_top().x
                <= fighters.getright_bottom().x && pb.getleft_top().y <= fighters.getleft_top().y
                && pb.getleft_top().y >= fighters.getleft_bottom().y)  ||
                pb.getright_top().x <= fighters.getright_bottom().x && pb.getright_top().x
                        >= fighters.getleft_bottom().x && pb.getright_top().y <= fighters.getright_bottom().y &&
                        pb.getright_top().y >= fighters.getleft_top().y)
        {
            return true;
        }

        else return false;
    }

    /**
     * Displays score after game ends
     */
    public void scoreCount ()
     {
        if(playerLifepoints!=0) {
            playerLifepoints -= 1;
            Game._sprites.remove(scoreDisplay.get(playerLifepoints));
            scoreDisplay.remove(scoreDisplay.size()-1);
        }
     }

    /**
     * Updates the high score and adds it to list
     * @param hs - the value of the high score
     */
     public void updateHighScore(int hs) throws Exception {
         ArrayList<Integer> digits = splitNumbers(hs);

         for(int i=0; i<highScoresArray.size()-1; i++)
         {
             highScoresArray.remove(i);
         }
         for (int j=0; j<digits.size(); j++)
         {
             highScoresArray.add(getScore(j+"",digits.get(j),"high"));
             Game._sprites.add(highScoresArray.get(j));
         }
         if(digits.size()<4)
         {
             for (int j=digits.size(); j<4; j++)
             {
                 highScoresArray.add(getScore(j+"",0,"high"));
                 Game._sprites.add(highScoresArray.get(j));
             }
         }
     }
    /***
     * Generates all the drawable sprites for the game currently
     * @return an arraylist of all the drawable sprites in the game
     */
    private int bulletpoint;
    public static ArrayList<Sprite> getDrawableSprites() {
        return new ArrayList<Sprite>(_sprites);
    }

     static boolean lock1= true;
    public static int count1=0;
    /**
     * counts red fighters and displays
     */
    public static ArrayList<RedFighter> EnemyMarch() {

        if(fighters.size()>=0 && fighters.size()<enemy && count1 <enemy)
        {lock1 = false;
        count1++;}
        else
            lock1=true;

        if(!lock1)
        {
            fighters.add(new RedFighter(fighters.size()));
        Game._sprites.add(fighters.get(fighters.size()-1));

        }
        return fighters;
    }

    public static int countblue=0;

    /**
     * counts blue fighters and displays
     */
    public static ArrayList<BlueFighter> BlueEnemyMarch() {

        if(blueFighters.size()>=0 && blueFighters.size()<enemy && countblue <enemy)
        {lock1 = false;
            countblue++;}
        else
            lock1=true;

        if(!lock1)
        {
            blueFighters.add(new BlueFighter(blueFighters.size()));
            Game._sprites.add(blueFighters.get(blueFighters.size()-1));

        }
        return blueFighters;
    }

    /**
     * Organizes attack of red fighters
     */
    public void EnemyAttack()
    {
        ArrayList<RedFighter> enemyMarchWave;

        if(count==0)
        {
            enemyMarchWave = EnemyMarch();
        }
        else enemyMarchWave= new ArrayList<>();
        count++;
        if(count==10)
            count=0;


    }

    /**
     * Organizes attack of blue fighters
     */
    public void BlueEnemyAttack()
    {
        ArrayList<BlueFighter> enemyMarchWave1;

        if(countblue2==0)
        {
            enemyMarchWave1 = BlueEnemyMarch();
        }
        else enemyMarchWave1= new ArrayList<>();
        countblue2++;
        if(countblue2==10)
            countblue2=0;


    }

    public boolean startGame=false;
    /**
     * Implements ship movement and game setup
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int nKey = e.getKeyCode();


        switch (nKey) {
            case KeyEvent.VK_LEFT:
                if(newGame)
                this.mShip.moveLeft();
                change=-40;
                break;
            case KeyEvent.VK_RIGHT:
                if(newGame)
                this.mShip.moveRight();
                change=40;
                break;
            case KeyEvent.VK_DOWN:
                if(!newGame)
                {  gameOver.get(4).SelectMenu();
                    gameOver.get(5).SelectMenu();}
                break;
            case KeyEvent.VK_UP:
                if(!newGame)
                {  gameOver.get(4).SelectMenu();
                    gameOver.get(5).SelectMenu();}
                break;
            case KeyEvent.VK_ENTER:
                if(!newGame)
                {
                    if(gameOver.get(4).getPoints().y == 280){

                        //if(Game._sprites.contains(gameOver.get(0)))
                        try{
                            Game._sprites.remove(Game._sprites.size()-4);
                            playerLifepoints=3;}
                            catch(Exception exp){
                          //  System.out.println("error");
                            }
                            startGame= false;
                    newGame= true;
                    for (int i=0; i<6; i++)
                        Game._sprites.remove(gameOver.get(i));}

                    else{
                        if(!startGame) {
                            try{
                                Game._sprites.remove(gameOver.get(0));
                                playerLifepoints=3;}
                            catch(Exception exp){
                              //  System.out.println("error");
                            }
                            Game._sprites.add(mShip);
                            //for (int i = 6; i < 7; i++)
                                Game._sprites.add(gameOver.get(6));

                            try {
                                ScoreReport();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            startGame= true;

                        }
                        else if(startGame)
                        {
                            try{
                                Game._sprites.remove(gameOver.get(0));}
                            catch(Exception exp){
                              //  System.out.println("error");
                            }
                          //  System.out.print(gameOver.size());
                            for (int i = 1; i < gameOver.size(); i++)
                                Game._sprites.remove(gameOver.get(i));
                            newGame= true;

                            fire = false;
                            count= 0;
                            countblue2 = 0;
                            reverse = false;
                            anti_reverse = true;
                            blue_reverse = false;
                            blue_anti_reverse = true;
                            enemy=10;
                            attackCount =0;
                            playerLifepoints = 3;
                            shipVisible = false;
                            visibleFlag =0;
                            points=0;


                        }
                    }
                }
                break;
            case KeyEvent.VK_SPACE:
            if(newGame)
             {
                playerBullet.add(new PlayerBullet(new Point(0, 0), SpriteTexLoader.SpriteTex.PLAYER_BULLET));
                bulletpoint = playerBullet.size() - 1;

                Game._sprites.add(playerBullet.get(bulletpoint));
                fire = true;
                playerBullet.get(bulletpoint).movement(mShip.getShipLoc().getX(), mShip.getShipLoc().getY());
             }
             break;
            default:
                System.out.println("Pressing the key: " + KeyEvent.getKeyText(nKey));
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        int nKey = e.getKeyCode();
    }
    public void keyTyped(KeyEvent e) {}



    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() { // uses the Event dispatch thread from Java 5 (refactored)
            public void run() {
                try {
                    Game game = new Game();
                    game.startRenderLoopThread();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
