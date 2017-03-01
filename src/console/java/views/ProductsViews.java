/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.views;

import console.java.entities.Product;
import java.util.Scanner;

/**
 * Class này chứa các hàm đảm nhiệm việc in ra màn hình cho người dùng
 *
 * @author DongHo
 */
public class ProductsViews {

    /**
     * Hàm này thực hiện việc in chi tiết sản phẩm ra ngoài màn hình
     * @param product 
     */
    public static void printProduct(Product product) {
        System.out.println("----------------------------------------------------");
        System.out.println("- Mã sản phẩm: " + product.getBarCode());
        System.out.println("- Tên sản phẩm: "+ product.getName());
        System.out.println("- Mô tả sản phẩm: "+ product.getDescription());
        System.out.println("- Số lượng: "+ product.getQuantity());
        System.out.println("- Giá: "+ product.getPrice());
        System.out.println("- Loại sản phẩm: "+ product.getCategoryId());
        System.out.println("- Giảm giá: "+ product.getDiscount());
        System.out.println(""); // Dòng trống
    }
    
    /**
     * Hàm này thực hiện việc in thông báo để bắt người dùng nhập vào tùy chọn tìm kiếm
     */
    public static void searchOption() {
        System.out.println("--------------------- TÙY CHỌN TÌM KIẾM -------------------------------");
        System.out.println("1. Tìm theo mã sản phẩm");
        System.out.println("2. Tìm theo tên sản phẩm");
        System.out.println("3. Tìm theo mô tả sản phẩm");
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
            if (!"yYnN".contains(choice)||choice.isEmpty()) {
                System.err.println("Vui lòng chỉ nhập 'y' hoặc 'n'");
            } else {
                break;
            }
        }
        return "yY".contains(choice);
    }
    public static boolean agreementBoolean(){
        String choice = "";
        boolean loop = true;

        while (true) {
            System.out.println("------------------------------------------");
            System.out.print("Ban co muon xoa khong ? (y/n): ");
            choice = new Scanner(System.in).nextLine();
            if (!"yYnN".contains(choice)||choice.isEmpty()) {
                System.err.println("Vui lòng chỉ nhập 'y' hoặc 'n'");
            } else {
                break;
            }
        }
        return "yY".contains(choice);
    }

}
