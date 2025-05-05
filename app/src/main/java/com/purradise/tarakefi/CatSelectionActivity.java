package com.purradise.tarakefi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CatSelectionActivity extends AppCompatActivity {

    private TextView cat1Name, cat1Job, cat1Shift, cat1Bio;
    private ImageView cat1Image;
    private Button cat1Button;

    private TextView cat2Name, cat2Job, cat2Shift, cat2Bio;
    private ImageView cat2Image;
    private Button cat2Button;

    private TextView cat3Name, cat3Job, cat3Shift, cat3Bio;
    private ImageView cat3Image;
    private Button cat3Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_selection); // Update with correct layout file

        // Initialize views for cat 1
        cat1Name = findViewById(R.id.cat1Name);
        cat1Job = findViewById(R.id.cat1Job);
        cat1Shift = findViewById(R.id.cat1Shift);
        cat1Bio = findViewById(R.id.cat1Bio);
        cat1Image = findViewById(R.id.cat1Image);
        cat1Button = findViewById(R.id.cat1Button);

        // Initialize views for cat 2
        cat2Name = findViewById(R.id.cat2Name);
        cat2Job = findViewById(R.id.cat2Job);
        cat2Shift = findViewById(R.id.cat2Shift);
        cat2Bio = findViewById(R.id.cat2Bio);
        cat2Image = findViewById(R.id.cat2Image);
        cat2Button = findViewById(R.id.cat2Button);

        // Initialize views for cat 3
        cat3Name = findViewById(R.id.cat3Name);
        cat3Job = findViewById(R.id.cat3Job);
        cat3Shift = findViewById(R.id.cat3Shift);
        cat3Bio = findViewById(R.id.cat3Bio);
        cat3Image = findViewById(R.id.cat3Image);
        cat3Button = findViewById(R.id.cat3Button);

        // Handle button click for cat 1
        cat1Button.setOnClickListener(v -> {
            saveCatDetailsToSharedPreferences("Kefi", "Shop Owner", "6am - 2pm", "Loves latte art and naps in the sun.", R.drawable.kefi_pic);
            navigateToReceiptActivity();
        });

        // Handle button click for cat 2
        cat2Button.setOnClickListener(v -> {
            saveCatDetailsToSharedPreferences("Loki", "Senior Barista", "2pm - 10pm", "Can balance a cup on his tail.", R.drawable.loki_ni_alaiza_pic);
            navigateToReceiptActivity();
        });

        // Handle button click for cat 3
        cat3Button.setOnClickListener(v -> {
            saveCatDetailsToSharedPreferences("Loki", "Trainee", "10am - 6pm", "New and eager to impress customers.", R.drawable.loki_ni_yasmien_pic);
            navigateToReceiptActivity();
        });
    }

    // Method to save selected cat's details to SharedPreferences
    private void saveCatDetailsToSharedPreferences(String name, String position, String shift, String bio, int imageRes) {
        SharedPreferences sharedPreferences = getSharedPreferences("OrderData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("selectedCatName", name);
        editor.putString("selectedCatPosition", position);
        editor.putString("selectedCatShift", shift);
        editor.putString("selectedCatBio", bio);
        editor.putInt("selectedCatImage", imageRes);

        editor.apply();
    }

    // Method to navigate to the Receipt Activity
    private void navigateToReceiptActivity() {
        Intent intent = new Intent(CatSelectionActivity.this, ReceiptActivity.class);
        startActivity(intent);
    }
}
