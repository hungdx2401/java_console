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
            System.err.println("Email sai !!!");
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
    public static boolean checkExistance(String str) {
        try {
            Statement statement = DAO.getConnection().createStatement();
            String sqlString1 = "SELECT * FROM admin WHERE name = '" + str + "'";
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
}
