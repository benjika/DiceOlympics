package com.example.beni.diceolympics;

import android.media.MediaPlayer;

/**
 * Created by Beni on 03-Feb-18.
 */

public class Sounds {

    private static boolean isMute = false; //if app is mute, isMute = true. else, can play sound

    //if app is mute, return true, else return false
    static public boolean getIsMute() {
        return isMute;
    }

    //sets new vale for Is mute
    public static void setIsMute(boolean newIsMute) {
        isMute = newIsMute;
    }

}
