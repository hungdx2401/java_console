/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.models;

/**
 *
 * @author DongHo
 */
public class SessionAmin {

	private static int id;
	private static String name;
	private static String email;

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		SessionAmin.id = id;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		SessionAmin.name = name;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		SessionAmin.email = email;
	}
}
