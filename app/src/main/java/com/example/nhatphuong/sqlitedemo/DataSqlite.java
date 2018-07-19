package com.example.nhatphuong.sqlitedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;

public class DataSqlite extends SQLiteOpenHelper {
    public DataSqlite(Context context, String name, SQLiteDatabase.CursorFactory factory,
            int version) {
        super(context, name, factory, version);
    }
    //Func Thêm Sửa Xóa
    public void QueryData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);

    }

    //Func Select
    public Cursor Getdata(String sql){
        SQLiteDatabase getData = getReadableDatabase();
        return getData.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
