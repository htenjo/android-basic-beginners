package co.zero.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private int cupsAmount = 0;
    private int currentTurn = 0;
    private int cupPrice = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        currentTurn++;
        displayTurn();
    }

    public void increaseAmount(View view){
        cupsAmount++;
        displayQuantity();
        displayPrice();
    }

    public void decreaseAmount(View view){
        if(cupsAmount > 0) {
            cupsAmount--;
        }

        displayQuantity();
        displayPrice();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(Integer.toString(cupsAmount));
    }

    private void displayPrice(){
        TextView quantityTextView = (TextView) findViewById(R.id.price_text_view);
        String price = NumberFormat.getCurrencyInstance().format(cupsAmount * cupPrice);
        quantityTextView.setText(price);
    }

    private void displayTurn(){
        TextView turnView = (TextView) findViewById(R.id.turn_text_view);
        turnView.setText("Your turn is: " + currentTurn);
    }
}
