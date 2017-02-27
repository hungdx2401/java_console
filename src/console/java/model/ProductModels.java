/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.model;

import console.java.entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class này chứa các hàm thao tác với Database
 *
 * @author DongHo
 */
public class ProductModels {

    /**
     * Hàm này tìm kiếm sản phẩm theo tên sản phẩm với tham số là từ khóa tìm
     * kiếm
     *
     * @param keyword
     * @param option
     * @return ResultSet
     */
    public static ResultSet searchProduct(String keyword, int option) {
        String column;
        switch (option) {
            case 1:
                column = "barCode";
                break;
            case 2:
                column = "name";
                break;
            default:
                column = "description";
                break;
        }
        String strQuery = "SELECT * FROM products WHERE " + column + " LIKE '%" + keyword + "%';";
        ResultSet rs;
        try {
            rs = DAO.getConnection().createStatement().executeQuery(strQuery);
        } catch (SQLException ex) {
            System.err.println("Có lỗi xảy ra! " + ex);
            return null;
        }
        return rs;
    }

    //Model insert a new product
    public static void productsinsert(Product product) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement("Insert into products values(?,?,?,?,?,?)");
            pstmt.setString(1, product.getBarCode());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getDescription());
            pstmt.setInt(4, product.getQuantity());
            pstmt.setFloat(5, product.getPrice());
            pstmt.setInt(6, product.getCategoryId());
            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                System.out.println("them thanh cong.");
            }
        } catch (Exception e) {
            System.out.println("Loi them san pham.");
        }

    }
}
