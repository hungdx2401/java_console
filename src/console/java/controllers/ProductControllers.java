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
}
