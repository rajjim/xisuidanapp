package com.rahjim.xisuidan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class numjiyi extends AppCompatActivity {

    Bundle b;
    Button jiyibtok;
    Button numjiyibtstart;
    LinearLayout jiyill;
    int height;
    int level;
    int width;
    ArrayList<String> randomStrings;
    int zongmiao = 0;

    boolean isstop = true;

    Handler mHandler = new Handler()
    {
        public void handleMessage(Message paramAnonymousMessage)
        {
            super.handleMessage(paramAnonymousMessage);
            switch (paramAnonymousMessage.what)
            {
            }
            while(isstop)return;
            zongmiao += 1;
            int s=zongmiao/216000;
            int f=zongmiao/3600;
            int m = numjiyi.this.zongmiao / 60;
            int hm = numjiyi.this.zongmiao;
            numjiyi.this.numjiyibtstart.setText(s%24+":"+f%60+":"+m% 60 + "." + hm %60);
            numjiyi.this.mHandler.sendEmptyMessageDelayed(1, 1);
        }
    };



    public static int adjustFontSize(int paramInt1, int paramInt2)
    {
        if (paramInt1 <= 240) {
            return 13;
        }
        if (paramInt1 <= 320) {
            return 16;
        }
        if (paramInt1 <= 480) {
            return 18;
        }
        if (paramInt1 <= 540) {
            return 21;
        }
        if (paramInt1 <= 800) {
            return 23;
        }
        return 25;
    }


    public ArrayList<String> getRandomStrings(){
        ArrayList<String> rets=new ArrayList();
        Random r = new Random();
        int numStringSums=10;
        for(int i=0;i<level;i++){
            int xiabiao=r.nextInt(numStringSums);
            rets.add(String.valueOf(xiabiao));
        }
        return rets;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiyi);
        Display display = getWindowManager().getDefaultDisplay();
        Point localPoint = new Point();
        display.getSize(localPoint);
        this.width = localPoint.x;
        this.height = localPoint.y;
        this.b = getIntent().getExtras();
        jiyill=(LinearLayout)findViewById(R.id.jiyill);
        numjiyibtstart=(Button)findViewById(R.id.jiyibtstart);
        jiyibtok=(Button)findViewById(R.id.jiyibtok);
        jiyibtok.setEnabled(false);
        level=b.getInt("level");

        this.randomStrings=getRandomStrings();
        numjiyibtstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numjiyibtstart.setClickable(false);
                jiyibtok.setEnabled(true);
                LinearLayout hll=new LinearLayout(numjiyi.this);
                hll.setPadding(0, 5, 0, 0);
                hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                hll.setOrientation(LinearLayout.HORIZONTAL);
                hll.setGravity(1);
                for(int ii=0;ii<numjiyi.this.level;ii++){
                    EditText et=new EditText(numjiyi.this);
                    et.setText(randomStrings.get(ii));
                    et.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                    et.setLayoutParams(new ViewGroup.LayoutParams(numjiyi.this.width / 11, -2));
                    et.setFilters(new InputFilter[] { new InputFilter.LengthFilter(1) });
                    et.setInputType(2);
                    et.setFocusable(false);
                    et.setInputType(0);
                    hll.addView(et);

                    if((ii+1)%10==0){
                        jiyill.addView(hll);
                        hll=new LinearLayout(numjiyi.this);
                        hll.setPadding(0, 5, 0, 0);
                        hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                        hll.setOrientation(LinearLayout.HORIZONTAL);
                        hll.setGravity(1);
                    }
                }
                isstop = false;
                mHandler.sendEmptyMessage(1);
            }
        });
        jiyibtok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numjiyi.this.isstop = true;
                numjiyi.this.mHandler.sendEmptyMessage(1);
                Intent intent = new Intent(numjiyi.this, numhuiyi.class);
                intent.putExtra("level",numjiyi.this.level);
                intent.putExtra("time", numjiyi.this.zongmiao);
                intent.putExtra("randomStrings", numjiyi.this.randomStrings);
                numjiyi.this.startActivity(intent);
                numjiyi.this.finish();
                return;
            }
        });
    }
}
