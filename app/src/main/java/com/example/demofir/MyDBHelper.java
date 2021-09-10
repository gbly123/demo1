package com.example.demofir;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.demofir.db.good;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String mTableName ="fooddatamanager";
    private good good;
    public MyDBHelper(@Nullable Context context) {
        super(context, "my.db", null, 2);}
    /***
     * 创建数据库创建一次所有表；
     * */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+mTableName+"(_id integer primary key autoincrement,"
                + "list_id varchar(15)unique,uname varchar(15),product_date varchar(15),keep_date varchar(15),end_line_date varchar(15));");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
