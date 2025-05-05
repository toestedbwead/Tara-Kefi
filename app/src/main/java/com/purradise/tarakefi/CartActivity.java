package com.purradise.tarakefi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    private LinearLayout cartContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartContainer = findViewById(R.id.cartContainer);

        SharedPreferences prefs = getSharedPreferences("MyCart", MODE_PRIVATE);
        String cartData = prefs.getString("cart", "");

        if (!cartData.isEmpty()) {
            String[] items = cartData.split(",");

            for (String item : items) {
                if (!item.isEmpty()) {
                    TextView itemText = new TextView(this);
                    itemText.setText("• " + item);
                    itemText.setTextSize(18);
                    itemText.setPadding(0, 12, 0, 12);
                    cartContainer.addView(itemText);
                }
            }
        } else {
            TextView empty = new TextView(this);
            empty.setText("Your cart is empty.");
            empty.setTextSize(18);
            cartContainer.addView(empty);
        }
    }
}
