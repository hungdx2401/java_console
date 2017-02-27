/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.controllers;

import console.java.entity.Product;
import console.java.model.DAO;
import console.java.model.ProductModels;
import console.java.utilities.ScannerUtilities;
import console.java.views.ProductViews;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static boolean processUpdate() {
        try {
                int count = 0;
                System.out.println("Nhap ma so : ");
                String barCode = ScannerUtilities.getString();
                Statement stt = DAO.getConnection().createStatement();

                String sql = "SELECT * FROM products WHERE barcode = '" + barCode + "'";
                ResultSet rs = stt.executeQuery(sql);

                while (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String quantity = rs.getString("quantity");
                    String price = rs.getString("price");
                    String categoryId = rs.getString("categoryId");
                    System.out.printf("%-10s %s\n", "Ten         :", name);
                    System.out.printf("%-10s %s\n", "Mo ta       :", description);
                    System.out.printf("%-10s %s\n", "So luong    :", quantity);
                    System.out.printf("%-10s %s\n", "Gia         :", price);
                    System.out.printf("%-10s %s\n", "Chung loai  :", categoryId);
                    ++count;
                }
                
                if (count == 0) {
                    System.err.println("Khong co san pham !!!");
                    return false;
                }

                System.out.println("Chu y : Neu ban khong muon sua ,hay de trong va tiep tuc !!!");
                System.out.println("Nhap ten moi           :");
                String newName = ScannerUtilities.getString();
                System.out.println("Nhap mo ta moi         :");
                String newDescription = ScannerUtilities.getString();
                System.out.println("Nhap so luong moi      :");
                int newQuantity = ScannerUtilities.getInt();
                System.out.println("Nhap gia moi           :");
                float newPrice = ScannerUtilities.getFloat();
                System.out.println("Nhap ma chung loai moi :");
                int newCategoryId = ScannerUtilities.getInt();

                Product product = new Product();
                product.setName(newName);
                product.setDescription(newDescription);
                product.setQuantity(newQuantity);
                product.setPrice(newPrice);
                product.setCategoryId(newCategoryId);
                //Call update method in productModel class
        } catch (SQLException e) {
            System.err.println("Khong the update !!!");
        }
        return true;
    }
}
