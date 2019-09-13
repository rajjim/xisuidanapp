package com.rahjim.xisuidan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;

public class puhuiyi extends AppCompatActivity {

    LinearLayout huiyill;
    LinearLayout uphll;
    Button huiyibtok;
    int height;
    int width;
    int level;
    int time;
    DatabaseHelper dbhelper;
    SQLiteDatabase db;

    //prepare pu list
    ArrayList<IbHelper> prlist=new ArrayList();
    //chooseButtonList
    ArrayList<IbHelper> etlist = new ArrayList();

    Stack<IbHelper> prstack=new Stack();

    Map<Integer,ImageButton> predelete=new HashMap();
    Map<Integer,ImageButton> etmap=new HashMap();

    int[] pu = {R.drawable.pu00,R.drawable.pu01,R.drawable.pu02,R.drawable.pu03,R.drawable.pu04,R.drawable.pu05,R.drawable.pu06,R.drawable.pu07,R.drawable.pu08,R.drawable.pu09,R.drawable.pu10,
            R.drawable.pu11,R.drawable.pu12,R.drawable.pu13,R.drawable.pu14,R.drawable.pu15,R.drawable.pu16,R.drawable.pu17,R.drawable.pu18,R.drawable.pu19,R.drawable.pu20,
            R.drawable.pu21,R.drawable.pu22,R.drawable.pu23,R.drawable.pu24,R.drawable.pu25,R.drawable.pu26,R.drawable.pu27,R.drawable.pu28,R.drawable.pu29,R.drawable.pu30,
            R.drawable.pu31,R.drawable.pu32,R.drawable.pu33,R.drawable.pu34,R.drawable.pu35,R.drawable.pu36,R.drawable.pu37,R.drawable.pu38,R.drawable.pu39,R.drawable.pu40,
            R.drawable.pu41,R.drawable.pu42,R.drawable.pu43,R.drawable.pu44,R.drawable.pu45,R.drawable.pu46,R.drawable.pu47,R.drawable.pu48,R.drawable.pu49,R.drawable.pu50,
            R.drawable.pu51,R.drawable.pu52
    };

    ArrayList<Integer> selectedet=new ArrayList();





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

        uphll=new LinearLayout(puhuiyi.this);
        uphll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        uphll.setOrientation(LinearLayout.HORIZONTAL);
        uphll.setGravity(1);

