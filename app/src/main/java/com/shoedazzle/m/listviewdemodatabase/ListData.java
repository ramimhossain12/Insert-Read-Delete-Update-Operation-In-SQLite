package com.shoedazzle.m.listviewdemodatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListData extends AppCompatActivity {

    ListView listView;

    //DataBaseHelper class Object;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        listView = findViewById(R.id.list);

        //DataBaseHelper class Object;
        databaseHelper = new DatabaseHelper(this);
        lodeData();
    }

    public void lodeData(){
        ArrayList<String> listData = new ArrayList<String>();
        Cursor cursor = databaseHelper.showAllData();

        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No Data is found! ",Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()){
                listData.add(cursor.getString(0)+" \t"+cursor.getString(1));


            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.listviewid,listData);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Selectvalue = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Select Value :"+Selectvalue,Toast.LENGTH_LONG).show();
            }
        });

    }
}
