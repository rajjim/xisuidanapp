package com.rahjim.xisuidan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


public class openview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openview);
        new Thread()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(2000L);
                    Intent localIntent = new Intent(openview.this, MainActivity.class);
                    openview.this.startActivity(localIntent);
                    openview.this.finish();
                    return;
                }
                catch (InterruptedException localInterruptedException)
                {
                    for (;;)
                    {
                        localInterruptedException.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
