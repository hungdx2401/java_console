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
				System.err.println("Vui long nhap so! ");
			}
		}
		return inputInt;
	}

	/**
	 * Hàm này trả về số int được nhập từ người dùng nằm trong khoảng min, max
	 * @param min
	 * @param max
	 * @return 
	 */
	public static int getInt(int min, int max) {
		int inputInt = 0;
		while (true) {
			try {
				inputInt = new Scanner(System.in).nextInt();
				if ((inputInt <= max) && (inputInt >= min)) {
					break;
				} else {
					System.out.println("Vui lòng chỉ nhập số trong khoảng từ (" + min + ") đến (" + max + ")");
				}
			} catch (Exception e) {
				System.err.println("Vui lòng chỉ nhập số! ");
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
				System.err.printf("Vui long nhap lai toi thieu " + length + " ky tu! ");
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
				System.err.println("Vui long nhap dinh dang mot ki tu! ");
			} else {
				break;
			}
		}
		return input.charAt(0);
	}

	/**
	 * Hàm này trả về giá trị float được người dùng nhập vào
	 *
	 * @return
	 */
	public static float getFloat() {
		Float inputFloat = null;
		while (true) {
			try {
				inputFloat = new Scanner(System.in).nextFloat();
				break;
			} catch (Exception e) {
				System.err.println("Vui long nhap so thuc ");
			}
		}
		return inputFloat;
	}

	/**
	 * Hàm này bắt buộc người dùng phải chọn một trong các số truyền vào
	 *
	 * @param inputInt
	 * @return lựa chọn của người dùng
	 */
	public static int choiceInput(int... inputInt) {
		String str1 = "";
		String str2 = "";
		for (int i = 0; i < inputInt.length; i++) {
			if (i != 0) {
				str1 += "/";
			}
			str1 += Integer.toString(inputInt[i]);
			str2 += Integer.toString(inputInt[i]);
		}
		int choice;
		do {
			System.out.println("Vui lòng chọn (" + str1 + ") : ");
			choice = ScannerUtilities.getInt();
		} while (!(str2.contains(Integer.toString(choice).toCharArray()[0] + "")));

		return choice;
	}

} // END - class
