/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.utilities;

/**
 *
 * @author Thang
 */
public class ValidateUtilities {
    //Ham kiem tra email
    public static boolean validateEmail(String str){
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        Boolean b = str.matches(EMAIL_REGEX);
        if(!b){
            System.err.println("Email sai !!!");
            return false;
        }
        return true;
    }
    //Ham kiem tra trong
    public static boolean checkBlank(String str){
        if(str.isEmpty()){
            System.out.println("Vui long nhap !");
            return false;
        }
        return true;
    }
}
