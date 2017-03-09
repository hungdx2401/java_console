/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.utilities;

/**
 *
 * @author DongHo
 */
public class StringUtilities {

     /**
      * Hàm này hạn chế một chuỗi quá dài để hiển thị bằng cách cắt nó thành một chuỗi con giới hạn số lượng ký tự để hiển thị
      * @param str
      * @param length
      * @return 
      */
     public static String truncate(String str, int length) {
	  if (str.length() > length) {
	       return str.substring(0, length);
	  } else {
	       return str;
	  }
     }
}
