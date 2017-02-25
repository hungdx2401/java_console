/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.model;

import console.java.entity.Product;
import java.sql.PreparedStatement;

/**
 *
 * @author Asus
 */
public class ProductModel {

    public static void insert(Product product) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement("Insert into user values(?,?,?,?,?,?)");
            pstmt.setString(1, product.getBarCode());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getDescription());
            pstmt.setInt(4, product.getQuantity());
            pstmt.setFloat(5, product.getPrice());
            pstmt.setInt(6, product.getCategoryId());
            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                System.out.println("thêm thành công");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi insert.");
        }
    }
}
