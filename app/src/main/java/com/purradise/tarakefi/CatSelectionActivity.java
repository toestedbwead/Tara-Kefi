package com.purradise.tarakefi;

import android.annotation.SuppressLint;
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

    private TextView cat4Name, cat4Job, cat4Shift, cat4Bio;
    private ImageView cat4Image;
    private Button cat4Button;

    private TextView cat5Name, cat5Job, cat5Shift, cat5Bio;
    private ImageView cat5Image;
    private Button cat5Button;

    private TextView cat6Name, cat6Job, cat6Shift, cat6Bio;
    private ImageView cat6Image;
    private Button cat6Button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_selection); // Update with correct layout file

        // Kefi
        cat1Name = findViewById(R.id.cat1Name);
        cat1Job = findViewById(R.id.cat1Job);
        cat1Shift = findViewById(R.id.cat1Shift);
        cat1Bio = findViewById(R.id.cat1Bio);
        cat1Image = findViewById(R.id.cat1Image);
        cat1Button = findViewById(R.id.cat1Button);

        // Loki
        cat2Name = findViewById(R.id.cat2Name);
        cat2Job = findViewById(R.id.cat2Job);
        cat2Shift = findViewById(R.id.cat2Shift);
        cat2Bio = findViewById(R.id.cat2Bio);
        cat2Image = findViewById(R.id.cat2Image);
        cat2Button = findViewById(R.id.cat2Button);

        // Loki
        cat3Name = findViewById(R.id.cat3Name);
        cat3Job = findViewById(R.id.cat3Job);
        cat3Shift = findViewById(R.id.cat3Shift);
        cat3Bio = findViewById(R.id.cat3Bio);
        cat3Image = findViewById(R.id.cat3Image);
        cat3Button = findViewById(R.id.cat3Button);

        // Lobi
        cat4Name = findViewById(R.id.cat4Name);
        cat4Job = findViewById(R.id.cat4Job);
        cat4Shift = findViewById(R.id.cat4Shift);
        cat4Bio = findViewById(R.id.cat4Bio);
        cat4Image = findViewById(R.id.cat4Image);
        cat4Button = findViewById(R.id.cat4Button);

        // Nabi
        cat5Name = findViewById(R.id.cat5Name);
        cat5Job = findViewById(R.id.cat5Job);
        cat5Shift = findViewById(R.id.cat5Shift);
        cat5Bio = findViewById(R.id.cat5Bio);
        cat5Image = findViewById(R.id.cat5Image);
        cat5Button = findViewById(R.id.cat5Button);

        // Snow
        cat6Name = findViewById(R.id.cat6Name);
        cat6Job = findViewById(R.id.cat6Job);
        cat6Shift = findViewById(R.id.cat6Shift);
        cat6Bio = findViewById(R.id.cat6Bio);
        cat6Image = findViewById(R.id.cat6Image);
        cat6Button = findViewById(R.id.cat6Button);

        cat1Button.setOnClickListener(v -> {
            saveCatDetailsToSharedPreferences("Kefi", "CEO (Cat Executive Officer)", "On-call. Lagi.", "Hindi siya nagtatrabaho, pero lahat sumusunod. Mahilig tumambay sa ibabaw ng resibo printer. Paborito niyang gawin: tumitig ng matagal, parang may alam siyang hindi mo alam. Nonchalant final boss", R.drawable.kefi_pic);
            navigateToReceiptActivity();
        });

        cat2Button.setOnClickListener(v -> {
            saveCatDetailsToSharedPreferences("Loki ni Alaiza", "Paw-rista", "10am - 6pm", "Kape't tulog ang routine. Madalas mahanap sa counter, naka-tulala o nakatingin sa customers like may deep thoughts. Certified whipped cream enjoyer.", R.drawable.loki_ni_alaiza_pic);
            navigateToReceiptActivity();
        });

        cat3Button.setOnClickListener(v -> {
            saveCatDetailsToSharedPreferences("Loki ni Yasmien", "Cashpurr", "8am - 4pm", "Mabilis magbilang ng barya, pero mabagal gumalaw. Laging may suot na bell, pero never maririnig kasi tulog siya half the time.", R.drawable.loki_ni_yasmien_pic);
            navigateToReceiptActivity();
        });

        cat4Button.setOnClickListener(v -> {
            saveCatDetailsToSharedPreferences("Lobi", "Espawso Specialist", "12nn - 8pm", "Mahilig sa dark roast at dark corners. Madalas mong maririnig na nagreklamo pero lalapit din ‘pag may treats.", R.drawable.lobi_pic);
            navigateToReceiptActivity();
        });

        cat5Button.setOnClickListener(v -> {
            saveCatDetailsToSharedPreferences("Nabi", "Head of Cuddles", "Flexible hours (depende sa mood)", "Pwedeng lap cat, pwedeng diva. Magpapahimas siya—pero dapat siya ang lalapit, hindi ikaw.", R.drawable.nabi_pic);
            navigateToReceiptActivity();
        });

        cat6Button.setOnClickListener(v -> {
            saveCatDetailsToSharedPreferences("Snow", "Security Guard (Honorary)", "6pm - 2am", "Tahimik pero may presence. Nagbabantay sa may pinto, pero ‘pag may pumasok na dog... wala na, tago agad.", R.drawable.snow_pic);
            navigateToReceiptActivity();
        });
    }

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

    private void navigateToReceiptActivity() {
        Intent intent = new Intent(CatSelectionActivity.this, ReceiptActivity.class);
        startActivity(intent);
    }
}
