/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.views;

import console.java.controllers.ProductsController;
import console.java.entities.Product;
import console.java.utilities.ScannerUtilities;
import console.java.utilities.StringUtilities;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class này chứa các hàm đảm nhiệm việc in ra màn hình cho người dùng
 *
 * @author DongHo
 */
public class ProductsViews {

    /**
     * Hàm này thực hiện việc in chi tiết sản phẩm ra ngoài màn hình
     *
     * @param product
     */
    public static void printProduct(Product product) {
        String leftAlignFormat = "| %-20s | %-30s | %-60s | %-10s | %-10s | %-16s | %-10s | %n";
        System.out.printf(leftAlignFormat, product.getBarCode(),
                StringUtilities.truncate(product.getName(), 25), StringUtilities.truncate(product.getDescription(), 50),
                product.getQuantity(), product.getPrice(),
                product.getCategoryId(), product.getDiscount(),
                product.getDiscount());
    }
    

    /**
     * Hàm này thực hiện việc in thông báo để bắt người dùng nhập vào tùy chọn
     * tìm kiếm
     */
    public static void searchOption() {
        System.out.println("--------------------- TÙY CHỌN TÌM KIẾM -------------------------------");
        System.out.println("1. Tìm theo mã sản phẩm");
        System.out.println("2. Tìm theo tên sản phẩm");
        System.out.println("3. Tìm theo mô tả sản phẩm");
        System.out.print("--- Vui lòng chọn (1/2/3): ");
    }
    /**
     * Hàm này thực hiện việc in thông báo để bắt người dùng nhập vào tùy chọn
     * tìm xoa
     */
     public static void searchOptionDelete(){
        System.out.println("--------------------- TÙY CHỌN XOA -------------------------------");
        System.out.println("1. Xóa theo mã sản phẩm");
        System.out.println("2. Xóa theo tên sản phẩm");
        System.out.println("3. Xóa theo mô tả sản phẩm");
        System.out.print("--- Vui lòng chọn (1/2/3): ");
     }
    /**
     * Hàm này hiện ra câu hỏi "Bạn có muốn tiếp tục không? (y/n)" và trả về
     * [(y)? true : false]
     *
     * @return boolean
     */
    public static boolean continueBoolean() {
        String choice = "";
        boolean loop = true;

        while (true) {
            System.out.println("------------------------------------------");
            System.out.print("Bạn có muốn tiếp tục không? (y/n): ");
            choice = new Scanner(System.in).nextLine();
            if (!"yYnN".contains(choice) || choice.isEmpty()) {
                System.err.println("Vui lòng chỉ nhập 'y' hoặc 'n'");
            } else {
                break;
            }
        }
        return "yY".contains(choice);
    }
    
    public static boolean continueDelete() {
        String choice = "";
        boolean loop = true;

        while (true) {
            System.out.println("------------------------------------------");
            System.out.print("Bạn có muốn xóa không? (y/n): ");
            choice = new Scanner(System.in).nextLine();
            if (!"yYnN".contains(choice) || choice.isEmpty()) {
                System.err.println("Vui lòng chỉ nhập 'y' hoặc 'n'");
            } else {
                break;
            }
        }
        return "yY".contains(choice);
    }

    public static boolean agreementBoolean() {
        String choice = "";
        boolean loop = true;

        while (true) {
            System.out.println("------------------------------------------");
            System.out.print("Ban co muon xoa khong ? (y/n): ");
            choice = new Scanner(System.in).nextLine();
            if (!"yYnN".contains(choice) || choice.isEmpty()) {
                System.err.println("Vui lòng chỉ nhập 'y' hoặc 'n'");
            } else {
                break;
            }
        }
        return "yY".contains(choice);
    }

    /**
     * Hàm này hiện menu chính để thực hiện các thao tác với sản phẩm
     *
     * @throws java.sql.SQLException
     */
    public static void productsMenu() throws SQLException {
        while (true) {
            System.out.println("------------------------------------");
            System.out.println("1.Them moi san pham");
            System.out.println("2.Danh sach san pham");
            System.out.println("3.Sua thong tin san pham");
            System.out.println("4.Tim kiem san pham");
            System.out.println("5.Xoa san pham");
            System.out.println("6.Quay lai");
            System.out.println("------------------------------------");
            int choice = ScannerUtilities.choiceInput(1, 2, 3, 4, 5, 6);
            switch (choice) {
                case 1:
                    ProductsController.productsInsert();
                    break;
                case 2:
                    ProductsController.productsPrintAll();
                    break;
                case 3:
                    ProductsController.processUpdate();
                    break;
                case 4:
                    ProductsController.searchProduct();
                    break;
                case 5:
                    ProductsController.productsDelete();
                    break;
                case 6:
                    break;
                default:
                    System.err.println("Vui long chon lai !!!");
                    break;
            }
            if (choice == 6) {
                break;
            }
        }
    }
}
