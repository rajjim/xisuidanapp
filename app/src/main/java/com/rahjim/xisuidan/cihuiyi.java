package com.rahjim.xisuidan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class cihuiyi extends AppCompatActivity {

    LinearLayout huiyill;
    Button huiibtok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cihuiyi);
        int level=this.getIntent().getIntExtra("level",0);
        int zongmiao=this.getIntent().getIntExtra("time",0);
        String[] randomStrings=this.getIntent().getStringArrayExtra("shuzu");
        Toast.makeText(this, level+" "+zongmiao+" "+randomStrings.toString(), Toast.LENGTH_SHORT).show();

    }
}
