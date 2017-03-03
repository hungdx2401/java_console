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
			e.printStackTrace();
			System.err.println("Da xay ra loi !!!");
		}
	}

	public static void insert() {
		String id = "";
		String name = "";
		String email = "";
		String pass = "";
		String status = "";

		System.out.println("Nhap Ho va Ten: ");
		name = ScannerUtilities.getString();
		System.out.println("Nhap Email: ");
		email = ScannerUtilities.getString();
		System.out.println("Nhap Mat Khau: ");
		pass = ScannerUtilities.getString();
		try {
			Statement statement = DAO.getConnection().createStatement();
			String sqlString = "INSERT INTO admin (name, email, pass) "
				+ "VALUES('" + name + "', '" + email + "', '" + pass + "')";
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
				System.out.println("----ID: " + rs.getString("id"));
				System.out.println("----Ho va Ten: " + rs.getString("name"));
				System.out.println("----Email: " + rs.getString("email"));
				System.out.println("----Mat Khau: " + rs.getString("pass"));
				System.out.println("----Tinh Trang: " + rs.getString("status"));
				System.out.println("----Ngay Tao: " + rs.getString("created_at"));
				System.out.println("----Ngay Sua: " + rs.getString("updated_at"));
				System.out.println("-------------------------");
			}
		} catch (Exception e) {
			System.out.println("Loi Hien Thi Admin!");
		}
	}

	public static void delete() {
		int id;

		System.out.println("Nhap ID Admin muon xoa: ");
		id = ScannerUtilities.getInt();
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

				while (true) {
					System.out.println("------------------------------------------");
					System.out.print("Ban muon tiep tuc khong? (yes/no): ");
					choice = new Scanner(System.in).nextLine();
					if (!"yYnN".contains(choice)) {
						System.out.println("Ban hay nhap (Y/N)");
					}
					if (!"yY".contains(choice)) {
						System.out.println("Ban da thoat!");
					} else {
						try {
							String sqlString = "DELETE FROM admin Where id = " + id;
							statement.execute(sqlString);
							System.out.println("Xoa thanh cong Admin!");
							break;
						} catch (Exception e) {
							System.out.println("Loi Them Admin!");
						}
					}

				}

			}
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}

	public static int loginAdmin(String name, String password) {
		int count = 0;
		try {
			String checklogin = String.format("SELECT * FROM admin WHERE name = '%s' AND pass = '%s'", name, password);

			ResultSet rs = DAO.getConnection().createStatement().executeQuery(checklogin);
			while (rs.next()) {
				++count;
			}
			if (count > 0) {
				System.out.println("Dang nhap thanh cong.");
			} else {
				System.out.println("Thông tin không chính xác!");
				System.out.println("-----------------------------------");
				System.out.println("Chọn 1: Đăng nhập lại");
				System.out.println("Chọn 2: Đóng chương trình");
				System.out.println("-----------------------------------");
				int choice = ScannerUtilities.choiceInput(1,2);
				if (choice == 2) {
					System.exit(0);
				}
			}
		} catch (Exception e) {
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
