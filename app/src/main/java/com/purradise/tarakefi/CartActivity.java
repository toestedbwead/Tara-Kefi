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

        cartContainer.removeAllViews();
        totalAmount = 0.00;

        // Count how many times each item appears
        java.util.Map<String, Integer> itemCounts = new java.util.HashMap<>();

        for (String item : cartItems) {
            if (!item.isEmpty()) {
                itemCounts.put(item, itemCounts.getOrDefault(item, 0) + 1);
            }
        }

        // Display each item with quantity
        for (java.util.Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            addCartItem(entry.getKey(), entry.getValue());
        }

        updateTotalPrice();
    }

    private void addCartItem(String itemName, int quantity) {
        TextView itemTextView = new TextView(this);
        itemTextView.setText("• " + itemName + " x" + quantity);
        itemTextView.setTextSize(18);
        itemTextView.setPadding(0, 12, 0, 12);
        cartContainer.addView(itemTextView);

        // Simple fixed price per item
        totalAmount += 80.00 * quantity;
    }


    private void updateTotalPrice() {
        double vat = totalAmount * 0.12;
        double grandTotal = totalAmount + vat;

        // Show breakdown
        totalPriceTextView.setText(
                "Subtotal: ₱" + String.format("%.2f", totalAmount) + "\n" +
                        "VAT (12%): ₱" + String.format("%.2f", vat) + "\n" +
                        "Total: ₱" + String.format("%.2f", grandTotal)
        );
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
