package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import controller.Extrato;

public class ExtratoTest {

	Extrato movBanc, copy;
	java.util.Date utilDate = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	@Before
	public void setUP() throws Exception {

		movBanc = new Extrato(1, 100.00, 1000.00, 2, 2, sqlDate);
		copy = new Extrato(1, 100.00, 1000.00, 2, 2, movBanc.getData());
	}

	@Test
	public void test00SalvaExtrato() {

		movBanc.salvaExtrato();
		copy.salvaExtrato();
		assertEquals("Teste movBanc igual copy", movBanc, copy);
	}

	@Test
	public void test02ConsultaExtrato() {

		Extrato fixture = new Extrato(1, 100.00, 1000.00, 2, 2, sqlDate);
		fixture.setDataInicial(sqlDate);
		fixture.setDataFinal(sqlDate);

		int last = fixture.consultaExtrato().size();

		Extrato novo = new Extrato();

		novo.setIdCliente(fixture.consultaExtrato().get(last - 1).getIdCliente());
		novo.setTipoCredDeb(fixture.consultaExtrato().get(last - 1).getTipoCredDeb());
		novo.setIdTipoMovimentacao(fixture.consultaExtrato().get(last - 1).getIdTipoMovimentacao());
		novo.setValorMovimentacao(fixture.consultaExtrato().get(last - 1).getValorMovimentacao());
		novo.setSaldoAtual(fixture.consultaExtrato().get(last - 1).getSaldoAtual());
		novo.setData((java.sql.Date) fixture.getData());
		novo.setDataFinal((java.sql.Date) fixture.getData());
		novo.setDataInicial((java.sql.Date) fixture.getData());

		assertEquals("Teste Carregar Primeiro Extrato", fixture, novo);
	}
	
	@Test
	public void test03ExtratoDias() {

		Extrato fixture = new Extrato(1, 100.00, 1000.00, 2, 2, sqlDate);
		fixture.extratoDias(15);

		int last = fixture.consultaExtrato().size();

		Extrato novo = new Extrato();

		novo.setIdCliente(fixture.consultaExtrato().get(last - 1).getIdCliente());
		novo.setTipoCredDeb(fixture.consultaExtrato().get(last - 1).getTipoCredDeb());
		novo.setIdTipoMovimentacao(fixture.consultaExtrato().get(last - 1).getIdTipoMovimentacao());
		novo.setValorMovimentacao(fixture.consultaExtrato().get(last - 1).getValorMovimentacao());
		novo.setSaldoAtual(fixture.consultaExtrato().get(last - 1).getSaldoAtual());
		novo.setData((java.sql.Date) fixture.getData());
		novo.setDataFinal((java.sql.Date) fixture.getDataFinal());
		novo.setDataInicial((java.sql.Date) fixture.getDataInicial());
		
		//System.out.println("Data Incial: " + (java.sql.Date) fixture.getDataInicial() + "\nDataFinal: " + (java.sql.Date) fixture.getDataFinal() );

		assertEquals("Teste Carregar Primeiro Extrato", fixture, novo);
	}{
		
	}
}
