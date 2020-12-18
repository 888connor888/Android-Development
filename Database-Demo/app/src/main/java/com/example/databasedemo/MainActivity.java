package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers(name VARCHAR,age INT(3),id INTEGER PRIMARY KEY)");
//        myDatabase.execSQL("INSERT INTO newUsers(name,age) VALUES ('SHUBHAM',21)");
//        myDatabase.execSQL("INSERT INTO newUsers(name,age) VALUES ('VISHAL',23)");
//        myDatabase.execSQL("INSERT INTO newUsers(name,age) VALUES ('VIJAY',21)");
//        myDatabase.execSQL("DELETE  FROM users WHERE name LIKE '%a%' ");
        Cursor c = myDatabase.rawQuery("SELECT * FROM newUsers ",null);
        int nameIndex = c.getColumnIndex("name");
        int ageIndex = c.getColumnIndex("age");
        int idIndex = c.getColumnIndex("id");
        c.moveToFirst();
        while(!c.isAfterLast()){
            Log.i("name","Id: "+ c.getInt(idIndex) + " Name: "+c.getString(nameIndex)+" Age: "+c.getInt(ageIndex));
            c.moveToNext();
        }
    }
}