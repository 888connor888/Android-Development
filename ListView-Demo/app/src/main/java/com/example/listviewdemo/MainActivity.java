package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView myListView = (ListView) findViewById(R.id.listView);
        final ArrayList<String> myGroup = new ArrayList<String>();
        myGroup.add("Vishal");
        myGroup.add("Connor");
        myGroup.add("Babul");
        myGroup.add("Vijay");
        myGroup.add("Ritesh");
        myGroup.add("Faisalwa");
        myGroup.add("Chubey Neta");
        myGroup.add("Rahul");
        myGroup.add("Arpit");
        myGroup.add("Debjit");
        myGroup.add("Shivnath");
        myGroup.add("Bagchi");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,myGroup);
        myListView.setAdapter(arrayAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("name",myGroup.get(position));
                Toast.makeText(MainActivity.this, myGroup.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}