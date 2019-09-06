package com.rahjim.xisuidan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ddjiyi extends AppCompatActivity {


    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    String[] dd = {};
    Button jiyibtok;
    Button ddjiyibtstart;
    LinearLayout jiyill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiyi);

        jiyill=(LinearLayout)findViewById(R.id.jiyill);
        ddjiyibtstart=(Button)findViewById(R.id.jiyibtstart);
        jiyibtok=(Button)findViewById(R.id.jiyibtok);
        jiyibtok.setEnabled(false);

        ddjiyibtstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        jiyibtok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
