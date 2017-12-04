package _08final.src.mvc.model;

import _08final.src.images.SpriteTexLoader;
import _08final.src.mvc.controller.Game;
import java.awt.*;

/**
 * Created by kirinmasood on 12/1/2016.
 */
public class DisplayControl {

    public String cont="";

    DisplayControl (String cont)
    {
        this.cont= cont;
        controlling();
    }

    /**
     * Controls the values of the display
     * @return values
     */

    public DisplayValues controlling()
    {
        DisplayValues dp = new DisplayValues();
        return dp;
    }
}
