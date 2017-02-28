package console.java.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminsModel {

    public static void insert() {
        String id = "";
        String name = "";
        String email = "";
        String pass = "";
        String status = "";

        System.out.println("Nhap ID: ");
        id = new Scanner(System.in).nextLine();
        System.out.println("Nhap Ho va Ten: ");
        name = new Scanner(System.in).nextLine();
        System.out.println("Nhap Email: ");
        email = new Scanner(System.in).nextLine();
        System.out.println("Nhap Mat Khau: ");
        pass = new Scanner(System.in).nextLine();
        System.out.println("Nhap Trang Thai: ");
        status = new Scanner(System.in).nextLine();
        try {
            Statement statement = DAO.getConnection().createStatement();
            String sqlString = "INSERT INTO admin (name, email, pass, status) "
                    + "VALUES('" + name + "', '" + email + "', '" + pass + "', " + status + ")";
            statement.execute(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
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
                System.out.println("Mat Khau: " + rs.getString("passwords"));
                System.out.println("Tinh Trang: " + rs.getString("status"));
                System.out.println("Ngay Tao: " + rs.getString("status"));
                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            System.out.println("Loi Hien Thi Admin!");
        }
    }

    public static void update() {

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
                try {
                    String sqlString = "DELETE FROM admin Where ID = '" + id + "';";

                    String choice = "";
                    boolean loop = true;

                    while (true) {
                        System.out.println("------------------------------------------");
                        System.out.print("Ban muon tiep tuc khong? (yes/no): ");
                        choice = new Scanner(System.in).nextLine();
                        if (!"yYnN".contains(choice)) {
                            System.err.println("Vui Long Nhap (y/n)");
                            statement.execute(sqlString);
                        } else {
                            break;
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Loi Xoa Admin!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
}
