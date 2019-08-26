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
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class pujiyi extends AppCompatActivity {


    Bundle b;
    int[] pu = {R.drawable.pu00,R.drawable.pu01,R.drawable.pu02,R.drawable.pu03,R.drawable.pu04,R.drawable.pu05,R.drawable.pu06,R.drawable.pu07,R.drawable.pu08,R.drawable.pu09,R.drawable.pu10,
            R.drawable.pu11,R.drawable.pu12,R.drawable.pu13,R.drawable.pu14,R.drawable.pu15,R.drawable.pu16,R.drawable.pu17,R.drawable.pu18,R.drawable.pu19,R.drawable.pu20,
            R.drawable.pu21,R.drawable.pu22,R.drawable.pu23,R.drawable.pu24,R.drawable.pu25,R.drawable.pu26,R.drawable.pu27,R.drawable.pu28,R.drawable.pu29,R.drawable.pu30,
            R.drawable.pu31,R.drawable.pu32,R.drawable.pu33,R.drawable.pu34,R.drawable.pu35,R.drawable.pu36,R.drawable.pu37,R.drawable.pu38,R.drawable.pu39,R.drawable.pu40,
            R.drawable.pu41,R.drawable.pu42,R.drawable.pu43,R.drawable.pu44,R.drawable.pu45,R.drawable.pu46,R.drawable.pu47,R.drawable.pu48,R.drawable.pu49,R.drawable.pu50,
            R.drawable.pu51,R.drawable.pu52
    };
    Button jiyibtok;
    Button pujiyibtstart;
    LinearLayout jiyill;
    int height;
    int level;
    int width;
    ArrayList<Integer> randomStrings;
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
            int m = pujiyi.this.zongmiao / 60;
            int hm = pujiyi.this.zongmiao;
            pujiyi.this.pujiyibtstart.setText(s%24+":"+f%60+":"+m% 60 + "." + hm %60);
            pujiyi.this.mHandler.sendEmptyMessageDelayed(1, 1);
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


    public ArrayList<Integer> getRandomStrings(){
        ArrayList<Integer> rets=new ArrayList();
        Random r = new Random();
        int puSums=this.pu.length;
        for(int i=0;i<level;i++){
            int xiabiao=r.nextInt(puSums);
            if(xiabiao==0||(rets.indexOf(pu[xiabiao])>-1)) {
                i--;
                continue;
            }
            rets.add(pu[xiabiao]);
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
        pujiyibtstart=(Button)findViewById(R.id.jiyibtstart);
        jiyibtok=(Button)findViewById(R.id.jiyibtok);
        jiyibtok.setEnabled(false);
        level=b.getInt("level");

        this.randomStrings=getRandomStrings();
        pujiyibtstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pujiyibtstart.setClickable(false);
                jiyibtok.setEnabled(true);
                LinearLayout hll=new LinearLayout(pujiyi.this);
                hll.setGravity(Gravity.CENTER);
                hll.setPadding(0, 5, 0, 0);
                hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                hll.setOrientation(LinearLayout.HORIZONTAL);
                hll.setGravity(1);
                for(int ii=0;ii<pujiyi.this.level;ii++){
                    ImageView localImageView = new ImageView(pujiyi.this);
                    localImageView.setLayoutParams(new ViewGroup.LayoutParams(pujiyi.this.width / 6, pujiyi.this.width / 6 * 150 / 105));
                    localImageView.setBackgroundResource(randomStrings.get(ii));
                    hll.addView(localImageView);

                    if((ii+1)%6==0||(ii==51)){
                        jiyill.addView(hll);
                        hll=new LinearLayout(pujiyi.this);
                        hll.setGravity(Gravity.CENTER);
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
                pujiyi.this.isstop = true;
                pujiyi.this.mHandler.sendEmptyMessage(1);
                Intent intent = new Intent(pujiyi.this, puhuiyi.class);
                intent.putExtra("level",pujiyi.this.level);
                intent.putExtra("time", pujiyi.this.zongmiao);
                intent.putExtra("randomStrings", pujiyi.this.randomStrings);
                pujiyi.this.startActivity(intent);
                pujiyi.this.finish();
                return;
            }
        });
    }
}
