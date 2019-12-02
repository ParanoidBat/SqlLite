package com.example.android.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NAME= "Data";

    //tables
    private static final String TABLE_EMPLOYEE= "Employee";

    //columns
    private static final String COLUMN_ID= "id";
    private static final String COLUMN_NAME= "name";
    private static final String COLUMN_PAISA= "paisa";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //create the table/s
        String create_table= "CREATE TABLE " + TABLE_EMPLOYEE + " (" + COLUMN_ID +  " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT, " + COLUMN_PAISA + " TEXT" + " )";
        sqLiteDatabase.execSQL(create_table); //execute sql commands that does not return data
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop older table if existed. Create new tables with updated values
        sqLiteDatabase.execSQL(new StringBuilder().append("DROP TABLE IF EXISTS ").append(TABLE_EMPLOYEE).toString());

        onCreate(sqLiteDatabase);
    }

    //add employee in database
    public void addEmployee(Employee employee){
        SQLiteDatabase db= this.getWritableDatabase(); //better to read/write in background thread, cuz its a long process

        ContentValues values= new ContentValues(); //stores in key-value pairs. Used for mapping
        values.put(COLUMN_NAME, employee.getName());
        values.put(COLUMN_PAISA, employee.getPaisa());

        db.insert(TABLE_EMPLOYEE, null, values); //put data in database. will resolve column names and values itself and put accordingly
        db.close();
    }

    public Employee getEmployee(int id){ //get employee with this id (Single row returned)
        SQLiteDatabase db= this.getReadableDatabase();

        //which columns to return
        String [] projection={COLUMN_ID, COLUMN_NAME, COLUMN_PAISA};

        //where clause. Created and passed as two separate params to avoid sql injection
        String selection= COLUMN_ID + " =? "; // '=?' -> where
        String []args= {String.valueOf(id)};

        Cursor cursor= db.query(TABLE_EMPLOYEE, projection, selection, args, null, null, null);

        cursor.moveToNext(); /* by default cursor position is at -1, calling it moves it to the first entry. This method also checks if all
        entries have been iterated, if placed as argument to while loop */

        //get value from cursor position. need to mention the index of column, do that by calling get column index method
        Employee employee= new Employee(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID))),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)), cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PAISA)));

        cursor.close();

        return employee;
    }

    //get all employees
    public List<Employee> getData(){
        List<Employee> employees = new ArrayList<>();

        SQLiteDatabase db= this.getReadableDatabase();  //not sure here, re-check

        String query= new StringBuilder().append("SELECT * FROM ").append(TABLE_EMPLOYEE).toString();

        Cursor cursor= db.rawQuery(query, null); //raw query can only be used for read operations

        while(cursor.moveToNext()){ //iterate unless cursor is null.
            Employee e= new Employee(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID))),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)), cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PAISA)));

            employees.add(e);
        }

        cursor.close();
        return employees;
    }

    //update data of employee who has this paisa
    public int updateWithPaisa(Employee employee, String paisa){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values= new ContentValues();
        //values.put(COLUMN_ID, employee.getId());
        values.put(COLUMN_NAME, employee.getName());
        values.put(COLUMN_PAISA, employee.getPaisa());

        String selection = COLUMN_PAISA + " =? ";
        String []args= {paisa};

        int n= db.update(TABLE_EMPLOYEE, values, selection, args); //returns number of rows affected by update

        db.close();
        return n;
    }

    //delete entry who has this paisa
    public int deleteWithPaisa(String paisa){
        SQLiteDatabase db = this.getWritableDatabase();

        int n = db.delete(TABLE_EMPLOYEE, COLUMN_PAISA + " LIKE ?", new String[]{paisa}); //instead of 'Like ?' , '=?' can also be used

        db.close();
        return n;
    }
}


