/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.models;

import console.java.entities.Admin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import console.java.utilities.ScannerUtilities;
import console.java.utilities.ValidateUtilities;
import console.java.views.ProductsViews;

/**
 *
 * @author Thang
 */
public class AdminsModel {

    public static void update(Admin admin) {
        try {
            String updateQuery = "UPDATE admin SET name='%s'"
                    + ",email='%s',pass='%s',updated_at=NOW() "
                    + "WHERE id =" + admin.getId();
            String update = String.format(updateQuery,
                    admin.getName(),
                    admin.getEmail(),
                    admin.getPassword());
            Statement stt = DAO.getConnection().createStatement();
            stt.execute(update);
            System.out.println("Update thanh cong !!!");
        } catch (Exception e) {
            System.err.println("Da xay ra loi !!!" + e);
        }
    }

    public static void insert() throws Exception{
        String id = "";
        String name = "";
        String email = "";
        String pass = "";
        String status = "";
        try {
            Statement statement = DAO.getConnection().createStatement();
            do {
                System.out.println("Nhap Ho va Ten: ");
                name = ScannerUtilities.getString();
            } while (ValidateUtilities.checkBlank(name) == false || ValidateUtilities.checkExistanceAdmin(name) == false);
            do {
                System.out.println("Nhap Email: ");
                email = ScannerUtilities.getString();
            } while (ValidateUtilities.checkBlank(email) == false
                    || ValidateUtilities.validateEmail(email) == false);
            do {
                System.out.println("Nhap Mat Khau: ");
                pass = ScannerUtilities.getString(5);
            } while (ValidateUtilities.checkBlank(pass) == false);

            String sqlString = "INSERT INTO admin (name, email, pass) "
                    + "VALUES('" + name + "', '" + email + "', '" + pass + "')";
            statement.execute(sqlString);
            System.out.println("Them thanh cong");
        } catch (Exception e) {
            System.err.println("Loi " + e);
        }
    }

    public static void getAllAdmin() {
        ResultSet rs;
        // LẤY Tổng bản ghi
        int total;
        try {
            rs = DAO.getConnection().createStatement().executeQuery("select count(*) from admin");
            rs.next();
            total = rs.getInt(1);
        } catch (SQLException ex) {
            System.err.println("Có lỗi! " + ex);
            return;
        }
        // NHẬP Số bản ghi mỗi trang
        int perPage;
        // TÍNH Số trang theo perPage
        int totalPages;
        // offset
        int offset;
        // strQuery
        String strQuery;
        // Nhập trang muốn xem
        int pageNumber;
        Admin admin;
        if (total == 0) {
            System.out.println("Không có dữ liệu!");
        } else {
            System.out.println("Bạn muốn xem bao nhiêu tài khoản / mỗi trang?");
            perPage = ScannerUtilities.getInt();
            if (perPage == 0) {
                System.err.println("0 tài khoản mỗi trang!");
                System.err.println("...quay lại");
                return;
            }
            if (total % perPage == 0) {
                totalPages = total / perPage;
            } else {
                totalPages = total / perPage + 1;
            }
            // Hiển thị tổng số trang
            System.out.printf("Bạn có [%d] trang để hiển thị\n", totalPages);
            boolean continueBoolean = true;
            while (continueBoolean) {
                System.out.print("Bạn muốn xem trang mấy? ");
                pageNumber = ScannerUtilities.getInt(1, totalPages);
                offset = (pageNumber - 1) * perPage;

                String Query = String.format("SELECT * FROM admin LIMIT %s OFFSET %s;", perPage, offset);
                ResultSet results;
                String leftAlignFormat = "| %-10s | %-30s | %-30s | %-20s | %-20s | %-20s | %n";
                System.out.println("Danh Sach Admin");
                System.out.format("-----------------------------------------------------------------------------------------------------------------------------------------------------%n");
                System.out.format(leftAlignFormat, "ID", "Ten", "Email", "Password", "Ngay tao", "Ngay sua");
                System.out.format("-----------------------------------------------------------------------------------------------------------------------------------------------------%n");
                try {
                    results = DAO.getConnection().createStatement().executeQuery(Query);
                    while (results.next()) {
                        System.out.printf(leftAlignFormat, results.getString("id"),
                                results.getString("name"), results.getString("email"),results.getString("pass"),
                                results.getString("created_at"), results.getString("updated_at"));
                    }
                    results.first();
                } catch (SQLException ex) {
                    System.err.println("Có lỗi xảy ra! " + ex);
                }
                System.out.format("-----------------------------------------------------------------------------------------------------------------------------------------------------%n");
                continueBoolean = ProductsViews.continueBoolean();
            }
        }
    }

