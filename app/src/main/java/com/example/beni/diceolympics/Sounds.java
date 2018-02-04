package com.example.beni.diceolympics;

import android.widget.ImageButton;

/**
 * Created by Benny on 03-Feb-18.
 */

public class Sounds {

    private static boolean isMute = false; //if app is mute, isMute = true. else, can play sound

    //if app is mute, return true, else return false
    static public boolean getIsMute() {
        return isMute;
    }

    //sets new vale for Is mute
   private static void setIsMute(boolean newIsMute) {
        isMute = newIsMute;
    }

    static void buttonMutePress(ImageButton btnMute){
        if(isMute){
            Sounds.setIsMute(false);
            btnMute.setImageResource(R.drawable.unmute);
        }
        else{
            Sounds.setIsMute(true);
            btnMute.setImageResource(R.drawable.mute);
        }
    }

}
