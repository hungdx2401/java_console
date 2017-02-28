package console.java.model;

import console.java.models.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminModel {

    public static void insert() {
        String id = "";
        String name = "";
        String email = "";
        String pass = "";
        String status = "";

        System.out.println("Nhap Ho va Ten: ");
        name = new Scanner(System.in).nextLine();
        System.out.println("Nhap Email: ");
        email = new Scanner(System.in).nextLine();
        System.out.println("Nhap Mat Khau: ");
        pass = new Scanner(System.in).nextLine();
        try {
            Statement statement = DAO.getConnection().createStatement();
            String sqlString = "INSERT INTO admin (name, email, pass) "
                    + "VALUES('" + name + "', '" + email + "', '" + pass + "')";
            statement.execute(sqlString);
        } catch (SQLException e) {
            System.out.println("Loi khi them Admin!");
        }
    }

    public static void getAllAdmin() {
        System.out.println("_________Danh Sach Admin_________");
        try {
            Statement statement = DAO.getConnection().createStatement();
            String sqlString = "SELECT * FROM admin";
            ResultSet rs = statement.executeQuery(sqlString);

            while (rs.next()) {
                System.out.println("-------------------------");
                System.out.println("ID: " + rs.getString("ID"));
                System.out.println("Ho va Ten: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Mat Khau: " + rs.getString("pass"));
                System.out.println("Tinh Trang: " + rs.getString("status"));
                System.out.println("Ngay Tao: " + rs.getString("created_at"));
                System.out.println("Ngay Sua: " + rs.getString("updated_at"));
                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            System.out.println("Loi Hien Thi Admin!");
        }
    }

    public static void delete() {
        String id = "";

        System.out.println("Nhap ID Admin muon xoa: ");
        id = new Scanner(System.in).nextLine();
        try {
            Statement statement = DAO.getConnection().createStatement();
            String sqlString1 = "SELECT * FROM admin Where ID = '" + id + "'";
            ResultSet rs = statement.executeQuery(sqlString1);
            if (rs.next() == false) {
                System.out.println("Khong co ID nhu tren!");
            } else {

                String choice = "";
                boolean loop = true;

                while (true) {
                    System.out.println("------------------------------------------");
                    System.out.print("Ban muon tiep tuc khong? (yes/no): ");
                    choice = new Scanner(System.in).nextLine();
                    if (!"yYnN".contains(choice)) {
                        System.err.println("Vui Long Nhap (y/n)");
                        try {
                            String sqlString = "DELETE FROM admin Where id = " + id;
                            statement.execute(sqlString);
                        } catch (Exception e) {
                            System.out.println("Loi Xoa Admin!");
                        }
                    } else {
                        break;
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    public static void main(String[] args) {
        delete();
    }
}
