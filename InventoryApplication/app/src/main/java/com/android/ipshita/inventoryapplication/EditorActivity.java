package com.android.ipshita.inventoryapplication;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.ipshita.inventoryapplication.data.ItemContract.ItemEntry;
import com.android.ipshita.inventoryapplication.data.ItemHelper;

public class EditorActivity extends AppCompatActivity  {

    ItemHelper mDbHelper;

    private Uri mCurrentItemUri;
    private EditText mNameEditText;
    private EditText mPriceEditText;
    private EditText mQuantityEditText;

    private boolean mItemHasChanged = false;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mItemHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);


        Button addnew=(Button)findViewById(R.id.add_button);
        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeData();
            }
        });
    }

    public void writeData() {
        if (isValid()) {
            mDbHelper = new ItemHelper(this);
            EditText textName = (EditText) findViewById(R.id.edit_item_name);
            EditText textPrice = (EditText) findViewById(R.id.edit_item_price);
            EditText textQuantity = (EditText) findViewById(R.id.edit_item_quantity);
            String itemName = textName.getText().toString();
            int itemPrice = Integer.parseInt(textPrice.getText().toString());
            int itemQuantity = Integer.parseInt(textQuantity.getText().toString());
            ContentValues values = new ContentValues();
            values.put(ItemEntry.COLUMN_ITEM_NAME, itemName);
            values.put(ItemEntry.COLUMN_ITEM_PRICE, itemPrice);
            values.put(ItemEntry.COLUMN_ITEM_QUANTITY, itemQuantity);

            Uri newUri = getContentResolver().insert(ItemEntry.CONTENT_URI, values);

            // Show a toast message depending on whether or not the insertion was successful
            if (newUri == null) {
                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, getString(R.string.insert_pet_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.insert_pet_successful),
                        Toast.LENGTH_SHORT).show();
            }
            finish();
        } else {
            Toast.makeText(this, getString(R.string.editor_fields_blank),
                    Toast.LENGTH_SHORT).show();

        }
    }

    public boolean isValid() {
        EditText textName = (EditText) findViewById(R.id.edit_item_name);
        EditText textPrice = (EditText) findViewById(R.id.edit_item_price);
        EditText textQuantity = (EditText) findViewById(R.id.edit_item_quantity);
        return (textName.getText().toString().trim().length() != 0 &&
                textPrice.getText().toString().trim().length() != 0 &&
                textQuantity.getText().toString().trim().length() != 0);
    }
}
