package console.java.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminModels {

    public static void insert() {
        String id = "";
        String name = "";
        String email = "";
        String passwords = "";
        String status = "";
        String creat_at = "";
        
        System.out.println("Nhập ID: ");
        id = new Scanner(System.in).nextLine();
        System.out.println("Nhập Name: ");
        name = new Scanner(System.in).nextLine();
        System.out.println("Nhập Email: ");
        email = new Scanner(System.in).nextLine();
        System.out.println("Nhập Passwords: ");
        passwords = new Scanner(System.in).nextLine();
        try {
            Statement statement = DAO.getConnection().createStatement();
            String sqlString = "INSERT INTO admin (ID, name, email, passwords) "
                    + "VALUES('" + id + "', '" + name + "', '" + email + "', '" + passwords + "', '" + status + "','"+ creat_at +"')";
            statement.execute(sqlString);
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm sv!");
        }
    }
}
