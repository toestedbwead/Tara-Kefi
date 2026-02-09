package com.purradise.tarakefi;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class ReceiptActivity extends AppCompatActivity {

    private LinearLayout orderContainer;
    private TextView totalTextView;
    private TextView catNameView, catPositionView, catShiftView, catBioView;
    private ImageView catImageView;
    private SharedPreferences cartPrefs, catPrefs;
    private Map<String, Integer> itemCounts = new HashMap<>();
    private double subtotal = 0.0, vat = 0.0, total = 0.0;
    private String catName, catPosition, catShift, catBio;

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

        Button generatePdfBtn = findViewById(R.id.btnGeneratePdf);
        generatePdfBtn.setOnClickListener(v -> requestStoragePermission());
        generatePdfBtn.setOnClickListener(v -> generatePdfReceipt());
    }

    private void loadOrder() {
        String cartData = cartPrefs.getString("cart", "");
        String[] items = cartData.split(",");
        itemCounts.clear();
        subtotal = 0.0;

        for (String item : items) {
            if (!item.isEmpty()) {
                itemCounts.put(item, itemCounts.getOrDefault(item, 0) + 1);
            }
        }

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

        vat = subtotal * 0.12;
        total = subtotal + vat;

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
        catName = catPrefs.getString("selectedCatName", "Unknown");
        catPosition = catPrefs.getString("selectedCatPosition", "Unknown");
        catShift = catPrefs.getString("selectedCatShift", "Unknown");
        catBio = catPrefs.getString("selectedCatBio", "No bio available.");
        int imageRes = catPrefs.getInt("selectedCatImage", R.drawable.ic_launcher_foreground);

        catNameView.setText(catName);
        catPositionView.setText(catPosition);
        catShiftView.setText("Shift: " + catShift);
        catBioView.setText(catBio);
        catImageView.setImageResource(imageRes);
    }

    private void requestStoragePermission() {
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        } else {
            generatePdfReceipt(); // if already granted
        }
    }


    private void generatePdfReceipt() {
        PdfDocument pdfDoc = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        PdfDocument.Page page = pdfDoc.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        int y = 25;

        paint.setTextSize(12);
        canvas.drawText("Tara, Kefi Receipt", 80, y, paint);
        y += 20;

        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            canvas.drawText("• " + entry.getKey() + " x" + entry.getValue(), 10, y, paint);
            y += 15;
        }

        y += 10;
        canvas.drawText("Subtotal: ₱" + String.format("%.2f", subtotal), 10, y, paint); y += 15;
        canvas.drawText("VAT (12%): ₱" + String.format("%.2f", vat), 10, y, paint); y += 15;
        canvas.drawText("Total: ₱" + String.format("%.2f", total), 10, y, paint); y += 20;

        canvas.drawText("Server: " + catName, 10, y, paint); y += 15;
        canvas.drawText("Position: " + catPosition, 10, y, paint); y += 15;
        canvas.drawText("Shift: " + catShift, 10, y, paint); y += 15;
        canvas.drawText("Bio: " + catBio, 10, y, paint);

        pdfDoc.finishPage(page);

        ContentValues values = new ContentValues();
        String filename = "TaraKefi_Receipt_" + System.currentTimeMillis() + ".pdf";
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, filename);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf");
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS); // Save to Downloads folder

        ContentResolver resolver = getContentResolver();
        Uri fileUri = resolver.insert(MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL), values);

        try {
            if (fileUri != null) {
                OutputStream out = resolver.openOutputStream(fileUri);
                if (out != null) {
                    pdfDoc.writeTo(out);
                    out.flush();
                    out.close();
                }

                values.clear();
                values.put(MediaStore.MediaColumns.IS_PENDING, 0);
                resolver.update(fileUri, values, null, null);

                Toast.makeText(this, "PDF saved to Downloads", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to create file", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        pdfDoc.close();
    }



}
