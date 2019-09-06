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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ddresult extends AppCompatActivity {

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
                Intent intent = new Intent(ddresult.this, didian.class);
                intent.putExtra("level", level);
                ddresult.this.startActivity(intent);
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
                Intent intent = new Intent(ddresult.this, ddjiyi.class);
                intent.putExtra("level", level);
                ddresult.this.startActivity(intent);
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
        ArrayList<String> randomStrings=this.getIntent().getStringArrayListExtra("randomStrings");
        ArrayList<Integer> wrongindexs=this.getIntent().getIntegerArrayListExtra("wrongindexs");
        String wrrate=this.getIntent().getStringExtra("wrrate");
        ArrayList<String> answerlist=this.getIntent().getStringArrayListExtra("answerlist");

        String titletext="用时："+timeIntToString(time)+"，正确率："+wrrate;
        tvtitle.setText(titletext);

        LinearLayout hll=new LinearLayout(ddresult.this);
        hll.setGravity(Gravity.CENTER);
        hll.setPadding(0, 5, 0, 0);
        hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        hll.setOrientation(LinearLayout.HORIZONTAL);
        hll.setGravity(1);
        int wrongindex;
        int wrongxiabiao=0;
        int wrongcount=wrongindexs.size();
        if(wrongcount==0)
            wrongindex=-1;
        else
            wrongindex=wrongindexs.get(wrongxiabiao++);
        for(int ii=0;ii<ddresult.this.level;ii++){

            EditText et=new EditText(ddresult.this);
            et.setText(answerlist.get(ii));
            et.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
            et.setLayoutParams(new ViewGroup.LayoutParams(ddresult.this.width / 5, -2));
            et.setFocusable(false);
            et.setInputType(0);
            et.setPadding(0, 0, 0, 0);
            et.setGravity(17);
            et.setFilters(new InputFilter[] { new InputFilter.LengthFilter(4) });
            et.setTextSize(0, ddresult.this.width / 23);
            if(wrongindex==ii){
                et.setBackgroundColor(Color.RED);
                if(wrongxiabiao+1<=wrongcount)
                    wrongindex=wrongindexs.get(wrongxiabiao++);
            }
            hll.addView(et);

            if((ii+1)%5==0){
                resultll.addView(hll);
                hll=new LinearLayout(ddresult.this);
                hll.setGravity(Gravity.CENTER);
                hll.setPadding(0, 5, 0, 0);
                hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                hll.setOrientation(LinearLayout.HORIZONTAL);
                hll.setGravity(1);
            }
        }
        TextView tvright=new TextView(ddresult.this);
        tvright.setText("正确答案");
        tvright.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
        tvright.setGravity(1);
        resultll.addView(tvright);
        hll=new LinearLayout(ddresult.this);
        hll.setGravity(Gravity.CENTER);
        hll.setPadding(0, 5, 0, 0);
        hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        hll.setOrientation(LinearLayout.HORIZONTAL);
        hll.setGravity(1);
        for(int ii=0;ii<ddresult.this.level;ii++){
            EditText et=new EditText(ddresult.this);
            et.setText(randomStrings.get(ii));
            et.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
            et.setLayoutParams(new ViewGroup.LayoutParams(ddresult.this.width / 5, -2));
            et.setFocusable(false);
            et.setInputType(0);
            et.setPadding(0, 0, 0, 0);
            et.setGravity(17);
            et.setFilters(new InputFilter[] { new InputFilter.LengthFilter(4) });
            et.setTextSize(0, ddresult.this.width / 23);
            hll.addView(et);

            if((ii+1)%5==0){
                resultll.addView(hll);
                hll=new LinearLayout(ddresult.this);
                hll.setGravity(Gravity.CENTER);
                hll.setPadding(0, 5, 0, 0);
                hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                hll.setOrientation(LinearLayout.HORIZONTAL);
                hll.setGravity(1);
            }
        }


    }
}
