/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.controllers;

import console.java.entities.Product;
import console.java.models.DAO;
import console.java.models.ProducstModel;
import console.java.utilities.ScannerUtilities;
import console.java.views.ProductsViews;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

/**
 * Class này chứa các hàm xử lý sản phẩm
 *
 * @author DongHo
 */
public class ProductsController {

    public static void searchProduct() {

        ResultSet rs;
        Product product = new Product();
        int count = 0;
        boolean continueBoolean = true;

        while (continueBoolean) {
            ProductsViews.searchOption();
            int option = ScannerUtilities.getInt();
            System.out.print("Nhap vao tu khoa muon tim kiem: ");
            String keywordName = ScannerUtilities.getString(3);
            rs = ProducstModel.searchProduct(keywordName, option);
            try {
                while (rs.next()) {
                    product.setBarCode(rs.getString("barCode"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setPrice(rs.getFloat("price"));
                    product.setDiscount(rs.getFloat("discount"));
                    product.setCategoryId(rs.getInt("category_id"));
                    ProductsViews.printProduct(product);
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
            continueBoolean = ProductsViews.continueBoolean();
        }
    }

    public static boolean processUpdate() {
        String oldName = "";
        String oldDescription = "";
        String oldQuantity = "";
        String oldPrice = "";
        String oldCategoryId = "";
        boolean continueBoolean = true;
        while (continueBoolean) {
            try {
                int count = 0;
                System.out.println("Nhap ma so : ");
                String barCode = ScannerUtilities.getString();
                Statement stt = DAO.getConnection().createStatement();
                String sql = String.format("SELECT * FROM products "
                        + "WHERE barcode = '%s'", barCode);
                ResultSet rs = stt.executeQuery(sql);
                //In ra thong tin san pham truoc khi sua
                while (rs.next()) {
                    oldName = rs.getString("name");
                    oldDescription = rs.getString("description");
                    oldQuantity = rs.getString("quantity");
                    oldPrice = rs.getString("price");
                    oldCategoryId = rs.getString("category_id");
                    System.out.println("Ten            :" + oldName);
                    System.out.println("Mo ta          :" + oldDescription);
                    System.out.println("So luong       :" + oldQuantity);
                    System.out.println("Gia            :" + oldPrice);
                    System.out.println("Ma chung loai  :" + oldCategoryId);
                    ++count;
                }
                //Kiem tra xem co san pham hay khong
                if (count == 0) {
                    System.err.println("Khong co san pham !!!");
                    return false;
                }
                //Nhap thong tin moi cua san pham
                System.out.println("Chu y : Neu ban khong muon sua ,"
                        + "hay de trong va tiep tuc !!!");
                System.out.println("Nhap ten moi           :");
                String newName = ScannerUtilities.getString();
                System.out.println("Nhap mo ta moi         :");
                String newDescription = ScannerUtilities.getString();
                System.out.println("Nhap so luong moi      :");
                String newQuantity = ScannerUtilities.getString();
                System.out.println("Nhap gia moi           :");
                String newPrice = ScannerUtilities.getString();
                System.out.println("Nhap ma chung loai moi :");
                String newCategoryId = ScannerUtilities.getString();
                //Gan gia tri cu neu de trong
                Product product = new Product();
                if (newName.isEmpty()) {
                    newName = oldName;
                }
                if (newDescription.isEmpty()) {
                    newDescription = oldDescription;
                }
                if (newQuantity.isEmpty()) {
                    newQuantity = oldQuantity;
                }
                if (newPrice.isEmpty()) {
                    newPrice = oldPrice;
                }
                if (newCategoryId.isEmpty()) {
                    newCategoryId = oldCategoryId;
                }
                product.setBarCode(barCode);
                product.setName(newName);
                product.setDescription(newDescription);
                product.setQuantity(Integer.parseInt(newQuantity));
                product.setPrice(Float.parseFloat(newPrice));
                product.setCategoryId(Integer.parseInt(newCategoryId));
                //Goi den model va hoi co muon tiep tuc khong
                ProducstModel.update(product);
                continueBoolean = ProductsViews.continueBoolean();
            } catch (SQLException e) {
                System.err.println("Khong the update !!!");
            }
        }
        return true;
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
        ProducstModel.productsInsert(product);
    }
//hàm controller print all products 
    public static void productsPrintAll() {
        ResultSet rs = ProducstModel.productsPrintAll();
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
                ProductsViews.printProduct(product);
            }
        } catch (SQLException ex) {
            System.err.println("Co loi xay ra! " + ex);
        }
    }
}
