/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.controllers;

import console.java.entity.Product;
import console.java.model.ProductModels;
import console.java.utilities.ScannerUtilities;
import console.java.views.ProductViews;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class này chứa các hàm xử lý sản phẩm
 *
 * @author DongHo
 */
public class ProductControllers {

    public static void searchProduct() {

        ResultSet rs;
        Product product = new Product();
        int count = 0;
        boolean continueBoolean = true;

        while (continueBoolean) {
            ProductViews.searchOption();
            int option = ScannerUtilities.getInt();
            System.out.print("Nhap vao tu khoa muon tim kiem: ");
            String keywordName = ScannerUtilities.getString(3);
            rs = ProductModels.searchProduct(keywordName, option);
            try {
                while (rs.next()) {
                    product.setBarCode(rs.getString("barCode"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setPrice(rs.getFloat("price"));
                    product.setDiscount(rs.getFloat("discount"));
                    product.setCategoryId(rs.getInt("category_id"));
                    ProductViews.printProduct(product);
                    ++count;
                }
            } catch (SQLException ex) {
                System.err.println("Co loi xay ra! " + ex);
            }
            if (count == 0) {
                System.out.println("Khong tim thay san pham!");
            } else {
                System.out.println("Tim thay " + count + " san pham.");
            }
            continueBoolean = ProductViews.continueBoolean();
        }
    }

    /*
    hàm insert product lấy giá trị từ bàn phím
     */
    public static void productsInsert() {
        System.out.println("Them san pham moi");
        System.out.println("Nhap Barcode ");
        String barCode = ScannerUtilities.getString();
        System.out.println("Nhap name ");
        String name = ScannerUtilities.getString();
        System.out.println("Nhap description");
        String description = ScannerUtilities.getString();
        System.out.println("Nhap quantity ");
        int quantity = ScannerUtilities.getInt();
        System.out.println("Nhap price ");
        float price = ScannerUtilities.getFloat();
        System.out.println("Nhap categoryId ");
        int categoryId = ScannerUtilities.getInt();
        Product product = new Product();
        product.setBarCode(barCode);
        product.setName(name);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        ProductModels.productsInsert(product);
    }
//hàm controller print all products 
    public static void productsPrintAll() {
        ResultSet rs = ProductModels.productsPrintAll();
        Product product = new Product();
        try {
            while (rs.next()) {
                product.setBarCode(rs.getString("barCode"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getFloat("price"));
                product.setDiscount(rs.getFloat("discount"));
                product.setCategoryId(rs.getInt("category_id"));
                ProductViews.printProduct(product);
            }
        } catch (SQLException ex) {
            System.err.println("Co loi xay ra! " + ex);
        }
    }
}
