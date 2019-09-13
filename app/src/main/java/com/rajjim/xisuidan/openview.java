package com.rajjim.xisuidan;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


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