        hsv.addView(uphll);
        //init ImageButton list
        for(int ii=1;ii<53;ii++){
            ImageButton et=new ImageButton(puhuiyi.this);
            et.setLayoutParams(new ViewGroup.LayoutParams(puhuiyi.this.width / 2, puhuiyi.this.width / 2 * 150 / 105));
            et.setBackgroundResource(pu[ii]);

            //set prepare pu button function
            et.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //get res
                    int res=0;
                    IbHelper ih=new IbHelper(null,0,0,false,0,null);
                    int index=0;
                    for(int i=0;i<52;i++){
                        ih=prlist.get(i);
                        if(ih.et==view){
                            res=ih.res;
                            index=ih.index-1;
                            break;
                        }
                    }

                    ih.res=pu[0];
                    ih.et.setBackgroundResource(pu[0]);
                    prstack.push(ih);





                    //location to we need
                    for(int i=0;i<level;i++){
                        ih=etlist.get(i);
                        if(ih.selected){
                            ih.tv.setBackgroundColor(Color.WHITE);
                            ih.et.setBackgroundResource(res);
                            ih.res=res;
                            ih.selected=false;
                            index=ih.index;
                            if((i+1)!=level){
                                ih=etlist.get(i+1);
                                ih.selected=true;
                                ih.tv.setBackgroundColor(Color.CYAN);
                                ih.lastindex=index;
                            }
                            break;
                        }
                    }




                }
            });

            IbHelper ih=new IbHelper(et,pu[ii],ii,false,0,null);
            prlist.add(ih);
            uphll.addView(et);

        }
        huiyill.addView(hsv);

        //reset button
        Button btreset=new Button(this);
        btreset.setText("撤回");
        btreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IbHelper ih;
                for(int i=0;i<level;i++){
                    ih=etlist.get(i);
                    if(ih.selected){
                        int lastindex=ih.lastindex;
                        ih.selected=false;
                        ih.lastindex=0;
                        ih.et.setBackgroundResource(pu[0]);
                        ih.res=pu[0];
                        ih.tv.setBackgroundColor(Color.WHITE);

                        //restore pre list
                        if(!prstack.isEmpty()) {
                            ih = prstack.pop();
                            ih.et.setBackgroundResource(pu[ih.index]);
                            ih.res = pu[ih.index];
                        }
                        //find last one
                        ih=etlist.get(lastindex);
                        ih.selected=true;
                        ih.tv.setBackgroundColor(Color.CYAN);

                        break;
                    }
                }
            }
        });

        huiyill.addView(btreset);



        hsv=new HorizontalScrollView(puhuiyi.this);
        hsv.setLayoutParams(new ViewGroup.LayoutParams(-1, -2) );
        LinearLayout hll=new LinearLayout(puhuiyi.this);
        hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        hll.setOrientation(LinearLayout.HORIZONTAL);
        hll.setGravity(1);
        hsv.addView(hll);
        //init ImageButton list
        for(int ii=0;ii<level;ii++){
            LinearLayout vll=new LinearLayout(puhuiyi.this);
            vll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            vll.setOrientation(LinearLayout.VERTICAL);
            ImageButton et=new ImageButton(puhuiyi.this);
            et.setLayoutParams(new ViewGroup.LayoutParams(puhuiyi.this.width / 2, puhuiyi.this.width / 2 * 150 / 105));
            et.setBackgroundResource(pu[0]);
            TextView tv=new TextView(puhuiyi.this);
            tv.setText(String.valueOf(ii+1));
            tv.setGravity(1);
            tv.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            //set prepare pu button function
            et.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    for(int i=0;i<level;i++) {

                        IbHelper ih = etlist.get(i);

                        if(ih.et==view){
                            ih.selected=true;
                            ih.tv.setBackgroundColor(Color.CYAN);
                        }else{
                            ih.tv.setBackgroundColor(Color.WHITE);
                            ih.selected=false;
                        }

                    }
                        //set all unselected et bgcolor to white
//                    LinearLayout chl=(LinearLayout) view.getParent().getParent();
//                        for(int i=0;i<chl.getChildCount();i++){
//                            LinearLayout cvl=(LinearLayout)chl.getChildAt(i);
//                            TextView ctv=(TextView) cvl.getChildAt(1);
//                            ctv.setBackgroundColor(Color.WHITE);
//                        }


//                        //get parent vll
//                        LinearLayout vl = (LinearLayout) view.getParent();
//                        //get tv and set backgrand color
//                        TextView textview = (TextView) vl.getChildAt(1);
//                        textview.setBackgroundColor(Color.CYAN);


                }
            });
            IbHelper ih=new IbHelper(et,pu[0],ii,false,0,tv);
            etlist.add(ih);
            vll.addView(et);
            vll.addView(tv);
            hll.addView(vll);

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
                    answerlist.add(etlist.get(i).res);
                    if(answerlist.get(i).equals(randomStrings.get(i))){
                        rightcount++;
                    }else{
                        wrongindexs.add(i);
                    }
                    System.out.println("res对比："+answerlist.get(i)+":"+randomStrings.get(i));
                    System.out.println("index是："+etlist.get(i).index);
                    System.out.println("rightcount"+rightcount+"   wrongndexs"+wrongindexs.size());
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
//                Toast.makeText(puhuiyi.this,"执行插入结果是："+result,Toast.LENGTH_LONG).show();
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
