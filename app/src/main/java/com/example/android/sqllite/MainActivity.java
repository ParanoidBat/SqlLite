package com.example.android.sqllite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_new, btn_show, btn_delete, btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_new = findViewById(R.id.btn_new);
        btn_delete = findViewById(R.id.btn_delete);
        btn_show = findViewById(R.id.btn_show);
        btn_update = findViewById(R.id.btn_update);
    }

    public void onClickNew(View v){
        Intent intent = new Intent(this, CreateNewActivity.class);

        startActivity(intent);
    }

    public void onClickDelete(View v){
        Intent intent = new Intent(this, DeleteActivity.class);

        startActivity(intent);
    }

    public void onClickShow(View v){
        Intent intent = new Intent(this, ShowActivity.class);

        startActivity(intent);
    }

    public void onClickUpdate(View v){
        Intent intent = new Intent(this, UpdateActivity.class);

        startActivity(intent);
    }
}
