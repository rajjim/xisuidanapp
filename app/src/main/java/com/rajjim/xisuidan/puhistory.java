package com.rajjim.xisuidan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class puhistory extends AppCompatActivity {

    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    ArrayList<HashMap<String, Object>> listitem;
    ListView lv;
    HashMap<String, Object> map;
    ArrayList<rememberdata> pl;
    int position;
    SimpleAdapter sa;
    SimpleDateFormat sfd;
    SimpleDateFormat sft;
    Button btret;
    Button btretmain;
    public boolean onContextItemSelected(MenuItem paramMenuItem)
    {
        int i = ((AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo()).position;
        if (paramMenuItem.getItemId() == 0)
        {
            map=listitem.get(i);
            db.execSQL("delete from memory where type = 'pu' and date = '"+map.get("date")+"'");
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
        setContentView(R.layout.history);
        btret=(Button)findViewById(R.id.btret);
        btretmain=(Button)findViewById(R.id.btretmain);

        btret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(puhistory.this, pu.class);
                puhistory.this.startActivity(intent);
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
        this.sfd = new SimpleDateFormat("yyyy-MM-dd");
        this.sft = new SimpleDateFormat("HH:mm:ss");
        this.dbhelper=new DatabaseHelper(this);
        this.db=dbhelper.getWritableDatabase();
        Cursor cursor = this.db.query("memory", null, "type=?", new String[] { "pu" }, null, null, null);
        int count = 0;
        while(cursor.moveToNext()){
            count++;
        }
        cursor.moveToFirst();
        for (int i=0;i<count;i++)
        {
            this.sa = new SimpleAdapter(this, this.listitem, R.layout.item, new String[] { "usetime", "level", "wrrate", "date" }, new int[] { R.id.itvusetime,R.id.itvlevel,R.id.itvwrrate,R.id.itvdate});
            this.lv.setAdapter(this.sa);
            this.lv.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                    contextMenu.setHeaderTitle("删除菜单");
                    contextMenu.add(0, 0, 0, "删除此项");
                    contextMenu.add(0, 1, 0, "取消删除");
                }
            });
            rememberdata pl = new rememberdata();
            pl.setType(cursor.getString(cursor.getColumnIndex("type")));
            pl.setUsetime(cursor.getString(cursor.getColumnIndex("usetime")));
            pl.setTimeint(cursor.getInt(cursor.getColumnIndex("timeint")));
            pl.setLevel(cursor.getInt(cursor.getColumnIndex("level")));
            pl.setWrrate(cursor.getString(cursor.getColumnIndex("wrrate")));
            pl.setDate(cursor.getString(cursor.getColumnIndex("date")));
            cursor.moveToNext();
            map = new HashMap();
            map.put("usetime", pl.getUsetime());
            map.put("level", pl.getLevel());
            map.put("wrrate", pl.getWrrate());
            map.put("date", pl.getDate());
//            Toast.makeText(puhistory.this,map.toString(),Toast.LENGTH_LONG).show();
            this.listitem.add(map);
        }

    }
}
