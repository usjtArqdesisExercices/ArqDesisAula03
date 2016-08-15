package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import factory.ConnectionFactory;
import to.MovimentacaoBancariaTO;

public class MovimentacaoBancariaDAO {

	public MovimentacaoBancariaDAO() {

	}

	public void salvaDebito(MovimentacaoBancariaTO to) {

		String sqlInsert = "INSERT INTO MovimentacaoBancaria (CodCliente, TipoMovimentacao, Tipo, ValorMovimentacao, SaldoAtual, DataMovimentacao) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {

			stm.setInt(1, to.getIdCliente());
			stm.setInt(2, to.getTipoMovimentacao()); // 1 - Transferencia / 2 -
														// Saque / 3 - Debito
														// Auto
			stm.setInt(3, 2); // 1 - Credito / 2 - Debito
			stm.setDouble(4, to.getValorRetirado());
			stm.setDouble(5, to.getSaldoAtual());
			stm.setDate(6, to.getData());
			stm.executeUpdate();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
