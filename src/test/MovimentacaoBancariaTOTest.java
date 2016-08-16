package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import to.MovimentacaoBancariaTO;

public class MovimentacaoBancariaTOTest {
	
	MovimentacaoBancariaTO to;
	java.util.Date utilDate = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());	
	
	@Before
	public void setUp() throws Exception {
		
		to = new MovimentacaoBancariaTO();
		to.setIdCliente(3);
		to.setTipoMovimentacao(2);
		to.setValorMovimentcao(100.00);
		to.setSaldoAtual(1500.00);
		to.setData(sqlDate);
	}

	@Test
	public void test00Get() {
		assertEquals("getIdCliente", to.getIdCliente(), 3);
		assertEquals("getTipoMovimentacao", to.getTipoMovimentacao(), 2);
		assertEquals(to.getValorMovimentcao(), 100.00, 0);
		assertEquals(to.getSaldoAtual(), 1500.00, 0);
		assertEquals("getData", to.getData(), sqlDate);	
	}
	
	@Test
	public void test01Equals() {
		
		MovimentacaoBancariaTO copy = new MovimentacaoBancariaTO();
		copy.setIdCliente(to.getIdCliente());
		copy.setTipoMovimentacao(to.getTipoMovimentacao());
		copy.setValorMovimentcao(to.getValorMovimentcao());
		copy.setSaldoAtual(to.getSaldoAtual());
		copy.setData(to.getData());
		assertEquals("Teste Copy igual TO", to, copy);
		
		
	}
	
	
}
