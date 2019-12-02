package com.example.android.sqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class ShowActivity extends AppCompatActivity {
    RecyclerView rv;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        rv= findViewById(R.id.rv);

        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);

        List<Employee> employeeList = dataBaseHandler.getData();

        String [] employee_array = new String[employeeList.size()];

        int i=0;
        for(Employee e : employeeList){
            employee_array[i]= String.valueOf(e.getId()) +"; "+ e.getName() +"; "+ e.getPaisa();
            i++;
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);

        adapter = new Adapter(employee_array);
        rv.setAdapter(adapter);
    }
}
