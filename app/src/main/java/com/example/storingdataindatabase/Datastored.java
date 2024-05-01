package com.example.storingdataindatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Datastored  extends SQLiteOpenHelper {
    public Datastored(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Details(name varchar(30),fathern varchar(30),emaild varchar(20),gender varchar(10),time varchar(10),chceckbox varchar(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertRecord(String n,String f,String e,String gender,String selectedTime,String opt){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("insert into Details values(?,?,?,?,?,?)",new String[]{n,f,e,gender,selectedTime,opt});
        db.close();
    }
    public void deleteData(String n)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from Details where name=?",new String[]{n});
        db.close();
    }
    public String displayData(String gender){
        String dData="";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cr=db.rawQuery("select * from Details",null);
        while(cr.moveToNext()){
            String n=cr.getString(0);
            String f=cr.getString(1);
            String e=cr.getString(2);
            String g=cr.getString(3);
            String t=cr.getString(4);
            String c= cr.getString(5);
            dData += "\n"+n+"\t"+f+"\t"+e+"\t"+g+"\t"+t+"\t"+c;
        }
        return  dData;

    }

}
