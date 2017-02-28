package console.java.views;

import java.util.Scanner;

public class AdminViews {

        public static void logIn() {
            String email="";
            String password="";

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
    }
