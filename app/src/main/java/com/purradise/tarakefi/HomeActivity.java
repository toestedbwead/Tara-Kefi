package com.purradise.tarakefi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences("MyCart", MODE_PRIVATE);

        Button viewCartButton = findViewById(R.id.cartButton);
        viewCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });

        Button addEspresso = findViewById(R.id.addEspressoButton);
        addEspresso.setOnClickListener(v -> {

            Product espresso = new Product("Espresso", 90.00, 1);
            addProductToCart(espresso);

            Toast.makeText(this, "Espresso added to cart!", Toast.LENGTH_SHORT).show();
        });

        Button addAmericano = findViewById(R.id.addAmericanoButton);
        addAmericano.setOnClickListener(v -> {

            Product americano = new Product("Americano", 80.00, 1);
            addProductToCart(americano);

            Toast.makeText(this, "Americano added to cart!", Toast.LENGTH_SHORT).show();
        });

        Button addMocha = findViewById(R.id.addMochaButton);
        addMocha.setOnClickListener(v -> {

            Product mocha = new Product("Mocha", 100.00, 1);
            addProductToCart(mocha);

            Toast.makeText(this, "Mocha added to cart!", Toast.LENGTH_SHORT).show();
        });

        Button addMacchiato = findViewById(R.id.addMacchiatoButton);
        addMacchiato.setOnClickListener(v -> {

            Product macchiato = new Product("Macchiato", 90.00, 1);
            addProductToCart(macchiato);

            Toast.makeText(this, "Macchiato added to cart!", Toast.LENGTH_SHORT).show();
        });

        Button addLatte = findViewById(R.id.addLatteButton);
        addLatte.setOnClickListener(v -> {

            Product latte = new Product("Latte", 90.00, 1);
            addProductToCart(latte);

            Toast.makeText(this, "Latte added to cart!", Toast.LENGTH_SHORT).show();
        });

        Button addCappuccino = findViewById(R.id.addCappuccinoButton);
        addCappuccino.setOnClickListener(v -> {

            Product cappuccino = new Product("Cappuccino", 95.00, 1);
            addProductToCart(cappuccino);

            Toast.makeText(this, "Cappuccino added to cart!", Toast.LENGTH_SHORT).show();
        });

        Button addTea = findViewById(R.id.addTeaButton);
        addTea.setOnClickListener(v -> {

            Product tea = new Product("Tea", 75.00, 1);
            addProductToCart(tea);

            Toast.makeText(this, "Tea added to cart!", Toast.LENGTH_SHORT).show();
        });

        Button addHotChocolate = findViewById(R.id.addHotChocolateButton);
        addHotChocolate.setOnClickListener(v -> {

            Product hot_chocolate = new Product("Hot Chocolate", 85.00, 1);
            addProductToCart(hot_chocolate);

            Toast.makeText(this, "Hot Chocolate added to cart!", Toast.LENGTH_SHORT).show();
        });

        Button addSandwich = findViewById(R.id.addSandwichButton);
        addSandwich.setOnClickListener(v -> {

            Product sandwich = new Product("Sandwich", 120.00, 1);
            addProductToCart(sandwich);

            Toast.makeText(this, "Sandwich added to cart!", Toast.LENGTH_SHORT).show();
        });

        Button addMuffins = findViewById(R.id.addMuffinsButton);
        addMuffins.setOnClickListener(v -> {

            Product muffins = new Product("Muffins", 50.00, 1);
            addProductToCart(muffins);

            Toast.makeText(this, "Muffins added to cart!", Toast.LENGTH_SHORT).show();
        });

        Button addCroissant = findViewById(R.id.addCroissantButton);
        addCroissant.setOnClickListener(v -> {

            Product croissant = new Product("Croissant", 65.00, 1);
            addProductToCart(croissant);

            Toast.makeText(this, "Croissant added to cart!", Toast.LENGTH_SHORT).show();
        });

        Button addCookies = findViewById(R.id.addCookiesButton);
        addCookies.setOnClickListener(v -> {

            Product cookies = new Product("Cookies", 45.00, 1);
            addProductToCart(cookies);

            Toast.makeText(this, "Cookies added to cart!", Toast.LENGTH_SHORT).show();
        });

        Button addCake = findViewById(R.id.addCakeButton);
        addCake.setOnClickListener(v -> {

            Product cake = new Product("Cake", 150.00, 1);
            addProductToCart(cake);

            Toast.makeText(this, "Cake added to cart!", Toast.LENGTH_SHORT).show();
        });
    }

    private void addProductToCart(Product product) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String cartData = sharedPreferences.getString("cart", "");


        cartData += product.getName() + ",";

        Log.d("HomeActivity", "Cart Data: " + cartData);

        editor.putString("cart", cartData);
        editor.apply();
    }
}
