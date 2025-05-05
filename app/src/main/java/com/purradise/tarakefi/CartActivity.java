package com.purradise.tarakefi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    private LinearLayout cartContainer;
    private TextView totalPriceTextView;
    private SharedPreferences sharedPreferences;
    private double totalAmount = 0.00; // To hold the total price

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartContainer = findViewById(R.id.cartContainer);
        totalPriceTextView = findViewById(R.id.totalPrice);  // TextView to show total price
        sharedPreferences = getSharedPreferences("MyCart", MODE_PRIVATE);

        findViewById(R.id.resetButton).setOnClickListener(v -> resetCart());
        findViewById(R.id.proceedButton).setOnClickListener(v -> proceedToCheckout());

        loadCart();
    }

    private void loadCart() {
        String cartData = sharedPreferences.getString("cart", "");
        String[] cartItems = cartData.split(",");

        cartContainer.removeAllViews(); // Remove all previous views
        totalAmount = 0.00; // Reset total before recalculating

        for (String itemName : cartItems) {
            if (!itemName.isEmpty()) {
                addCartItem(itemName);
            }
        }
        updateTotalPrice();  // Update the total price at the end
    }

    private void addCartItem(String itemName) {
        TextView itemTextView = new TextView(this);
        itemTextView.setText("• " + itemName);
        itemTextView.setTextSize(18);
        itemTextView.setPadding(0, 12, 0, 12);
        cartContainer.addView(itemTextView);

        // Update total (assuming each item costs 80.00 for now, you can change this logic)
        totalAmount += 80.00;  // Assuming each product has a price of 80.00 (you can adjust based on your product data)
    }

    private void updateTotalPrice() {
        // Update the total price displayed
        totalPriceTextView.setText("Total: ₱" + String.format("%.2f", totalAmount));
    }

    private void resetCart() {
        // Clear cart data and reset total
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cart", "");
        editor.apply();
        loadCart();  // Refresh the cart
    }

    private void proceedToCheckout() {
        // Implement checkout process here
        Toast.makeText(this, "Proceeding to checkout...", Toast.LENGTH_SHORT).show();
    }
}
