package console.java.views;

import console.java.controllers.AdminsController;
import console.java.entities.Admin;
import console.java.utilities.ScannerUtilities;
import java.util.Scanner;

public class AdminViews {

    public static void logIn() {
        String email = "";
        String password = "";

        System.out.println("---------------------------------");
        System.out.println("----------_*Dang Nhap*_----------");
        System.out.println("--Ten dang nhap:");
        email = new Scanner(System.in).next();
        System.out.println("--Mat Khau:");
        password = new Scanner(System.in).next();
        System.out.println("---------------------------------");
    }

    public static void logOut() {
        System.out.println("-------------------------------");
        System.out.println("----------Thong Bao------------");
        System.out.println("-------Ban da dang xuat!-------");
        System.out.println("-------------------------------");
        System.out.println("-------------------------------");
    }
    
    public static boolean continueBoolean() {
        String choice = "";
        boolean loop = true;

        while (true) {
            System.out.println("------------------------------------------");
            System.out.print("Bạn có muốn tiếp tục không? (y/n): ");
            choice = new Scanner(System.in).nextLine();
            if (!"yYnN".contains(choice)) {
                System.err.println("Vui lòng chỉ nhập 'y' hoặc 'n'");
            } else {
                break;
            }
        }
        return "yY".contains(choice);
    }
    
    public static void searchOption() {
        System.out.println("--------------------- TÙY CHỌN TÌM KIẾM -------------------------------");
        System.out.println("1. Tìm theo mã Admin");
        System.out.println("2. Tìm theo tên Admin");
        System.out.println("2. Tìm theo email");
        System.out.print("--- Vui lòng chọn (1/2/3): ");
    }

    public static void printAdmin(Admin admin) {
        System.out.println("----------------------------------------------------");
        System.out.println("- Mã Admin " + admin.getId());
        System.out.println("- Tên Admin: " + admin.getName());
        System.out.println("- Email Admin: " + admin.getEmail());
        System.out.println(""); // Dòng trống
    }
    public static void menuAdmin() {
        while (true) {
            System.out.println("----------------------------------");
            System.out.println("1.Them admin");
            System.out.println("2.Danh sach admin");
            System.out.println("3.Sua thong tin admin");
            System.out.println("4.Tim kiem admin");
            System.out.println("5.Xoa admin");
            System.out.println("6.Quay lai");
            System.out.println("----------------------------------");
            int choice = ScannerUtilities.choiceInput(1,2,3,4,5,6);
            switch (choice) {
                case 1:
                    AdminsController.processInsert();
                    break;
                case 2:
                    AdminsController.processList();
                    break;
                case 3:
                     AdminsController.processUpdate();
                    break;
                case 4:
                    AdminsController.searchAdmin();
                    break;
                case 5:
                    AdminsController.processDelete();
                    break;
            }
            if (choice == 6) {
                break;
            }
        }
    }
}
