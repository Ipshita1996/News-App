package com.android.ipshita.habittrackerapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.ipshita.habittrackerapp.data.HabitContract.HabitEntry;
import com.android.ipshita.habittrackerapp.data.HabitDbHelper;

public class CatalogActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);


        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new HabitDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        HabitDbHelper mDbHelper = new HabitDbHelper(this);

        // Create and/or open a database to read from it

        String[] projection = {HabitEntry._ID, HabitEntry.COLUMN_HABIT_NAME, HabitEntry.COLUMN_HABIT_PRIORITY,};

        readhabit(projection);

    }
    private void inserthabit(){
        // Gets the data repository in write mode
        SQLiteDatabase db =mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME ,"Add new Habit");
        values.put(HabitEntry.COLUMN_HABIT_PRIORITY,1);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        Log.v("CatalogActivity","this inserted"+newRowId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                inserthabit();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Cursor readhabit(String[] projection){

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor c = db.query(HabitEntry.TABLE_NAME, projection, null, null,
                null, null, null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
            displayView.setText("Number of rows in habits database table: " + c.getCount());

            displayView.append("\n \n" + HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitEntry.COLUMN_HABIT_PRIORITY + "\n");

            int idColumnIndex = c.getColumnIndex(HabitEntry._ID);
            int idNameIndex = c.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int idPriorityIndex = c.getColumnIndex(HabitEntry.COLUMN_HABIT_PRIORITY);
            while (c.moveToNext()) {
                int currentID = c.getInt(idColumnIndex);
                String currentName = c.getString(idNameIndex);
                int currentPriority = c.getInt(idPriorityIndex);

                displayView.append("\n" + currentID + " - " + currentName + " - " + currentPriority + "\n");

            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            c.close();
        }

        return c;
    }
}