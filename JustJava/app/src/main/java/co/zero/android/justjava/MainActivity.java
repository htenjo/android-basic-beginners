package co.zero.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private int cupsAmount = 1;
    private int currentTurn = 0;
    private int cupPrice = 5;
    private boolean whippedCream;
    private boolean chocolate;
    private String customerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayPrice();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        currentTurn++;
        this.customerName = getCustomerName();
        String orderInfo = buildOrderInfo();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        //intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"zerovirus23@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava Order for " + this.customerName);
        intent.putExtra(Intent.EXTRA_TEXT, orderInfo);

        if (intent.resolveActivity(getPackageManager()) != null) {
            Log.i(this.getClass().getSimpleName(), ":::::::::: Intent view resolved");
            startActivity(intent);
        }else{
            Log.i(this.getClass().getSimpleName(), ":::::::::: Intent view NOT resolved");
        }
    }

    public void increaseAmount(View view){
        if(cupsAmount == 100){
            Toast.makeText(this, "You cannot have more than 100 cups of coffee", Toast.LENGTH_SHORT).show();
            return;
        }

        cupsAmount++;
        displayQuantity();
        displayPrice();
    }

    public void decreaseAmount(View view){
        if(cupsAmount == 1){
            Toast.makeText(this, "You cannot have less than 1 cups of coffee", Toast.LENGTH_SHORT).show();
            return;
        }

        cupsAmount--;
        displayQuantity();
        displayPrice();
    }

    private String getCustomerName(){
        EditText customerView = (EditText) findViewById(R.id.customer_name_view);
        return customerView.getText().toString();
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
        int totalCupPrice = cupPrice;

        if(whippedCream){
            totalCupPrice++;
        }

        if(chocolate){
            totalCupPrice += 2;
        }

        return totalCupPrice * cupsAmount;
    }

    public void selectWhippedCream(View view){
        CheckBox checkBox = (CheckBox) view;
        whippedCream = checkBox.isChecked();
        displayPrice();
    }

    public void selectChocolate(View view){
        CheckBox checkBox = (CheckBox) view;
        this.chocolate = checkBox.isChecked();
        displayPrice();
    }

    private String buildOrderInfo(){
        StringBuffer orderInfo = new StringBuffer();
        orderInfo.append("===============================");
        orderInfo.append("\nORDER SUMMARY");
        orderInfo.append("\n===============================");
        orderInfo.append("\nName: " + customerName);
        orderInfo.append("\nQuantity: " + this.cupsAmount);
        orderInfo.append("\nWith Whipped Cream? : " + this.whippedCream);
        orderInfo.append("\nWith Chocolate? : " + this.chocolate);
        orderInfo.append("\nTotal: " + NumberFormat.getCurrencyInstance().format(getTotalPrice()));
        orderInfo.append("\nThank you!!!");
        orderInfo.append("\nYour turn is: " + currentTurn);
        orderInfo.append("\n=============================");
        return orderInfo.toString();
    }
}
