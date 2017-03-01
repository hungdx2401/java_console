package console.java.views;

import console.java.controllers.AdminsController;
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

    public static void menuAdmin() {
        while (true) {
            System.out.println("1.Them admin");
            System.out.println("2.Danh sach admin");
            System.out.println("3.Sua thong tin admin");
            System.out.println("4.Tim kiem admin");
            System.out.println("5.Xoa admin");
            System.out.println("6.Quay lai");
            System.out.println("Chon : ");
            int choice = ScannerUtilities.getInt();
            if(choice == 1){
                
            }
            if(choice == 2){
                
            }
            if(choice == 3){
                AdminsController.processUpdate();
            }
            if(choice == 4){
                
            }
            if(choice == 5){
                
            }
            if(choice == 6){
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        menuAdmin();
    }
}
