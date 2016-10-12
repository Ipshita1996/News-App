package com.android.ipshita.inventoryapplication;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ipshita.inventoryapplication.data.ItemContract;
import com.android.ipshita.inventoryapplication.data.ItemHelper;

/**
 * Created by Ipshita on 11-10-2016.
 */
public class product extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int ITEM_LOADER = 1;
    ItemHelper mDbHelper;
    private Uri currentUri;
    String currentName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);

        Intent intent = getIntent();
        currentUri = intent.getData();
        mDbHelper = new ItemHelper(this);
        getLoaderManager().initLoader(ITEM_LOADER, null, this);


        Button orderButton = (Button) findViewById(R.id.orderNow);
        final TextView newOrderQuantity = (TextView) findViewById(R.id.order_quantity);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newOrderQuantity.getText().toString().trim().length() != 0) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_SUBJECT,
                            "Order Information");
                    intent.putExtra(Intent.EXTRA_TEXT, newOrderQuantity.getText().toString());
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Order field cannot be blank", Toast.LENGTH_SHORT).show();
                }
            }
        });
        getLoaderManager().initLoader(ITEM_LOADER, null, this);
    }

    public void buttonOnClick(View view) {
        TextView textQuantity = (TextView) findViewById(R.id.order_quantity);
        String quantityString = textQuantity.getText().toString();
        int quantityInt = Integer.parseInt(quantityString);
        int id = view.getId();
        switch (id) {
            case R.id.increment_quantity_button:
                quantityInt++;
                break;
            case R.id.decrement_quantity_button:
                if (quantityInt != 0) {
                    quantityInt--;
                } else
                    Toast.makeText(this, "Quantity can't be negative", Toast.LENGTH_SHORT).show();
                break;
        }

        textQuantity.setText(Integer.toString(quantityInt));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {ItemContract.ItemEntry._ID,
                ItemContract.ItemEntry.COLUMN_ITEM_NAME,
                ItemContract.ItemEntry.COLUMN_ITEM_PRICE,
                ItemContract.ItemEntry.COLUMN_ITEM_QUANTITY,
                ItemContract.ItemEntry.COLUMN_ITEM_SOLD,};
        return new CursorLoader(this, currentUri,
                projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if(!cursor.isClosed()){
            TextView productName=(TextView)findViewById(R.id.productName);
            TextView productPrice=(TextView)findViewById(R.id.productPrice1);
            TextView productSold=(TextView)findViewById(R.id.textViewSold);
            TextView productQty=(TextView)findViewById(R.id.textViewqty);
            int nameColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_ITEM_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_ITEM_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_ITEM_QUANTITY);
            int soldColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_ITEM_SOLD);
            cursor.moveToNext();
            currentName = cursor.getString(nameColumnIndex);
            int currentPrice = cursor.getInt(priceColumnIndex);
            int currentQuantity = cursor.getInt(quantityColumnIndex);
            int currentSold = cursor.getInt(soldColumnIndex);

            productName.setText(currentName);
            productSold.setText(String.valueOf(currentSold));
            productPrice.setText(String.valueOf(currentPrice));
            productQty.setText(String.valueOf(currentQuantity));
            cursor.close();
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        loader.reset();

    }
}
