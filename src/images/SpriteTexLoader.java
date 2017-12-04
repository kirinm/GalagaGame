package _08final.src.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class easily loads the textures for the sprites in the game
 *
 * @version  1.0
 * @author Lamont Samuels
 * @since  11-13-16
 */

public class SpriteTexLoader {

    private static SpriteTexLoader sInstance = new SpriteTexLoader();

    public enum SpriteTex {
        SHIP,SAFE_SHIP,
        BLUE_FIGHTER,
        RED_FIGHTER,
        PLAYER_BULLET,
        ENEMY_BULLET,
        HIGH_SCORE,
        GAME_OVER,
        BACKGROUND,
        Game_LOGO,
        PLAY_GAME,
        HS_REPORT,
        INPUT_FILE,
        SHIELD,HEART,
        YOU_WIN,
        _0("0"),_1("1"),_2("2"),_3("3"),_4("4"),_5("5"),_6("6"),
        _7("7"),_8("8"),_9("9");

        private String val;
        SpriteTex(String val) {
            this.val = val;
        }
        SpriteTex() {

        }
        public String val() {
            return val;
        }

        }

    public SpriteTexLoader() {}
    /**
     * Retrieves the file path for the a Sprite texture in the images file
     * @param sprite - the sprite file path to retrieve
     */
    public static String getSpriteFile(SpriteTex sprite) {

        String file = "";
        switch (sprite) {
            case SHIP:
                file = "ship.png";
                break;
            case BLUE_FIGHTER:
                file = "blue_fighter.png";
                break;
            case RED_FIGHTER:
                file = "red_fighter.png";
                break;
            case PLAYER_BULLET:
                file = "playerBullet.png";
                break;
            case ENEMY_BULLET:
                file = "enemybullet.png";
                break;
            case HIGH_SCORE:
                file = "High_Score.png";
                break;
            case GAME_OVER:
                file = "Game_Over.png";
                break;
            case BACKGROUND:
                file = "background.gif";
                break;
            case Game_LOGO:
                file = "Galaga_Name.png";
                break;
            case PLAY_GAME:
                file = "Play_Game.png";
                break;
            case HS_REPORT:
                file = "HS_Report.png";
                break;
            case INPUT_FILE:
                file = "input.txt";
                break;
            case SHIELD:
                file = "shield.png";
                break;
            case SAFE_SHIP:
                file = "safe_Ship.png";
                break;
            case HEART:
                file = "heart.png";
                break;
            case YOU_WIN:
                file = "win.png";
                break;
            case _0:
                file = "0.png";
                break;
            case _1:
                file = "1.png";
                break;
            case _2:
                file = "2.png";
                break;
            case _3:
                file = "3.png";
                break;
            case _4:
                file = "4.png";
                break;
            case _5:
                file = "5.png";
                break;
            case _6:
                file = "6.png";
                break;
            case _7:
                file = "7.png";
                break;
            case _8:
                file = "8.png";
                break;
            case _9:
                file = "9.png";
                break;
        }
        return file;
    }

    /**
     * Returns a buffered image from the images directory of a particular sprite
     * @param sprite - the sprite texture to load
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public static BufferedImage load(SpriteTex sprite)  throws IllegalArgumentException {

        if (sprite == null){
            throw new IllegalArgumentException("Sprite texture parameter must not be null");
        }
        BufferedImage img = null;
        try {
            String file = getSpriteFile(sprite);
            img = ImageIO.read(sInstance.getClass().getResource(file));
        }catch (IOException e){
            System.out.print("Could not open texture :" + sprite.toString());
            System.exit(1);
        }
        return img;
    }


    public static File loadFile(SpriteTex sprite){


        File img = null;

            String file = getSpriteFile(sprite);
            img = new File(sInstance.getClass().getResource(file).getFile());

        return img;
    }

}
