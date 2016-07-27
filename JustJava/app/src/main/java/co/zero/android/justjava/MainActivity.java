package co.zero.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private int cupsAmount = 0;
    private int currentTurn = 0;
    private int cupPrice = 5;
    private boolean whippedCream;


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
        displayOrderInfo();
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
        String price = NumberFormat.getCurrencyInstance().format(getTotalPrice());
        quantityTextView.setText(price);
    }

    private int getTotalPrice(){
        return cupsAmount * cupPrice;
    }

    public void selectWhippedCream(View view){
        CheckBox checkBox = (CheckBox) view;
        whippedCream = checkBox.isChecked();
    }

    private void displayOrderInfo(){
        TextView turnView = (TextView) findViewById(R.id.order_text_view);

        StringBuffer orderInfo = new StringBuffer();
        orderInfo.append("===============================");
        orderInfo.append("\nORDER SUMMARY");
        orderInfo.append("\n===============================");
        orderInfo.append("\nName: Hernán Tenjo");
        orderInfo.append("\nQuantity: " + this.cupsAmount);
        orderInfo.append("\nWhit Whipped Cream? : " + this.whippedCream);
        orderInfo.append("\nTotal: " + NumberFormat.getCurrencyInstance().format(getTotalPrice()));
        orderInfo.append("\nThank you!!!");
        orderInfo.append("\nYour turn is: " + currentTurn);
        orderInfo.append("\n=============================");

        turnView.setText(orderInfo.toString());
    }
}
