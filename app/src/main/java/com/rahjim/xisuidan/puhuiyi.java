package com.rahjim.xisuidan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class puhuiyi extends AppCompatActivity {

    LinearLayout huiyill;
    Button huiyibtok;
    int height;
    int width;
    int level;
    int time;
    DatabaseHelper dbhelper;
    SQLiteDatabase db;

    //prepare pu list
    ArrayList<ImageButton> prlist=new ArrayList();
    //chooseButtonList
    ArrayList<ImageButton> etlist = new ArrayList();


    int[] pu = {R.drawable.pu00,R.drawable.pu01,R.drawable.pu02,R.drawable.pu03,R.drawable.pu04,R.drawable.pu05,R.drawable.pu06,R.drawable.pu07,R.drawable.pu08,R.drawable.pu09,R.drawable.pu10,
            R.drawable.pu11,R.drawable.pu12,R.drawable.pu13,R.drawable.pu14,R.drawable.pu15,R.drawable.pu16,R.drawable.pu17,R.drawable.pu18,R.drawable.pu19,R.drawable.pu20,
            R.drawable.pu21,R.drawable.pu22,R.drawable.pu23,R.drawable.pu24,R.drawable.pu25,R.drawable.pu26,R.drawable.pu27,R.drawable.pu28,R.drawable.pu29,R.drawable.pu30,
            R.drawable.pu31,R.drawable.pu32,R.drawable.pu33,R.drawable.pu34,R.drawable.pu35,R.drawable.pu36,R.drawable.pu37,R.drawable.pu38,R.drawable.pu39,R.drawable.pu40,
            R.drawable.pu41,R.drawable.pu42,R.drawable.pu43,R.drawable.pu44,R.drawable.pu45,R.drawable.pu46,R.drawable.pu47,R.drawable.pu48,R.drawable.pu49,R.drawable.pu50,
            R.drawable.pu51,R.drawable.pu52
    };

    int currentedit=4000;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.huiyi);
        Display display = getWindowManager().getDefaultDisplay();
        Point localPoint = new Point();
        display.getSize(localPoint);
        this.width = localPoint.x;
        this.height = localPoint.y;
        //init database
        this.dbhelper=new DatabaseHelper(this);
        this.db=dbhelper.getWritableDatabase();
        level=this.getIntent().getIntExtra("level",0);
        time=this.getIntent().getIntExtra("time",0);
        final ArrayList<Integer> randomStrings=this.getIntent().getIntegerArrayListExtra("randomStrings");

        huiyill=(LinearLayout)findViewById(R.id.huiyill);
        huiyibtok=(Button)findViewById(R.id.huiyibtok);

        HorizontalScrollView hsv=new HorizontalScrollView(puhuiyi.this);
        hsv.setLayoutParams(new ViewGroup.LayoutParams(-1, -2) );

        LinearLayout hll=new LinearLayout(puhuiyi.this);
        hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        hll.setOrientation(LinearLayout.HORIZONTAL);
        hll.setGravity(1);

        hsv.addView(hll);
        //init ImageButton list
        for(int ii=1;ii<53;ii++){
            ImageButton et=new ImageButton(puhuiyi.this);
            et.setLayoutParams(new ViewGroup.LayoutParams(puhuiyi.this.width / 2, puhuiyi.this.width / 2 * 150 / 105));
            et.setBackgroundResource(pu[ii]);

            //set prepare pu button function
            et.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


            prlist.add(et);
            hll.addView(et);

        }
        huiyill.addView(hsv);

        //reset button
        Button btreset=new Button(this);
        btreset.setText("撤回");
        btreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        huiyill.addView(btreset);



        hsv=new HorizontalScrollView(puhuiyi.this);
        hsv.setLayoutParams(new ViewGroup.LayoutParams(-1, -2) );
        hll=new LinearLayout(puhuiyi.this);
        hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        hll.setOrientation(LinearLayout.HORIZONTAL);
        hll.setGravity(1);
        hsv.addView(hll);
        //init ImageButton list
        for(int ii=0;ii<level;ii++){
            ImageButton et=new ImageButton(puhuiyi.this);
            et.setLayoutParams(new ViewGroup.LayoutParams(puhuiyi.this.width / 2, puhuiyi.this.width / 2 * 150 / 105));
            et.setBackgroundResource(pu[0]);

            //set prepare pu button function
            et.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


            etlist.add(et);
            hll.addView(et);

        }

        huiyill.addView(hsv);


        //add commit function to button
        huiyibtok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 1.post wrong index list to next activity for mark red sign
                // 2.make wrong / right rate  to string and post to next activity
                // 3.post answer list and random list to next activity for compare
                // 4.save the result to sqlist
                // 5.post level to next activity
                ArrayList<Integer> wrongindexs=new ArrayList();
                ArrayList<Integer> answerlist=new ArrayList();
                int rightcount=0;
                for(int i=0;i<level;i++) {
                    answerlist.add(etlist.get(i).getId());
                    if(answerlist.get(i)==randomStrings.get(i)){
                        rightcount++;
                    }else{
                        wrongindexs.add(i);
                    }
                }
                String wrrate=rightcount+"/"+level;
                String datestr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                ContentValues localContentValues = new ContentValues();
                localContentValues.put("type", "pu");
                localContentValues.put("level", level);
                localContentValues.put("usetime", timeIntToString(time));
                localContentValues.put("timeint",time);
                localContentValues.put("date", datestr);
                localContentValues.put("wrrate", wrrate);
                long result=db.insert("memory", null, localContentValues);
                Toast.makeText(puhuiyi.this,"执行插入结果是："+result,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(puhuiyi.this, puresult.class);
                intent.putExtra("wrongindexs",wrongindexs);
                intent.putExtra("wrrate",wrrate);
                intent.putExtra("answerlist",answerlist);
                intent.putExtra("randomStrings", randomStrings);
                intent.putExtra("level",puhuiyi.this.level);
                intent.putExtra("time",puhuiyi.this.time);
                puhuiyi.this.startActivity(intent);
                puhuiyi.this.finish();
                return;


            }
        });
    }


    String timeIntToString(int zongmiao){
        int s=zongmiao/216000;
        int f=zongmiao/3600;
        int m =zongmiao / 60;
        int hm = zongmiao;
        return s%24+":"+f%60+":"+m% 60 + "." + hm %60;
    }
}
