/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.controller;

import console.java.entity.Product;
import console.java.model.ProductModel;
import console.java.utility.ScannerUtility;

/**
 *
 * @author Asus
 */
public class Controller {

    public static void processInsert() {
        System.out.println("add a new product");
        System.out.println("enter Barcode values");
        String barCode = ScannerUtility.getString();
        System.out.println("enter name values");
        String name = ScannerUtility.getString();
        System.out.println("enter description values");
        String description = ScannerUtility.getString();
        System.out.println("enter quantity values");
        int quantity = ScannerUtility.getInt();
        System.out.println("enter price values");
        float price = ScannerUtility.getFloat();
        System.out.println("enter categoryId values");
        int categoryId = ScannerUtility.getInt();
        Product product = new Product();
        product.setBarCode(barCode);
        product.setName(name);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        ProductModel.insert(product);
    }
}
