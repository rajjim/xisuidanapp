package com.rahjim.xisuidan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ddjiyi extends AppCompatActivity {


    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    String[] dd = {};
    Button btret;
    Button btretmain;
    LinearLayout jiyill;
    HashMap<String, Object> map;
    ArrayList<HashMap<String, Object>> listitem;
    int position;
    SimpleAdapter sa;
    ListView lv;
    ArrayList<didiandata> pl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddhistory);
        btret=(Button)findViewById(R.id.btret);
        btretmain=(Button)findViewById(R.id.btretmain);

        btret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ddjiyi.this, didian.class);
                ddjiyi.this.startActivity(intent);
                finish();
            }
        });
        btretmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.pl = new ArrayList<>();
        this.lv = ((ListView)findViewById(R.id.lv));
        this.listitem = new ArrayList();
        this.map = new HashMap();
        this.dbhelper=new DatabaseHelper(this);
        this.db=dbhelper.getWritableDatabase();
        Cursor cursor = this.db.query("didian", null, null,null, null, null, null);
        int count = 1;
        while(cursor.moveToNext()){
            count++;
        }
        cursor.moveToFirst();
        for (int i=1;i<count;i++)
        {
            this.sa = new SimpleAdapter(this, this.listitem, R.layout.dditem, new String[] { "num", "pointname" }, new int[] { R.id.itvno,R.id.itvpointname});
            this.lv.setAdapter(this.sa);
            this.lv.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                    contextMenu.setHeaderTitle("删除菜单");
                    contextMenu.add(0, 0, 0, "删除此项");
                    contextMenu.add(0, 1, 0, "取消删除");
                }
            });
            didiandata pl = new didiandata();
            pl.setPointname(cursor.getString(cursor.getColumnIndex("pointname")));
            pl.setNum(cursor.getInt(cursor.getColumnIndex("num")));
            cursor.moveToNext();
            map = new HashMap();
            map.put("num", pl.getNum());
            map.put("pointname", pl.getPointname());

//            Toast.makeText(cihistory.this,map.toString(),Toast.LENGTH_LONG).show();
            this.listitem.add(map);
        }
    }

}
