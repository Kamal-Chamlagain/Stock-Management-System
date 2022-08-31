package bistaAgroFarm;

import java.sql.*;

import javax.swing.JOptionPane;

public class DbConnect {
	public static Connection con;
	public static Statement s;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "kamal@3");
			s = con.createStatement();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}
}
