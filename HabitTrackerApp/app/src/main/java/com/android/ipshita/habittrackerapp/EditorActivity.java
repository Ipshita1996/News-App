package com.android.ipshita.habittrackerapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.ipshita.habittrackerapp.data.HabitContract.HabitEntry;
import com.android.ipshita.habittrackerapp.data.HabitDbHelper;

public class EditorActivity extends AppCompatActivity {

    /**
     * EditText field to enter the habit's name
     */
    private EditText mNameEditText;

    /**
     * EditText field to enter the habit's priority
     */
    private EditText mPriorityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);


        mNameEditText = (EditText) findViewById(R.id.edit_habit_name);
        mPriorityEditText = (EditText) findViewById(R.id.edit_habit_priority);
    }

    private void insertHabit() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        String priorityString = mPriorityEditText.getText().toString().trim();
        int priority = Integer.parseInt(priorityString);

        // Create database helper
        HabitDbHelper mDbHelper = new HabitDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, nameString);
        values.put(HabitEntry.COLUMN_HABIT_PRIORITY, priority);

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database
                insertHabit();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}