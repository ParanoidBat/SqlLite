package com.example.android.sqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText et_old_paisa, et_new_paisa, et_new_name;
    Button btn_update;
    String new_paisa, new_name, old_paisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        et_old_paisa = findViewById(R.id.et_old_paisa);
        et_new_paisa = findViewById(R.id.et_new_paisa);
        et_new_name = findViewById(R.id.et_new_name);

        btn_update = findViewById(R.id.btn_update);
    }

    private void fetchData(){
        old_paisa = et_old_paisa.getText().toString();
        new_paisa = et_new_paisa.getText().toString();
        new_name = et_new_name.getText().toString();
    }

    private void clear(){
        et_old_paisa.setText("");
        et_new_name.setText("");
        et_new_paisa.setText("");
    }

    public void onClickUpdate(View v){
        fetchData();

        DataBaseHandler db = new DataBaseHandler(this);

        Employee employee = new Employee();

        employee.setName(new_name);
        employee.setPaisa(new_paisa);

        db.updateWithPaisa(employee, old_paisa);

        Toast.makeText(this, "Record Updated Successfully!", Toast.LENGTH_SHORT).show();

        clear();
    }
}
