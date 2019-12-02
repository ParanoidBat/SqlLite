package com.example.android.sqllite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class DeleteActivity extends AppCompatActivity {
    EditText et_paisa;
    Button btn_submit;
    String paisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        et_paisa = findViewById(R.id.et_delete);

        btn_submit = findViewById(R.id.btn_submit);
    }

    public void onClickSubmit(View v){
        paisa = et_paisa.getText().toString();

        DataBaseHandler db = new DataBaseHandler(this);

        db.deleteWithPaisa(paisa); //the entery, with this paisa, will be deleted

        Toast.makeText(this, "Record Deleted Successfully!", Toast.LENGTH_SHORT).show();

        finish();
    }
}
