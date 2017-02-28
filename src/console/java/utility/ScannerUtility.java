/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.utility;

import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class ScannerUtility {

    public static int getInt() {
        int input = 0;
        while (true) {
            try {
                input = new Scanner(System.in).nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Please enter the interger");
            }
        }
        return input;
    }

    public static String getString() {
        String inputString = "";
        while (true) {
            try {
                inputString = new Scanner(System.in).nextLine();
                if (inputString.length() > 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter the String");
            }

        }
        return inputString;
    }

    public static float getFloat() {
        float inputFloat = 0;
        while (true) {
            try {
                inputFloat = new Scanner(System.in).nextFloat();
                break;
            } catch (Exception e) {
                System.out.println("Please enter the Float");
            }
        }
        return inputFloat;
    }
}
