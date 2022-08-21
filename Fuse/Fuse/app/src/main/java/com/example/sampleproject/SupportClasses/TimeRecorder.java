package com.example.sampleproject.SupportClasses;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

//I still wanna do a method that directly updates the time to the database and maybe format it?

public class TimeRecorder {
    private Timer timer;
    private TimerTask timerTask;
    private double time;
    private boolean timerStarted;
    private Context context;


    public TimeRecorder(Context context) {
        this.context = context;
        this.timer = new Timer();
        this.timerStarted= false;
        this.time = 0.0;
    }

    public void startRecording() {
        if (!timerStarted) {
            this.timerStarted = true;

            this.timerTask = new TimerTask() {
                @Override
                public void run() {
                    time++;
                }
            };

            this.timer.scheduleAtFixedRate(this.timerTask,0,1);
        }
    }

    public void stopAndResetTimer(boolean playerWon) {
        if (timerStarted) {
            this.timerStarted = false;
            this.timerTask.cancel();
            if (playerWon) Toast.makeText(this.context, "You Win! Your time was " + JamesUtilities.formatMilliseconds((long) this.time) + ".",
                    Toast.LENGTH_SHORT).show();
            Log.d("TIMER STUFF", "Timer Stopped and time = " + getTime() + "seconds.");
            time = 0.0;
        }
    }

    public double getTime() {
        double realTime = this.time - 1;
        return realTime;
    }

    public void setTime(double time) {
        this.time = time;
    }



}
