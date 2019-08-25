package com.rahjim.xisuidan;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper
        extends SQLiteOpenHelper
{
    private static final String DB_NAME = "tpmemory.db";
    private static final int version = 1;

    public DatabaseHelper(Context paramContext)
    {
        super(paramContext, "tpmemory.db", null, 1);
    }

    public void close()
    {
        super.close();
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
        paramSQLiteDatabase.execSQL("create table memory(type varchar(20) not null , usetime varchar(20) not null,level varchar(20) not null,rightcount int not null,date timestamp not null);");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
}
