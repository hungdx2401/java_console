/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.model;

import console.java.entity.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    public static void update(Product product){
        try {
            String updateQuery = "UPDATE products SET name='%s',description='%s',quantity=%d,price=%f,category_id=%d";
            String update = String.format(updateQuery,product.getName(),product.getDescription(),product.getQuantity(),product.getPrice(),product.getCategoryId());
            Statement stt = DAO.getConnection().createStatement();
            stt.execute(update);
            System.out.println("Update thanh cong !!!");
        } catch (Exception e) {
            System.err.println("Da xay ra loi !!!");
        }
    }
}
