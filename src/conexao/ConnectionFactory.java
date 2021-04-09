package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/loja", "root","root123");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Problema de Conexão!!!!! " + ex.toString());
		}
		return null;
	}
}
