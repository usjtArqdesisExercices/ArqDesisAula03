package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import factory.ConnectionFactory;
import to.ExtratoTO;

public class ExtratoDAO {

	public ExtratoDAO() {

	}

	public void salvaDebito(ExtratoTO to) {

		String sqlInsert = "INSERT INTO MOVIMENTACAO_BANCARIA (CodCliente, IdTipoMovimentacao, TipoCredDeb, ValorMovimentacao, SaldoAtual, DataMovimentacao) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {

			stm.setInt(1, to.getIdCliente());
			stm.setInt(2, to.getIdTipoMovimentacao()); // 1 - Transferencia / 2 -
														// Saque / 3 - Debito
														// Auto
			stm.setInt(3, to.getTipoCredDeb()); // 1 - Credito / 2 - Debito
			stm.setDouble(4, to.getValorMovimentacao());
			stm.setDouble(5, to.getSaldoAtual());
			stm.setDate(6, to.getData());
			stm.executeUpdate();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<ExtratoTO> consultaExtrato(ExtratoTO to) {

		ArrayList<ExtratoTO> extrato = new ArrayList<>();

		String sqlSelect = "SELECT * FROM MOVIMENTACAO_BANCARIA WHERE CodCliente = ? and DataMovimentacao >= ? and DataMovimentacao <= ?";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, to.getIdCliente());
			stm.setString(2, "2016-08-13");
			stm.setString(3, "2016-08-13");
			//stm.setDate(2, to.getDataInicial());
			//stm.setDate(3, to.getDataFinal());
			 

			try (ResultSet rs = stm.executeQuery();) {

				while (rs.next()) {

					to = new ExtratoTO();
					to.setIdCliente(rs.getInt("CodCliente"));
					to.setIdTipoMovimentacao(rs.getInt("IdTipoMovimentacao"));
					to.setTipoCredDeb(rs.getInt("TipoCredDeb"));
					to.setValorMovimentacao(rs.getDouble("ValorMovimentacao"));
					to.setSaldoAtual(rs.getDouble("SaldoAtual"));
					to.setData(rs.getDate("DataMovimentacao"));
					extrato.add(to);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return extrato;
	}

}
