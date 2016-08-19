package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import dao.ExtratoDAO;
import to.ExtratoTO;

public class ExtratoDAOTest {

	ExtratoTO to;
	ExtratoDAO dao;
	java.util.Date utilDate = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	@Before
	public void setUp() throws Exception {

		dao = new ExtratoDAO();
		to = new ExtratoTO();
		to.setIdCliente(1);
		to.setTipoCredDeb(2);
		to.setIdTipoMovimentacao(2);
		to.setValorMovimentacao(10.00);
		to.setSaldoAtual(200.00);
		to.setData(sqlDate);
		to.setDataInicial(sqlDate);
		to.setDataFinal(sqlDate);
	}

	@Test
	public void test01SalvaExtrato() {

		int last = dao.consultaExtrato(to).size();

		dao.salvaExtrato(to);
		ExtratoTO copy = dao.consultaExtrato(to).get(last);
		copy.setData(to.getData());
		copy.setDataInicial(to.getDataInicial());
		copy.setDataFinal(to.getDataFinal());
		assertEquals("Teste carregar ultimo extrato copara to com copy", to, copy);
	}

	@Test
	public void test02ConsultaExtrato() {

		ExtratoTO copy = new ExtratoTO();

		copy.setIdCliente(1);
		copy.setIdTipoMovimentacao(2);
		copy.setTipoCredDeb(2);
		copy.setValorMovimentacao(10.00);
		copy.setSaldoAtual(200.00);
		copy.setData(sqlDate);
		copy.setDataInicial(sqlDate);
		copy.setDataFinal(sqlDate);
		
		int last = dao.consultaExtrato(to).size();		

		ExtratoTO novo = new ExtratoTO();
		novo = dao.consultaExtrato(copy).get(last -1);

		copy.setDataInicial(novo.getDataInicial());
		copy.setDataFinal(novo.getDataFinal());
		novo.setData(copy.getData());

		/*
		 * System.out.println("idCliente: " + copy.getIdCliente() +
		 * " || idTipoMov: " + copy.getIdTipoMovimentacao() +
		 * " || TipoCredDeb: " + copy.getTipoCredDeb() +
		 * " || Valor Movimentacao: " + copy.getValorMovimentacao() +
		 * " || Saldo Atual: " + copy.getSaldoAtual() + " || Data: " +
		 * copy.getData() + " || DataIni: " + copy.getDataInicial() +
		 * " || DataFin: " + copy.getDataFinal());
		 * System.out.println("idCliente: " + novo.getIdCliente() +
		 * " || idTipoMov: " + novo.getIdTipoMovimentacao() +
		 * " || TipoCredDeb: " + novo.getTipoCredDeb() +
		 * " || Valor Movimentacao: " + novo.getValorMovimentacao() +
		 * " || Saldo Atual: " + novo.getSaldoAtual() + " || Data: " +
		 * novo.getData() + " || DataIni: " + novo.getDataInicial() +
		 * " || DataFin: " + novo.getDataFinal());
		 */

		assertEquals("Teste Carregar Primeiro Extrato", copy, novo);
	}

}
