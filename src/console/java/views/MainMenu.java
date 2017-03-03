/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.views;

import console.java.utilities.ScannerUtilities;
import java.sql.SQLException;

/**
 *
 * @author Thang
 */
public class MainMenu {

    public static void mainMenu() throws SQLException {
        while (true) {
            System.out.println("-----------------------------------------");
            System.out.println("--Phan mem quan ly san pham va don hang--");
            System.out.println("1.Menu san pham");
            System.out.println("2.Menu admins");
            System.out.println("3.Thoat");
            System.out.println("-----------------------------------------");
            int choice = ScannerUtilities.choiceInput(1, 2, 3);
            switch (choice) {
                case 1:
                    ProductsViews.productsMenu();
                    break;
                case 2:
                    AdminViews.menuAdmin();
                    break;
            }
            if (choice == 3) {
                break;
            }
        }
    }
}