    public static void delete() {
        int id;
        while (true) {
            System.out.println("Nhap ID Admin muon xoa: ");
            id = ScannerUtilities.getInt();
            if (id == SessionAmin.getId()) {
                System.err.println("Bạn không thể xóa chính mình!");
                return;
            }
            try {
                Statement statement = DAO.getConnection().createStatement();
                String sqlString1 = "SELECT * FROM admin Where id = '" + id + "'";
                ResultSet rs = statement.executeQuery(sqlString1);
                if (rs.next() == false) {
                    System.out.println("Khong co ID nhu tren!");
                } else {
                    String choice = "";
                    boolean loop = true;
                    System.out.println("-------------------------");
                    System.out.println("----ID: " + rs.getString("id"));
                    System.out.println("----Ho va Ten: " + rs.getString("name"));
                    System.out.println("----Email: " + rs.getString("email"));
                    System.out.println("----Mat Khau: " + rs.getString("pass"));
                    System.out.println("----Tinh Trang: " + rs.getString("status"));
                    System.out.println("----Ngay Tao: " + rs.getString("created_at"));
                    System.out.println("----Ngay Sua: " + rs.getString("updated_at"));
                    System.out.println("-------------------------");

                    System.out.println("------------------------------------------");
                    System.out.print("Ban muon xoa khong? (y/n): ");
                    choice = new Scanner(System.in).nextLine();
                    if (!"yYnN".contains(choice)) {
                        System.out.println("Ban hay nhap (Y/N)");
                    } else {
                        try {
                            String sqlString = "DELETE FROM admin Where id = " + id;
                            statement.execute(sqlString);
                            System.out.println("Xoa thanh cong Admin!");
                        } catch (Exception e) {
                            System.out.println("Loi Them Admin!");
                        }
                    }
                    if (ProductsViews.continueBoolean() == false) {
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
    }

    public static int loginAdmin(String name, String password) {
        int count = 0;
        try {
            String checklogin = String.format("SELECT * FROM admin WHERE name = '%s' AND pass = '%s'", name, password);

            ResultSet rs = DAO.getConnection().createStatement().executeQuery(checklogin);
            while (rs.next()) {
                ++count;
                SessionAmin.setId(rs.getInt("id"));
                SessionAmin.setName(rs.getString("name"));
                SessionAmin.setEmail(rs.getString("email"));
            }
            if (count > 0) {
                System.out.println("Đăng nhập thành công.");
            } else {
                System.out.println("Thông tin không chính xác!");
                System.out.println("-----------------------------------");
                System.out.println("Chọn 1: Đăng nhập lại");
                System.out.println("Chọn 2: Đóng chương trình");
                System.out.println("-----------------------------------");
                int choice = ScannerUtilities.choiceInput(1, 2);
                if (choice == 2) {
                    System.exit(0);
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi kiem tra Admin");
        }
        return count;
    }

    public static ResultSet searchAdmin(String keyword, int option) {
        String column;
        switch (option) {
            case 1:
                System.out.println("--- Tìm kiếm theo mã admin ---");
                column = "id";
                break;
            case 2:
                System.out.println("--- Tìm kiếm theo tên admin ---");
                column = "name";
                break;
            default:
                System.out.println("--- Tìm kiếm theo email admin ---");
                column = "email";
                break;
            case 4:
                System.out.println("Quay lai Menu Admin!");
                column = "";
                break;
        }
        String strQuery = "SELECT * FROM admin WHERE " + column + " LIKE '%"
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

    public static Admin getAdminFromResultSet(ResultSet rs) {
        Admin admin = new Admin();
        try {
            admin.setId(rs.getInt("id"));
            admin.setName(rs.getString("name"));
            admin.setEmail(rs.getString("email"));
        } catch (SQLException ex) {
            System.err.println("Lỗi gì đó! " + ex);
        }
        return admin;
    }
}
