/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.utilities;

import console.java.models.DAO;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Thang
 */
public class ValidateUtilities {

    //Ham kiem tra email
    public static boolean validateEmail(String str) {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        Boolean b = str.matches(EMAIL_REGEX);
        if (!b) {
            System.out.println("Email sai !!!");
            return false;
        } else if(str.isEmpty()){
            return true;
        }
        return true;
    }
    //Ham kiem tra trong
    public static boolean checkBlank(String str) {
        if (str.isEmpty()) {
            System.out.println("Vui long nhap !!!");
            return false;
        }
        return true;
    }
    //Ham kiem tra ton tai 
    public static boolean checkExistanceAdmin(String str) {
        try {
            Statement statement = DAO.getConnection().createStatement();
            String sqlString1 = "SELECT * FROM admins WHERE name = '" + str + "'";
            ResultSet rs = statement.executeQuery(sqlString1);
            if(rs.next() == true){
                System.out.println("Da ton tai ,vui long nhap lai !!!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Da co loi :" + e);
        }
        return true;
    }
    
    public static boolean checkExistanceProductsName(String str) {
        try {
            Statement statement = DAO.getConnection().createStatement();
            String sqlString1 = "SELECT * FROM products WHERE name = '" + str + "'";
            ResultSet rs = statement.executeQuery(sqlString1);
            if(rs.next() == true){
                System.out.println("Da ton tai ,vui long nhap lai !!!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Da co loi :" + e);
        }
        return true;
    }
    
    public static boolean checkExistanceProductsBarcode(String str) {
        try {
            Statement statement = DAO.getConnection().createStatement();
            String sqlString1 = "SELECT * FROM products WHERE barcode = '" + str + "'";
            ResultSet rs = statement.executeQuery(sqlString1);
            if(rs.next() == true){
                System.out.println("Da ton tai ,vui long nhap lai !!!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Da co loi :" + e);
        }
        return true;
    }
    
    //Ham kiem tra chuoi khi update
    public static boolean validateStringUpdate(String str) {
        Boolean b = str.matches("^[\\w\\d]+$");
        if(str.isEmpty()){
            return true;
        }
        if (!b) {
            System.out.println("Sai dinh dang !!!");
            return false;
        } 
        return true;
    }
    
    //Ham kiem tra so nguyen khi update
    public static boolean validateNumberUpdate(String str) {
        Boolean b = str.matches("^[\\d]+$");
        if(str.isEmpty()){
            return true;
        }
        if (!b) {
            System.out.println("Sai dinh dang !!!");
            return false;
        }
        return true;
    }
    
    //Ham kiem tra so thuc khi update
    public static boolean validateFloatUpdate(String str) {
        Boolean b = str.matches("[+-]?([0-9]*[.])?[0-9]+");
        if(str.isEmpty()){
            return true;
        }
        if (!b) {
            System.out.println("Sai dinh dang !!!");
            return false;
        }
        return true;
    }
}
