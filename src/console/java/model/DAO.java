/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tuana
 */
public class DAO {
    public static final String CONNECTION_URL_PREFIX = "jdbc:mysql://" ;
    public static final String DATABASE = "products";
    public static final String DATABASE_USER = "?user=root";
    public static final String DATABASE_PASSWORD = "";
    public static final String HOST_WITH_HOST = "//localhost:3306/";
    
    public static Connection getConnection()throws SQLException{
        String connectionString = CONNECTION_URL_PREFIX + HOST_WITH_HOST + DATABASE + DATABASE_USER + DATABASE_PASSWORD;
        Connection conn = DriverManager.getConnection(connectionString);
        return conn;
    
    }
    
    
}
