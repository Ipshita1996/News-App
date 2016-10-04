package com.android.ipshita.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity=2;
    int price=0;
    boolean answer_whipped;
    boolean answer_chocolate;
    Editable name;

    public void submitOrder(View view) {
        CheckBox whipped_cream= (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolate= (CheckBox) findViewById(R.id.chocolate_checkbox);
        EditText username= (EditText) findViewById(R.id.username);
        name=username.getText();


        calculatePrice(quantity);
        if(whipped_cream.isChecked()){

            answer_whipped=true;

        }
        else answer_whipped=false;
        if(chocolate.isChecked()){

            answer_chocolate=true;

        }
        else answer_chocolate=false;
        createOrderSummary(price);

    }



    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    int amt=5;
    private int calculatePrice(int quantity) {
        CheckBox whipped_cream= (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolate= (CheckBox) findViewById(R.id.chocolate_checkbox);
        if(whipped_cream.isChecked()){
           amt=6;
        }
        if(chocolate.isChecked()){
           amt=7;
        }
        if(whipped_cream.isChecked() && chocolate.isChecked())
        {
            amt=8;
        }
        price = quantity *amt;
        return price;
    }
    public void increment(View view){
       quantity++;
        if(quantity > 100)
        {
            quantity=100;
            Toast.makeText(this,getString(R.string.extraerror), Toast.LENGTH_SHORT).show();
        }
        display(quantity);

    }
    public void decrement(View view){
        quantity--;
        if(quantity < 1)
        {
            quantity=1;
            Toast.makeText(this,getString(R.string.lesserror), Toast.LENGTH_SHORT).show();
        }
        display(quantity);

    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the order summary on the screen.
     */

    private void createOrderSummary(int price){

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:ipshita1996@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "I need coffee!");
        String body=getString(R.string.name)+name+"\n"+getString(R.string.confirmwc)+answer_whipped+"\n"+getString(R.string.confirmc)+answer_chocolate+"\n"+getString(R.string.quantity_label)+" "+quantity+"\n"+getString(R.string.total)+price+"\n"+getString(R.string.thanks);
        intent.putExtra(Intent.EXTRA_TEXT ,body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }



}
