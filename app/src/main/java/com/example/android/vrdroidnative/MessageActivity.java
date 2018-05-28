package com.example.android.vrdroidnative;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.cre.vrandroid.UnityPlayerActivity;
import com.example.android.vrdroidnative.MainActivity;
import com.unity3d.unitygvr.GoogleVR;

public class MessageActivity extends AppCompatActivity {  //extends UnityPlayerActivity does not work

    public int counter = 0;
    public Boolean asyncStarted = false;
    private int total = 3000; // the total number
    public AsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

    }

    public void onUnityVRClick(View view)
    {
        Intent intent = new Intent(this, UnityPlayerActivity.class);
        startActivity(intent);
    }


    public static String ReturnMessage()
    {
        String message = new String("It works ");
        return message;
    }

    public void onBackButtonClick(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void timerFunc(PluginCallback pluginCallback){
        pluginCallback.onSuccess(1);
    }

    public void StartAsyncTask()
    {
        //when you want to start the counting start the thread bellow.
        if(asyncStarted == false) {
            asyncStarted = true;
            final TextView tvId = (TextView) findViewById(R.id.counter);

            AsyncTask.execute(new Runnable() {

                public void run() {
                    while (counter < total) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        tvId.post(new Runnable() {

                            public void run() {
                                tvId.setText("" + counter);

                            }

                        });
                        counter++;
                    }

                }

            });
        }
        else
            return;
    }

    public void OnClickTimerService(View view)
    {
        StartAsyncTask();
    }


}
