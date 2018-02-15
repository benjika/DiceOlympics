package com.example.beni.diceolympics;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;

/**
 * Created by Beni on 02-Feb-18.
 */

public class Shaker {

    private static float accelVal;
    private static float accelLast;
    private static float shake;
    private static boolean canShake = true;

    public static String getNameOfGame() {
        return NameOfGame;
    }

    public static void setNameOfGame(String nameOfGame) {
        NameOfGame = nameOfGame;
    }

    private static String NameOfGame;


    static final SensorEventListener sensorListener = new SensorEventListener()
            // listener of the physical shake
    {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            accelLast = accelVal;
            accelVal = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = accelVal - accelLast;
            shake = shake * 0.9f + delta;

            if (shake > 12 && canShake) {

                canShake = false;
                countDown();
                if (NameOfGame.equals("Pigdice")) PigdiceGame.UniversalShake();
                else if (NameOfGame.equals("Midnight")) MidnightGame.UltimateShake();
                else if (NameOfGame.equals("Who's Starting")) WhosStarting.UniversalShake();

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };


    static void startShaker(SensorManager shakeManager)
    // Initializes the physical shake
    {
        shakeManager.registerListener(sensorListener, shakeManager.getDefaultSensor
                (Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        accelVal = SensorManager.GRAVITY_EARTH;
        accelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;

    }

    private static void countDown()
    // countdowns a second from one physical shake to another
    // to avoid many shakes at once
    {
        new CountDownTimer(1000, 500) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                canShake = true;
            }
        }.start();
    }

}
