package dao;

import java.sql.Connection;
import java.sql.SQLException;

import visao.Visao;

import java.sql.DriverManager;

public class Conexao {
	public Connection getConnection() {
		Connection conector = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conector = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/covid19?useTimezone=true&serverTimezone=UTC", "root", "natanael");
		} catch (ClassNotFoundException e) {
			Visao.janelaErro("Falha na conexao: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			Visao.janelaErro("Sem conectar: " + e.getMessage());
		}
		return conector;
	}

}
