package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st =  null;
		
		try {
			conn = DB.getConnection();
			// METODO DE CONFIRMAÇÃO DE TRANSAÇÃO
			conn.setAutoCommit(false); // NAO VAI CONFIRMAR AUTOMATICAMENTE, FICARÃO PENDENTE APÓS AS EXECUÇÕES
			
			st = conn.createStatement();
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			// int x = 1;
			//if (x <2 ) {
				//throw new SQLException("Fake Error");
			//}
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 1");
			
			conn.commit();  // AQUI CONFIRMA AS OPERAÇÕES
			
			System.out.println("Rows1 = " + rows1);
		
			System.out.println("Rows2 = " + rows2);
			
		}
		catch (SQLException e) {
			try {
				conn.rollback(); // SE DER ERRO, SERÁ TRATADO COM UM ROLLBACK 
				throw new DbException("Transaction Rolled Back! Cause by: " + e.getMessage());
			} catch (SQLException e1) { //  TRATATIVA DE ERRO DE TENTATIVA DE ROLLBACK
				throw new DbException("Error trying to rollback! Cause by: " + e1.getMessage());
			}
		}
		finally {
			DB.closeConnection();
			DB.closeStatement(st);
		}

	}

}
