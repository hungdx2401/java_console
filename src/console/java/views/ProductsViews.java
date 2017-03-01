/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.views;

import console.java.controllers.ProductsController;
import console.java.entities.Product;
import console.java.utilities.ScannerUtilities;
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
            if (!"yYnN".contains(choice)) {
                System.err.println("Vui lòng chỉ nhập 'y' hoặc 'n'");
            } else {
                break;
            }
        }
        return "yY".contains(choice);
    }
    
    public static void productsMenu(){
        while(true){
            System.out.println("1.Them moi san pham");
            System.out.println("2.Danh sach san pham");
            System.out.println("3.Sua thong tin san pham");
            System.out.println("4.Tim kiem san pham");
            System.out.println("5.Xoa san pham");
            System.out.println("6.Quay lai");
            System.out.println("Chon : ");
            int choice = ScannerUtilities.getInt();
            if(choice == 1){
                ProductsController.productsInsert();
            }
            if(choice == 2){
                ProductsController.productsPrintAll();
            }
            if(choice == 3){
                ProductsController.processUpdate();
            }
            if(choice == 4){
                ProductsController.searchProduct();
            }
            if(choice == 5){
                
            }
            if(choice == 6){
                break;
            }
        }
    }

}
