package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import factory.ConnectionFactory;
import to.SaqueTO;

public class SaqueDAO {

	public SaqueDAO() {
		

	}

	public SaqueTO efetuarSaque(SaqueTO to) {

		String sqlInsert = "INSERT INTO Saque (CodCliente, ValorSaque, SaldoAtual, DataSaque) VALUES (?, ?, ?, ?)";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm =conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, to.getIdCliente());
			stm.setDouble(2, to.getValorSaque());
			stm.setDouble(3, to.getSaldoAtual());
			stm.setDate(4, to.getData());
			stm.execute();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;		
	}
	
	public SaqueTO carregaUtilmoSaque(int idCliente) {
		
		SaqueTO to = new SaqueTO();
		
		String sqlSelect = "SELECT * FROM Saque where idSaque = (select max(idSaque) from SAQUE where CodCliente = ?)";
				
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			
			stm.setInt(1, idCliente);
			try (ResultSet rs = stm.executeQuery();) {
				if(rs.next()) {
					
					to.setIdCliente(rs.getInt("CodCliente"));
					to.setSaldoAtual(rs.getDouble("SaldoAtual"));
					to.setValorSaque(rs.getDouble("ValorSaque"));
					to.setData(rs.getDate("DataSaque"));
				}
			}
			
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return to;
	}

}
