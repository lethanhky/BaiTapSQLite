package com.example.lethanhky_16011051;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBManageSV extends SQLiteOpenHelper {


    public DBManageSV(Context context)
    {
        super(context,  "SVDB.sqlite",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists Lop(id integer primary key autoincrement, "+" name text)");
        sqLiteDatabase.execSQL("create table if not exists SV(id integer primary key autoincrement, "+" name text, classname text, subject text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SV");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Lop");
        onCreate(sqLiteDatabase);
    }
    public ArrayList<SV> getAll(){
        ArrayList<SV> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from  SV" , null);
        if(cursor != null) cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            list.add(new SV(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;

    }
    public ArrayList<String> getAllTen(){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from  SV" , null);
        if(cursor != null) cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;

    }
    public boolean InsertLop(Lop lop){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", lop.getName());
        db.insert("Lop",null, contentValues);
        return true;
    }
    public boolean InsertSV(SV sv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("subject", sv.getSubject());
        contentValues.put("name", sv.getName());
        contentValues.put("classname", sv.getClass_name());
        db.insert("SV",null, contentValues);
        return true;
    }
    public SV getSV(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from SV where id= " + id,null);
        if(cursor != null)  cursor.moveToFirst();
        SV sv = new SV(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        cursor.close();
        db.close();
        return sv;

    }

    public boolean deleteSV(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("delete from SV where id=" + id,null);
        if(cursor != null)  cursor.moveToFirst();
        cursor.close();
        db.close();
        return true;

    }
    public boolean updateSV(int id , SV sv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("subject", sv.getSubject());
        contentValues.put("name", sv.getName());
        contentValues.put("classname", sv.getClass_name());
        db.update("SV", contentValues, "id=" +id, null);
        return true;
    }

}
