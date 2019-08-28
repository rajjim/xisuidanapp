package com.rahjim.xisuidan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class puresult extends AppCompatActivity {

    Button btret;
    Button btagain;
    Button btretmain;
    TextView tvtitle;
    LinearLayout resultll;

    int height;
    int width;
    int level;
    int time;

    String timeIntToString(int zongmiao){
        int s=zongmiao/216000;
        int f=zongmiao/3600;
        int m =zongmiao / 60;
        int hm = zongmiao;
        return s%24+":"+f%60+":"+m% 60 + "." + hm %60;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        btret=(Button)findViewById(R.id.btret);
        btretmain=(Button)findViewById(R.id.btretmain);
        btagain=(Button)findViewById(R.id.btagain);

        btret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(puresult.this, pu.class);
                intent.putExtra("level", level);
                puresult.this.startActivity(intent);
                finish();
            }
        });

        btretmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(puresult.this, pujiyi.class);
                intent.putExtra("level", level);
                puresult.this.startActivity(intent);
                finish();
            }
        });

        tvtitle=(TextView)findViewById(R.id.tvtitle);
        resultll=(LinearLayout)findViewById(R.id.resultll);
        //get the display
        Display display = getWindowManager().getDefaultDisplay();
        Point localPoint = new Point();
        display.getSize(localPoint);
        this.width = localPoint.x;
        this.height = localPoint.y;

        //get intent data
        level=this.getIntent().getIntExtra("level",0);
        time=this.getIntent().getIntExtra("time",0);
        ArrayList<Integer> randomStrings=this.getIntent().getIntegerArrayListExtra("randomStrings");
        ArrayList<Integer> wrongindexs=this.getIntent().getIntegerArrayListExtra("wrongindexs");
        String wrrate=this.getIntent().getStringExtra("wrrate");
        ArrayList<Integer> answerlist=this.getIntent().getIntegerArrayListExtra("answerlist");

        String titletext="用时："+timeIntToString(time)+"，正确率："+wrrate;
        tvtitle.setText(titletext);
        int wrongindex;
        int wrongxiabiao=0;
        int wrongcount=wrongindexs.size();
        if(wrongcount==0)
            wrongindex=-1;
        else
            wrongindex=wrongindexs.get(wrongxiabiao++);
        HorizontalScrollView hsv=new HorizontalScrollView(puresult.this);
        hsv.setLayoutParams(new ViewGroup.LayoutParams(-1, -2) );
        LinearLayout hll=new LinearLayout(puresult.this);
        hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        hll.setOrientation(LinearLayout.HORIZONTAL);
        hll.setGravity(1);
        hsv.addView(hll);
        //init ImageButton list
        for(int ii=0;ii<level;ii++){
            LinearLayout vll=new LinearLayout(puresult.this);
            vll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            vll.setOrientation(LinearLayout.VERTICAL);
            ImageButton et=new ImageButton(puresult.this);
            et.setLayoutParams(new ViewGroup.LayoutParams(puresult.this.width / 2, puresult.this.width / 2 * 150 / 105));
            et.setBackgroundResource(answerlist.get(ii));
            TextView tv=new TextView(puresult.this);
            tv.setText(String.valueOf(ii+1));
            tv.setGravity(1);
            tv.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            if(wrongindex==ii){
                tv.setBackgroundColor(Color.RED);
                if(wrongxiabiao+1<=wrongcount)
                    wrongindex=wrongindexs.get(wrongxiabiao++);
            }
            vll.addView(et);
            vll.addView(tv);
            hll.addView(vll);

        }

        resultll.addView(hsv);
        
        
        TextView tvright=new TextView(puresult.this);
        tvright.setText("正确答案");
        tvright.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
        tvright.setGravity(1);
        resultll.addView(tvright);


        hsv=new HorizontalScrollView(puresult.this);
        hsv.setLayoutParams(new ViewGroup.LayoutParams(-1, -2) );
        hll=new LinearLayout(puresult.this);
        hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        hll.setOrientation(LinearLayout.HORIZONTAL);
        hll.setGravity(1);
        hsv.addView(hll);
        //init ImageButton list
        for(int ii=0;ii<level;ii++){
            LinearLayout vll=new LinearLayout(puresult.this);
            vll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            vll.setOrientation(LinearLayout.VERTICAL);
            ImageButton et=new ImageButton(puresult.this);
            et.setLayoutParams(new ViewGroup.LayoutParams(puresult.this.width / 2, puresult.this.width / 2 * 150 / 105));
            et.setBackgroundResource(randomStrings.get(ii));
            TextView tv=new TextView(puresult.this);
            tv.setText(String.valueOf(ii+1));
            tv.setGravity(1);
            tv.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            vll.addView(et);
            vll.addView(tv);
            hll.addView(vll);

        }

        resultll.addView(hsv);


    }
}
