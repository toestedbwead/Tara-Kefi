package com.purradise.tarakefi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CatSelectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_selection);

        findViewById(R.id.cat1Button).setOnClickListener(view -> selectCat("Kefi"));
        findViewById(R.id.cat2Button).setOnClickListener(view -> selectCat("Loki"));
        findViewById(R.id.cat3Button).setOnClickListener(view -> selectCat("Loki"));

        findViewById(R.id.checkoutButton).setOnClickListener(v -> {
            // TODO: Go to receipt screen
        });
    }

    private void selectCat(String name) {
        Intent result = new Intent();
        result.putExtra("selectedCat", name);
        setResult(RESULT_OK, result);
        finish();
    }
}
