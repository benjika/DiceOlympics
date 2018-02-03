package com.example.beni.diceolympics;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Beni on 03-Feb-18.
 */

public class Sounds {

    static private boolean isMute = false;

    public static boolean getIsMute() {
        return isMute;
    }

    public static void setIsMute(boolean isMuteNew) {
        Sounds.isMute = isMuteNew;
    }

    static void playShakerdice1(Context context){
        if(!isMute) {
            MediaPlayer shakerDice1 = MediaPlayer.create(context,R.raw.shakerdice1);
            shakerDice1.start();}
    }

    static void playShakerdice2(Context context){
        if(!isMute) {
            MediaPlayer shakerDice2 = MediaPlayer.create(context,R.raw.shakerdice2);
            shakerDice2.start();}
    }

    static void playShakerdice3(Context context){
        if(!isMute) {
            MediaPlayer shakerDice3 = MediaPlayer.create(context,R.raw.shakerdice3);
            shakerDice3.start();}
    }

    static void playVictory(Context context){
        if(!isMute) {
            MediaPlayer Victory = MediaPlayer.create(context,R.raw.victory);
            Victory.start();}
    }

    static void playButtonPress(Context context){
        if(!isMute) {
            MediaPlayer buttonPress = MediaPlayer.create(context,R.raw.buttonpress);
            buttonPress.start();}
    }

    static void playSlide(Context context){
        if(!isMute) {
            MediaPlayer slide = MediaPlayer.create(context,R.raw.slide);
           slide.start();}
    }

}
