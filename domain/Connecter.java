package domain;

import java.sql.*;


public class Connecter {
Connection con;
public Connecter() {
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	catch(ClassNotFoundException e) {
		System.err.println(e);
	}
	try {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oresa","root","");
	}
	catch(SQLException es) {
		System.err.println(es);
	}
}
public Connection recupCon() {
	return con;
}
}
