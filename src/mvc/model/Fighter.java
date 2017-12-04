package _08final.src.mvc.model;

import _08final.src.mvc.controller.Game;

/**
 * Created by kirinmasood on 11/25/2016.
 */


public class Fighter {

    public RedFighter redfighter;


    public Fighter(RedFighter redFighter)
    {
        redfighter = redFighter;
    }

    public RedFighter getRedfighter()
    {
        return redfighter;
    }
}
