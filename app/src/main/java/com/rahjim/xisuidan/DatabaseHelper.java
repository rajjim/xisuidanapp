package com.rahjim.xisuidan;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DatabaseHelper
        extends SQLiteOpenHelper
{
    private static final String PATH= Environment.getExternalStorageDirectory().toString() + "/xisuidan";
    public static final String DB_NAME = PATH+"/xisuidan.db";
    private static final int version = 2;
    private static final String CREATE_TABLE="create table memory(type varchar(20) not null , timeint int not null,usetime varchar(20) not null,level int not null,wrrate varchar(20) not null,date timestamp not null)";
    private static final String CREATE_TABLE2="create table didian(num int not null,pointname varchar(20) not null)";

    public DatabaseHelper(Context paramContext)
    {
        super(paramContext, DB_NAME, null, 1);
    }

    public void close()
    {
        super.close();
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
        try {
            paramSQLiteDatabase.execSQL(CREATE_TABLE);
            paramSQLiteDatabase.execSQL(CREATE_TABLE2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
}
