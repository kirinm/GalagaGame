package _08final.src.mvc.view;

import _08final.src.images.SpriteTexLoader;
import _08final.src.mvc.controller.Game;
import _08final.src.mvc.model.Sprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static _08final.src.images.SpriteTexLoader.SpriteTex.BLUE_FIGHTER;
import static _08final.src.images.SpriteTexLoader.getSpriteFile;

/**
 * Created by lamont on 11/20/16.
 */
public class GamePanel extends JPanel {

    BufferedImage imgPlayer;
    private static SpriteTexLoader sInstance = new SpriteTexLoader();
    /**
     * Set properties of game panel
     */
    public GamePanel() {
        this.setPreferredSize(GameFrame.FRAME_DIM);
        try {
            String file = getSpriteFile(SpriteTexLoader.SpriteTex.BACKGROUND);
            imgPlayer = ImageIO.read(sInstance.getClass().getResource(file));
        } catch (Exception ex) {
        }
    }
    @Override

    public void paintComponent(Graphics g){
        // Call the super paintComponent of the panel
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(imgPlayer, 0, 0, GameFrame.FRAME_DIM.width,  GameFrame.FRAME_DIM.height,this);
        super.paintComponent(graphics2D);
        super.paintComponent(graphics2D);
        ArrayList<Sprite> sprites = Game.getDrawableSprites();
        for(Sprite sprite : sprites) {
            sprite.draw(g);
        }
    }


}
