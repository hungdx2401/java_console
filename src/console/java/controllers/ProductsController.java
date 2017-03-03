/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.controllers;

import console.java.entities.Product;
import console.java.models.DAO;
import console.java.models.ProducstModel;
import console.java.utilities.ScannerUtilities;
import console.java.views.ProductsViews;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class này chứa các hàm xử lý sản phẩm
 *
 * @author DongHo
 */
public class ProductsController {

	/**
	 * Hàm này thực hiện việc tìm kiếm theo các tùy chọn (tìm theo mã, tên,
	 * mô tả...)
	 */
	public static void searchProduct() {

		ResultSet rs;
		Product product;
		int count = 0;
		boolean continueBoolean = true;

		while (continueBoolean) {
			ProductsViews.searchOption();
			int option = 0;
			while (continueBoolean) {
				option = ScannerUtilities.getInt();
				if (option < 1 || option > 3) {
					System.out.println("Vui long nhap trong 1,2,3");
				} else {
					break;
				}
			}
			System.out.print("Nhap vao tu khoa muon tim kiem: ");
			String keywordStr = ScannerUtilities.getString(3);
			rs = ProducstModel.searchProduct(keywordStr, option);
			try {
				while (rs.next()) {
					product = ProducstModel.getProductFromResultSet(rs);
					ProductsViews.printProduct(product);
					++count;
				}
			} catch (SQLException ex) {
				System.err.println("Co loi xay ra! " + ex);
			}
			if (count == 0) {
				System.out.println("Khong tim thay san pham!");
			} else {
				System.out.println("Tim thay " + count + " san pham.");
			}
			continueBoolean = ProductsViews.continueBoolean();
		}
	}

	public static boolean processUpdate() {
		String oldName = "";
		String oldDescription = "";
		String oldQuantity = "";
		String oldPrice = "";
		String oldCategoryId = "";
		boolean continueBoolean = true;
		while (continueBoolean) {
			try {
				int count = 0;
				System.out.println("Nhap ma so : ");
				String barCode = ScannerUtilities.getString();
				Statement stt = DAO.getConnection().createStatement();
				String sql = String.format("SELECT * FROM products "
					+ "WHERE barcode = '%s'", barCode);
				ResultSet rs = stt.executeQuery(sql);
				//In ra thong tin san pham truoc khi sua
				while (rs.next()) {
					oldName = rs.getString("name");
					oldDescription = rs.getString("description");
					oldQuantity = rs.getString("quantity");
					oldPrice = rs.getString("price");
					oldCategoryId = rs.getString("category_id");
					System.out.println("Ten            :" + oldName);
					System.out.println("Mo ta          :" + oldDescription);
					System.out.println("So luong       :" + oldQuantity);
					System.out.println("Gia            :" + oldPrice);
					System.out.println("Ma chung loai  :" + oldCategoryId);
					++count;
				}
				//Kiem tra xem co san pham hay khong
				if (count == 0) {
					System.err.println("Khong co san pham !!!");
					return false;
				}
				//Nhap thong tin moi cua san pham
				System.out.println("Chu y : Neu ban khong muon sua ,"
					+ "hay de trong va tiep tuc !!!");
				System.out.println("Nhap ten moi           :");
				String newName = ScannerUtilities.getString();
				System.out.println("Nhap mo ta moi         :");
				String newDescription = ScannerUtilities.getString();
				System.out.println("Nhap so luong moi      :");
				String newQuantity = ScannerUtilities.getString();
				System.out.println("Nhap gia moi           :");
				String newPrice = ScannerUtilities.getString();
				System.out.println("Nhap ma chung loai moi :");
				String newCategoryId = ScannerUtilities.getString();
				//Gan gia tri cu neu de trong
				Product product = new Product();
				if (newName.isEmpty()) {
					newName = oldName;
				}
				if (newDescription.isEmpty()) {
					newDescription = oldDescription;
				}
				if (newQuantity.isEmpty()) {
					newQuantity = oldQuantity;
				}
				if (newPrice.isEmpty()) {
					newPrice = oldPrice;
				}
				if (newCategoryId.isEmpty()) {
					newCategoryId = oldCategoryId;
				}
				product.setBarCode(barCode);
				product.setName(newName);
				product.setDescription(newDescription);
				product.setQuantity(Integer.parseInt(newQuantity));
				product.setPrice(Float.parseFloat(newPrice));
				product.setCategoryId(Integer.parseInt(newCategoryId));
				//Goi den model va hoi co muon tiep tuc khong
				ProducstModel.update(product);
				continueBoolean = ProductsViews.continueBoolean();
			} catch (SQLException e) {
				System.err.println("Khong the update !!!");
			}
		}
		return true;
	}

