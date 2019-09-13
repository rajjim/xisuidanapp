package com.rajjim.xisuidan;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ddhistory extends AppCompatActivity {


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

    public boolean onContextItemSelected(MenuItem paramMenuItem)
    {
        int i = ((AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo()).position;
        if (paramMenuItem.getItemId() == 0)
        {
            map=listitem.get(i);
            db.execSQL("delete from didian where num = '"+map.get("num")+"'");
            this.listitem.remove(i);
            this.position = i;
            this.sa.notifyDataSetChanged();
            this.lv.invalidate();
        }
        return super.onContextItemSelected(paramMenuItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddhistory);
        btret=(Button)findViewById(R.id.btret);
        btretmain=(Button)findViewById(R.id.btretmain);
        btretmain.setText("增加");

        btret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ddhistory.this, didian.class);
                ddhistory.this.startActivity(intent);
                finish();
            }
        });

        //get pointname by clicked btremain
        btretmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText etpn = new EditText(ddhistory.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(ddhistory.this);
                builder.setTitle("增加的地点名字").setView(etpn)
                        .setNegativeButton("取消", null);
                builder.setPositiveButton("确定增加", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        ContentValues localContentValues = new ContentValues();
                        Cursor cursor = ddhistory.this.db.query("didian", null, null,null, null, null, null);
                        int count = 1;
                        while(cursor.moveToNext()){
                            count++;
                        }
                        localContentValues.put("num", count);
                        localContentValues.put("pointname",etpn.getText().toString().trim());
                        long result=ddhistory.this.db.insert("didian", null, localContentValues);
                        map = new HashMap();
                        map.put("num",count);
                        map.put("pointname",etpn.getText().toString().trim() );

//            Toast.makeText(cihistory.this,map.toString(),Toast.LENGTH_LONG).show();
                        ddhistory.this.listitem.add(map);
                        ddhistory.this.position = count;
                        ddhistory.this.sa.notifyDataSetChanged();
                        ddhistory.this.lv.invalidate();
                        Toast.makeText(ddhistory.this,"执行插入结果是："+result,Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();


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
