package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import to.ExtratoTO;

public class ExtratoTOTest {
	
	ExtratoTO to;
	java.util.Date utilDate = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());	
	
	@Before
	public void setUp() throws Exception {
		
		to = new ExtratoTO();
		to.setIdCliente(3);
		to.setIdTipoMovimentacao(2);
		to.setTipoCredDeb(2);
		to.setValorMovimentacao(100.00);
		to.setSaldoAtual(1500.00);
		to.setData(sqlDate);
		to.setDataInicial(sqlDate);
		to.setDataFinal(sqlDate);		
	}

	@Test
	public void test00Get() {
		assertEquals("getIdCliente", to.getIdCliente(), 3);
		assertEquals("getTipoMovimentacao", to.getIdTipoMovimentacao(), 2);
		assertEquals("getTipoCredDeb", to.getTipoCredDeb(), 2);
		assertEquals(to.getValorMovimentacao(), 100.00, 0);
		assertEquals(to.getSaldoAtual(), 1500.00, 0);
		assertEquals("getData", to.getData(), sqlDate);
		assertEquals("getDataInicial", to.getDataInicial(), sqlDate);
		assertEquals("getDataFinal", to.getDataFinal(), sqlDate);
	}
	
	@Test
	public void test01Equals() {
		
		ExtratoTO copy = new ExtratoTO();
		copy.setIdCliente(to.getIdCliente());
		copy.setIdTipoMovimentacao(to.getIdTipoMovimentacao());
		copy.setTipoCredDeb(to.getTipoCredDeb());
		copy.setValorMovimentacao(to.getValorMovimentacao());
		copy.setSaldoAtual(to.getSaldoAtual());
		copy.setData(to.getData());
		copy.setDataInicial(to.getDataInicial());
		copy.setDataFinal(to.getDataFinal());
		assertEquals("Teste Copy igual TO", to, copy);
		
		
	}
	
	
}
