package com.purradise.tarakefi;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.Map;

public class ReceiptActivity extends AppCompatActivity {

    private LinearLayout orderContainer;
    private TextView totalTextView;
    private TextView catNameView, catPositionView, catShiftView, catBioView;
    private ImageView catImageView;
    private SharedPreferences cartPrefs, catPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        orderContainer = findViewById(R.id.orderContainer);
        totalTextView = findViewById(R.id.totalPrice);
        catNameView = findViewById(R.id.catName);
        catPositionView = findViewById(R.id.catPosition);
        catShiftView = findViewById(R.id.catShift);
        catBioView = findViewById(R.id.catBio);
        catImageView = findViewById(R.id.catImage);

        cartPrefs = getSharedPreferences("MyCart", MODE_PRIVATE);
        catPrefs = getSharedPreferences("OrderData", MODE_PRIVATE);

        loadOrder();
        loadCatInfo();
    }

    private void loadOrder() {
        String cartData = cartPrefs.getString("cart", "");
        String[] items = cartData.split(",");
        Map<String, Integer> itemCounts = new HashMap<>();

        for (String item : items) {
            if (!item.isEmpty()) {
                itemCounts.put(item, itemCounts.getOrDefault(item, 0) + 1);
            }
        }

        double subtotal = 0.0;

        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            double price = getPriceForItem(itemName);
            subtotal += price * quantity;

            TextView itemText = new TextView(this);
            itemText.setText("• " + itemName + " x" + quantity);
            itemText.setTextSize(18);
            itemText.setPadding(0, 12, 0, 12);
            orderContainer.addView(itemText);
        }

        double vat = subtotal * 0.12;
        double total = subtotal + vat;

        totalTextView.setText(
                "Subtotal: ₱" + String.format("%.2f", subtotal) + "\n" +
                        "VAT (12%): ₱" + String.format("%.2f", vat) + "\n" +
                        "Total: ₱" + String.format("%.2f", total)
        );
    }

    private double getPriceForItem(String itemName) {
        switch (itemName) {
            case "Espresso": return 80.00;
            case "Americano": return 80.00;
            case "Mocha": return 100.00;
            case "Macchiato": return 90.00;
            case "Latte": return 90.00;
            case "Cappuccino": return 95.00;
            case "Tea": return 75.00;
            case "Hot Chocolate": return 85.00;
            case "Sandwich": return 120.00;
            case "Muffins": return 50.00;
            case "Croissant": return 65.00;
            case "Cookies": return 45.00;
            case "Cake": return 150.00;
            default: return 0.00;
        }
    }

    private void loadCatInfo() {
        String name = catPrefs.getString("selectedCatName", "Unknown");
        String position = catPrefs.getString("selectedCatPosition", "Unknown");
        String shift = catPrefs.getString("selectedCatShift", "Unknown");
        String bio = catPrefs.getString("selectedCatBio", "No bio available.");
        int imageRes = catPrefs.getInt("selectedCatImage", R.drawable.ic_launcher_foreground);

        catNameView.setText(name);
        catPositionView.setText(position);
        catShiftView.setText("Shift: " + shift);
        catBioView.setText(bio);
        catImageView.setImageResource(imageRes);
    }

}
