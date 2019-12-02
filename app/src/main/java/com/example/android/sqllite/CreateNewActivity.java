package com.example.android.sqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNewActivity extends AppCompatActivity {
    EditText et_name, et_paisa;
    Button btn_submit;
    String name, paisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        et_name = findViewById(R.id.et_name);
        et_paisa = findViewById(R.id.et_paisa);

        btn_submit = findViewById(R.id.btn_submit);
    }

    private void fetchData(){
        name = et_name.getText().toString();
        paisa = et_paisa.getText().toString();
    }

    private void clear(){
        et_paisa.setText("");
        et_name.setText("");

        btn_submit.setEnabled(false);
    }

    public void onClickSubmit(View v){
        fetchData(); //get data into strings

        if(name.isEmpty() || paisa.isEmpty()){ //dont want empty strings in db
            Log.d("click","empty fields");
            Toast.makeText(this, "Field/s Is Empty!", Toast.LENGTH_LONG).show();
            return;
        }

        clear(); //set fields empty and disable button to avoid redundant entries

        //feed data to data structure
        Employee employee = new Employee();
        employee.setName(name);
        employee.setPaisa(paisa);

        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);

        dataBaseHandler.addEmployee(employee); //put data into database

        Toast.makeText(this, "Data Entered Successfully!", Toast.LENGTH_SHORT).show();

        btn_submit.setEnabled(true); //set button enabled to get next value
    }
}