/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seeds;

import console.java.models.DAO;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Thang
 */
public class ProductsSeed {

    public static void main(String[] args) throws SQLException {
        Statement stt = DAO.getConnection().createStatement();
        stt.execute("TRUNCATE TABLE products");
        stt.execute("insert into products (barcode,name,description,quantity,price,category_id) values('abc1','adidas cloud foam','feel like ur walking on clouds',5,52.2,1)");
        stt.execute("insert into products (barcode,name,description,quantity,price,category_id) values('abc2','adidas ultra boost','make running a piece of cake',7,55.0,1)");
        System.out.println("Them du lieu mau thanh cong!");
    }
}
