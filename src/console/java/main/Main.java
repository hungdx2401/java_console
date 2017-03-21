/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.main;

import console.java.controllers.AdminsController;
import console.java.views.JAdminLogin;

/**
 *
 * @author DongHo
 */
public class Main {

     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) throws Exception {
//	  AdminsController.loginAdmin();
	  new JAdminLogin().setVisible(true);
     }
}
