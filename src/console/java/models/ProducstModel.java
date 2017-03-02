/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.models;

import console.java.entities.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class này chứa các hàm thao tác với Database
 *
 * @author DongHo
 */
public class ProducstModel {

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
                System.out.println("--- Tìm kiếm theo mã sản phẩm ---");
                column = "barCode";
                break;
            case 2:
                System.out.println("--- Tìm kiếm theo tên sản phẩm ---");
                column = "name";
                break;
            default:
                System.out.println("--- Tìm kiếm theo mô tả sản phẩm ---");
                column = "description";
                break;
        }
        String strQuery = "SELECT * FROM products WHERE " + column + " LIKE '%"
                + keyword + "%';";
        ResultSet rs;
        try {
            rs = DAO.getConnection().createStatement().executeQuery(strQuery);
        } catch (SQLException ex) {
            System.err.println("Có lỗi xảy ra! " + ex);
            return null;
        }
        return rs;
    }

    public static void update(Product product) {
        try {
            String updateQuery = "UPDATE products "
                    + "SET name='%s',description='%s'"
                    + ",quantity=%d,price=%f,category_id=%d,updated_at=NOW()"
                    + " WHERE barcode = '" + product.getBarCode() + "'";
            String update = String.format(updateQuery,
                    product.getName(),
                    product.getDescription(),
                    product.getQuantity(),
                    product.getPrice(),
                    product.getCategoryId());
            Statement stt = DAO.getConnection().createStatement();
            stt.execute(update);
            System.out.println("Update thanh cong !!!");
        } catch (Exception e) {
            System.err.println("Da xay ra loi !!!");
        }
    }

    //Model insert a new product
    public static void productsInsert(Product product) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement(""
                    + "Insert into products(barcode,name,description,quantity"
                    + ",price,category_id,status) values(?,?,?,?,?,?,1)");
            pstmt.setString(1, product.getBarCode());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getDescription());
            pstmt.setInt(4, product.getQuantity());
            pstmt.setFloat(5, product.getPrice());
            pstmt.setInt(6, product.getCategoryId());
            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                System.out.println("Them thanh cong.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi them san pham.");
        }
    }

    //Model get all products
    public static ResultSet productsPrintAll() {
        ResultSet rs;
        try {
            rs = DAO.getConnection().createStatement().executeQuery("SELECT * "
                    + "FROM products;");
        } catch (Exception e) {
            System.err.println("Co loi xay ra! " + e);
            return null;
        }
        return rs;
    }

    public static void insert(Product product) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement(""
                    + "Insert into user values(?,?,?,?,?,?)");
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

    /**
     * Hàm này lấy dữ liệu từ một ResultSet và gán vào một đối tượng Product
     * @param rs
     * @return Product Object
     */
    public static Product getProductFromResultSet(ResultSet rs) {
        Product product = new Product();
        try {
            product.setBarCode(rs.getString("barCode"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setQuantity(rs.getInt("quantity"));
            product.setPrice(rs.getFloat("price"));
            product.setDiscount(rs.getFloat("discount"));
            product.setCategoryId(rs.getInt("category_id"));
        } catch (SQLException ex) {
            System.err.println("Lỗi gì đó! " + ex);
        }

        return product;
    }

}
