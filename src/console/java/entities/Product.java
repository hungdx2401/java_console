/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.entities;

import console.java.models.GlobalConfig;
import console.java.models.Table;

/**
 *
 * @author Thang
 */
public class Product implements ToArray, Table{
    private String barCode;
    private String name;
    private String description;
    private int quantity;
    private int price;
    private int discount;
    private int category_id;

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryId() {
        return category_id;
    }

    public void setCategoryId(int categoryId) {
        this.category_id = categoryId;
    }

     @Override
     public String toString() {
	  return "Product{" + "barCode=" + barCode + ", name=" + name + ", description=" + description + ", quantity=" + quantity + ", price=" + price + ", discount=" + discount + ", categoryId=" + category_id + '}';
     }
    

     @Override
     public String[] toArray() {
	  return new String[]{barCode, name, description, Integer.toString(quantity), Integer.toString(price), Integer.toString(discount), Integer.toString(category_id)};
     }

     @Override
     public String getTable() {
	  return GlobalConfig.getPRODUCTS_TABLE();
     }

     @Override
     public String getUnique() {
	  return "barcode";
     }
}
