package console.java.views;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminViews {

    public static void logIn() {
        String email = "";
        String password = "";

        System.out.println("-------------------------------");
        System.out.println("----------_*Sign In*_----------");
        System.out.println("-- Account:");
        email = new Scanner(System.in).next();
        System.out.println("-- Password:");
        password = new Scanner(System.in).next();
        System.out.println("-------------------------------");
    }
    
    public static void logOut(){
        System.out.println("-------------------------------");
        System.out.println("----------Thông Báo------------");
        System.out.println("-------Bạn đã đăng xuất!-------");
        System.out.println("-------------------------------");
        System.out.println("-------------------------------");
    }
}