/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.entity;

/**
 *
 * @author Thang
 */
public class Product {
    private static String barCode;
    private static String name;
    private static String description;
    private static int quantity;
    private static float price;
    private static int categoryId;

    public static String getBarCode() {
        return barCode;
    }

    public static void setBarCode(String barCode) {
        Product.barCode = barCode;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Product.name = name;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Product.description = description;
    }

    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        Product.quantity = quantity;
    }

    public static float getPrice() {
        return price;
    }

    public static void setPrice(float price) {
        Product.price = price;
    }

    public static int getCategoryId() {
        return categoryId;
    }

    public static void setCategoryId(int categoryId) {
        Product.categoryId = categoryId;
    }
    
    
}
