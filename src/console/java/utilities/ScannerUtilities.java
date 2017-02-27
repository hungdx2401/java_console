/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.utilities;

import java.util.Scanner;

/**
 * Class này chứa các tiện ích nhập số, ký tự, chuỗi... từ người dùng
 *
 * @author DongHo
 */
public class ScannerUtilities {

    /**
     * Hàm này trả về một số mà người dùng nhập từ bàn phím
     *
     * @return int
     */
    public static int getInt() {
        int inputInt = 0;
        while (true) {
            try {
                inputInt = new Scanner(System.in).nextInt();
                break;
            } catch (Exception e) {
                System.err.println("Vui lòng chỉ nhập số! ");
            }
        }
        return inputInt;
    }
    
    public static float getFloat() {
        float inputInt = 0;
        while (true) {
            try {
                inputInt = new Scanner(System.in).nextFloat();
                break;
            } catch (Exception e) {
                System.err.println("Vui long chi nhap so! ");
            }
        }
        return inputInt;
    }

    /**
     * Hàm này trả về một chuỗi mà người dùng nhập vào từ bàn phím
     *
     * @return String
     */
    public static String getString() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * Hàm này trả về một chuỗi có độ dài tối thiểu @param ký tự
     *
     * @param length
     * @return String
     */
    public static String getString(int length) {
        String inputString;
        while (true) {
            inputString = new Scanner(System.in).nextLine();
            if (inputString.length() < length) {
                System.err.printf("Vui lòng nhập lại tối thiểu " + length + " ký tự! ");
            } else {
                break;
            }
        }
        return inputString;
    }

    /**
     * Hàm này trả về một ký tự mà người dùng nhập từ bàn phím
     *
     * @return char
     */
    public static char getChar() {
        String input;
        while (true) {
            input = new Scanner(System.in).nextLine();
            if (input.length() != 1) {
                System.err.println("Vui lòng nhập định dạng chỉ một ký tự! ");
            } else {
                break;
            }
        }
        return input.charAt(0);
    }

} // END - class
