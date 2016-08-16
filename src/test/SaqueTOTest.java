package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import to.SaqueTO;

public class SaqueTOTest {
	
	SaqueTO to;
	java.util.Date utilDate = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	
	
	@Before
	public void setUp() throws Exception {
		
		to =  new SaqueTO();
		to.setIdCliente(10);
		to.setSaldoAtual(600.00);
		to.setValorSaque(100.00);
		to.setData(sqlDate);
	}
	
	@Test
	public void test01Get() {
		assertEquals("getIdCliente", to.getIdCliente(), 10);
		assertEquals(to.getSaldoAtual(), 600.00, 0);
		assertEquals(to.getValorSaque(), 100.00, 0);
		assertEquals("getData", to.getData(), sqlDate);
	}
	
	@Test
	public void test02Equals() {
		
		SaqueTO copy = new SaqueTO();
		copy.setIdCliente(to.getIdCliente());
		copy.setSaldoAtual(to.getSaldoAtual());
		copy.setValorSaque(to.getValorSaque());
		copy.setData(to.getData());
		
		assertEquals("teste to igual a copia", to, copy);
	
		
	}
	

}
