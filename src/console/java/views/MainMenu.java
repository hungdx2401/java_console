/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.views;

import console.java.utilities.ScannerUtilities;

/**
 *
 * @author Thang
 */
public class MainMenu {

    public static void mainMenu() {
        while (true) {
            System.out.println("-----------------------------------------");
            System.out.println("--Phan mem quan ly san pham va don hang--");
            System.out.println("1.Menu san pham");
            System.out.println("2.Menu admins");
            System.out.println("3.Thoat");
            System.out.println("-----------------------------------------");
            System.out.println("Chon : ");
            int choice = ScannerUtilities.getInt();
            if(choice == 1){
                ProductsViews.productsMenu();
            }
            if(choice == 2){
                AdminViews.menuAdmin();
            }
            if(choice == 3){
                break;
            }
        }
    }
}
