package test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import controller.Extrato;
import controller.Saque;


public class SaqueTest {
	
	Saque saque, copy;
	java.util.Date utilDate = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	
	@Before
	public void setUp() throws Exception {

		saque = new Saque(3, 25.00, 1000.00);
		copy = new Saque(3, 25.00, 1000.00);
	}

	@Test
	public void test00EfetuarSaque() {
		
		saque.setData(sqlDate);
		saque.efetuarSaque();
		
		saque.carregaUtilmoSaque();
		
		Saque fixture = new Saque(); 
		fixture.setIdCliente(saque.getIdCliente());
		fixture.setValorSaque(saque.getValorSaque());
		fixture.setSaldoAtual(saque.getSaldoAtual());
		fixture.setData(saque.getData());
		
		assertEquals("Testa Saque igual fixture", saque, fixture);
	}
	
	@Test
	public void test01SalvaMovBanc() {
		
		Extrato extr = new Extrato(1, 100.00, 1500.00, 2, 2, sqlDate);
		extr.setDataInicial(extr.getData());
		extr.setDataFinal(extr.getData());
		extr.salvaExtrato();
		
		int last = extr.consultaExtrato().size();
		
		Extrato copy = new Extrato();
		
		copy.setIdCliente(extr.consultaExtrato().get(last - 1).getIdCliente());
		copy.setTipoCredDeb(extr.consultaExtrato().get(last - 1).getTipoCredDeb());
		copy.setIdTipoMovimentacao(extr.consultaExtrato().get(last - 1).getIdTipoMovimentacao());
		copy.setValorMovimentacao(extr.consultaExtrato().get(last - 1).getValorMovimentacao());
		copy.setSaldoAtual(extr.consultaExtrato().get(last - 1).getSaldoAtual());
		copy.setData((java.sql.Date) extr.getData());
		copy.setDataFinal((java.sql.Date) extr.getDataFinal());
		copy.setDataInicial((java.sql.Date) extr.getDataInicial());
		
		assertEquals("Teste compara copy com extr", copy, extr);
	}
	
	@Test
	public void test02CarregaUltimoSaque() {
		
		Saque copy = new Saque(3, 25.00, 1000.00);
		copy.setData(sqlDate);
		
		Saque fixture = new Saque();
		fixture.setIdCliente(3);
		fixture.carregaUtilmoSaque();
		
		
	}
	
	

}
