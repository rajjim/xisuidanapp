package com.rahjim.xisuidan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ddhuiyi extends AppCompatActivity {

    LinearLayout huiyill;
    Button huiyibtok;
    int height;
    int width;
    int level;
    int xiangyingddshu = 0;
    int time;
    ArrayList<EditText> etlist = new ArrayList();
    DatabaseHelper dbhelper;
    SQLiteDatabase db;




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
        final ArrayList<String> randomStrings=this.getIntent().getStringArrayListExtra("randomStrings");

        huiyill=(LinearLayout)findViewById(R.id.huiyill);
        huiyibtok=(Button)findViewById(R.id.huiyibtok);

        LinearLayout hll=new LinearLayout(ddhuiyi.this);
        hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        hll.setOrientation(LinearLayout.HORIZONTAL);
        hll.setGravity(1);

        //init et list
        for(int ii=0;ii<level;ii++){
            EditText et=new EditText(ddhuiyi.this);
            et.setLayoutParams(new ViewGroup.LayoutParams(this.width / 5, -2));
            et.setFilters(new InputFilter[] { new InputFilter.LengthFilter(4) });
            et.setId(ii + 1000);
            et.setPadding(0, 0, 0, 0);
            et.setGravity(17);
            et.setTextSize(0, this.width / 23);

            //add delete content jump to fore et's function
            et.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    ddhuiyi.this.xiangyingddshu += 1;
                    if (ddhuiyi.this.xiangyingddshu == 1)
                    {
                        if (i == KeyEvent.KEYCODE_DEL)
                        {
                            i= (int)(view.getId()) - 1;
                            EditText et2 = (EditText)ddhuiyi.this.findViewById(i + 1);
                            if ((i >= 1000) && (et2.getText().toString().trim().equals(""))) {
                                ((EditText)ddhuiyi.this.findViewById(i)).requestFocus();
                            }
                        }
                        return false;
                    }
                    ddhuiyi.this.xiangyingddshu = 0;
                    return false;
                }
            });
            etlist.add(et);
            hll.addView(et);

            if((ii+1)%5==0){
                huiyill.addView(hll);
                hll=new LinearLayout(ddhuiyi.this);
                hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                hll.setOrientation(LinearLayout.HORIZONTAL);
                hll.setGravity(1);
            }
        }
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
                ArrayList<String> answerlist=new ArrayList();
                int rightcount=0;
                for(int i=0;i<level;i++) {
                    answerlist.add(etlist.get(i).getText().toString().trim());
                    if(answerlist.get(i).equals(randomStrings.get(i))){
                        rightcount++;
                    }else{
                        wrongindexs.add(i);
                    }
                }
                String wrrate=rightcount+"/"+level;
                String datestr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                ContentValues localContentValues = new ContentValues();
                localContentValues.put("type", "dd");
                localContentValues.put("level", level);
                localContentValues.put("usetime", timeIntToString(time));
                localContentValues.put("timeint",time);
                localContentValues.put("date", datestr);
                localContentValues.put("wrrate", wrrate);
                long result=db.insert("memory", null, localContentValues);
//                Toast.makeText(ddhuiyi.this,"执行插入结果是："+result,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ddhuiyi.this, ddresult.class);
                intent.putExtra("wrongindexs",wrongindexs);
                intent.putExtra("wrrate",wrrate);
                intent.putExtra("answerlist",answerlist);
                intent.putExtra("randomStrings", randomStrings);
                intent.putExtra("level",ddhuiyi.this.level);
                intent.putExtra("time",ddhuiyi.this.time);
                ddhuiyi.this.startActivity(intent);
                ddhuiyi.this.finish();
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
