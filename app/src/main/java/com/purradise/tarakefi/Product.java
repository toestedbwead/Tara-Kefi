package com.purradise.tarakefi;

import org.json.JSONException;
import org.json.JSONObject;

public class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("quantity", quantity);
        return json;
    }

    public static Product fromJSON(JSONObject json) throws JSONException {
        return new Product(
                json.getString("name"),
                json.getDouble("price"),
                json.getInt("quantity")
        );
    }
}