	/*
    hàm insert product lấy giá trị từ bàn phím
	 */
	public static void productsInsert() {
		System.out.println("Them san pham moi");
		System.out.println("Nhap Barcode ");
		String barCode = ScannerUtilities.getString();
		System.out.println("Nhap name ");
		String name = ScannerUtilities.getString();
		System.out.println("Nhap description");
		String description = ScannerUtilities.getString();
		System.out.println("Nhap quantity ");
		int quantity = ScannerUtilities.getInt();
		System.out.println("Nhap price ");
		float price = ScannerUtilities.getFloat();
		System.out.println("Nhap categoryId ");
		int categoryId = ScannerUtilities.getInt();
		Product product = new Product();
		product.setBarCode(barCode);
		product.setName(name);
		product.setDescription(description);
		product.setQuantity(quantity);
		product.setPrice(price);
		product.setCategoryId(categoryId);
		ProducstModel.productsInsert(product);
	}

	/**
	 * Hàm này in ra toàn bộ sản phẩm có tùy chọn phân trang
	 * @throws java.sql.SQLException
	 */
	public static void productsPrintAll() throws SQLException {
		ResultSet rs;
		// LẤY Tổng bản ghi
		int total;
		try {
			rs = DAO.getConnection().createStatement().executeQuery("select count(*) from products");
			rs.next();
			total = rs.getInt(1);
		} catch (SQLException ex) {
			System.err.println("Có lỗi! " + ex);
			return;
		}
		// NHẬP Số bản ghi mỗi trang
		int perPage;
		// TÍNH Số trang theo perPage
		int totalPages;
		// offset
		int offset;
		// strQuery
		String strQuery;
		// Nhập trang muốn xem
		int pageNumber;
		Product product;
		if (total == 0) {
			System.out.println("Không có dữ liệu!");
		} else {
			System.out.println("Bạn muốn xem bao nhiêu sản phẩm / mỗi trang?");
			perPage = ScannerUtilities.getInt();
			if (perPage == 0) {
				System.err.println("0 sản phẩm mỗi trang!");
				System.err.println("...quay lại");
				return;
			}
			if (total % perPage == 0) {
				totalPages = total / perPage;

			} else {
				totalPages = total / perPage + 1;
			}
			// Hiển thị tổng số trang
			System.out.printf("Bạn có [%d] trang để hiển thị (mỗi trang [%d] sản phẩm)\n", totalPages, perPage);
			boolean continueBoolean = true;
			while (continueBoolean) {
				System.out.print("Bạn muốn xem trang mấy? ");
				pageNumber = ScannerUtilities.getInt(1, totalPages);
				offset = (pageNumber - 1) * perPage;
				ResultSet results = ProducstModel.pagination(offset, perPage);

				while (results.next()) {
					product = ProducstModel.getProductFromResultSet(results);
					ProductsViews.printProduct(product);
				}
				results.first();
				continueBoolean = ProductsViews.continueBoolean();
			}
		}
	}

	//ham xoa products
	public static void productsDelete() {
		ResultSet rs;
		Product product = new Product();
		int count = 0;
		boolean continueBoolean = true;
		int option = 0;
		while (continueBoolean) {
			ProductsViews.searchOption();
			while (continueBoolean) {
				option = ScannerUtilities.getInt();
				if (option < 1 || option > 3) {
					System.out.println("Vui long nhap trong 1,2,3");
				} else {
					break;
				}
			}
			System.out.print("Nhap vao tu khoa muon tim kiem de xoa : ");
			String keywordName = ScannerUtilities.getString(3);
			rs = ProducstModel.searchProduct(keywordName, option);
			try {
				while (rs.next()) {
					product.setBarCode(rs.getString("barCode"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setQuantity(rs.getInt("quantity"));
					product.setPrice(rs.getFloat("price"));
					product.setDiscount(rs.getFloat("discount"));
					product.setCategoryId(rs.getInt("category_id"));
					ProductsViews.printProduct(product);
					++count;
				}
			} catch (SQLException ex) {
				System.err.println("Co loi xay ra! " + ex);
			}
			if (count == 0) {
				System.out.println("Khong tim thay san pham!");
			} else {
				System.out.println("Tim thay " + count + " san pham.");
				continueBoolean = ProductsViews.agreementBoolean();
				if (continueBoolean) {
					ProducstModel.productsDelete(keywordName, option);
				}
			}
			continueBoolean = ProductsViews.continueBoolean();
		}
	}
}