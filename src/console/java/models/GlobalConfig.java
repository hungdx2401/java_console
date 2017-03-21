/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.models;

/**
 *
 * @author tuana
 */
public class GlobalConfig {

    private static final String CONNECTION_URL_PREFIX = "jdbc:mysql://";
    private static final String HOST = "localhost:3306/";
    private static final String DATABASE = "online_shop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static final int NUMBER_ADMINS_PER_PAGE = 50;
    
    private static final String ADMINS_TABLE = "admins";

     public static String getADMINS_TABLE() {
	  return ADMINS_TABLE;
     }
    
    public static String getCONNECTION_URL_PREFIX() {
        return CONNECTION_URL_PREFIX;

    }
    public static String getHOST() {
        return HOST;
    }

    public static String getDATABASE() {
        return DATABASE;
    }

    public static String getUSERNAME() {
        return USERNAME;

    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

     public static int getNUMBER_ADMINS_PER_PAGE() {
	  return NUMBER_ADMINS_PER_PAGE;
     }
    

}
