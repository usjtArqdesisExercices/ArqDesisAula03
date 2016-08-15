package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import factory.ConnectionFactory;
import to.ExtratoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtratoDAO {

	public ArrayList<ExtratoTO> consultaExtrato(ExtratoTO to) {

		ArrayList<ExtratoTO> extrato = new ArrayList<>();

		String sqlSelect = "SELECT * FROM MovimentacaoBancaria WHERE CodCliente = ? and DataMovimentacao >= ? and DataMovimentacao <= ?";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, to.getIdCliente());
			stm.setString(2, "2016-08-13");
			stm.setString(3, "2016-08-13");
			/*
			 * stm.setDate(2, to.getDataInicial()); stm.setDate(3,
			 * to.getDataFinal());
			 */

			try (ResultSet rs = stm.executeQuery();) {

				while (rs.next()) {

					to = new ExtratoTO();
					to.setIdCliente(rs.getInt("CodCliente"));
					to.setTipoMovimentacao(rs.getInt("TipoMovimentacao"));
					to.setTipo(rs.getInt("Tipo"));
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
