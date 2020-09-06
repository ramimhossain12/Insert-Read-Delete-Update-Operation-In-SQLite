package com.shoedazzle.m.listviewdemodatabase;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //DatabaseHelper Class Object;
    DatabaseHelper databaseHelper;

    //Find Data;
    EditText id, name;

    //Add Button;
    Button addData;

    //Show Data;
    Button showData;

    //Update Data;
    Button updateData;

    //Delete data;
    Button delete;

    //SQLiteDatabase
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.userid);
        name = findViewById(R.id.username);
        addData = findViewById(R.id.button);
        addData.setOnClickListener(this);

        //ShowData;
        showData = findViewById(R.id.show);
        showData.setOnClickListener(this);

        //UpdateData;
        updateData = findViewById(R.id.update);
        updateData.setOnClickListener(this);

        //Delete Data;
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);

        //Return Database;
        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {

        String Name = name.getText().toString();
        String Id = id.getText().toString();

        if (v.getId() == R.id.button) {

            if (Id.equals("") && Name.equals("")) {
                Toast.makeText(getApplicationContext(), "Please Enter all data", Toast.LENGTH_LONG).show();
            } else {
                //Call insertData;
                long rowNumber = databaseHelper.saveData(Id, Name);
                if (rowNumber > -1) {
                    Toast.makeText(getApplicationContext(), "Data Insert Successfull", Toast.LENGTH_LONG).show();
                    name.setText(" ");
                    id.setText(" ");
                } else {
                    Toast.makeText(getApplicationContext(), "Not Successfull Insert", Toast.LENGTH_LONG).show();
                }
            }

        }
        else if (v.getId() == R.id.show) {

            Intent intent = new Intent(MainActivity.this, ListData.class);
            startActivity(intent);

        }
        else if (v.getId() == R.id.update) {

                boolean isUpdated = databaseHelper.updateData(Id,Name);
                if (isUpdated == true) {
                    Toast.makeText(getApplicationContext(), "Successful UpDate", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "UnSuccessful UpDate", Toast.LENGTH_LONG).show();
                }
            }

            else if (v.getId() == R.id.delete) {

            int value =  databaseHelper.deleteData(Id);
            if(value>0){
                Toast.makeText(getApplicationContext(),"Successful Delete",Toast.LENGTH_LONG).show();
            }
            else{

                Toast.makeText(getApplicationContext(),"UnSuccessful Delete",Toast.LENGTH_LONG).show();
            }

            }

        }
}
