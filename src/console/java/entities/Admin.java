/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.entities;

import console.java.models.GlobalConfig;
import console.java.models.Table;

/**
 *
 * @author Thang
 */
public class Admin implements ToArray, Table {

    private int id;
    private String name;
    private String email;
    private String pass;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return pass;
    }

    public void setPassword(String password) {
        this.pass = password;
    }

     @Override
     public String toString() {
	  return "Admin{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
     }

     @Override
     public String[] toArray() {
	  return new String[]{Integer.toString(id), name, email};
     }

     @Override
     public String getTable() {
	  return GlobalConfig.getADMINS_TABLE();
     }
    
}
