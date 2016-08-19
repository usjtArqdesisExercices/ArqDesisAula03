package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import dao.SaqueDAO;
import to.SaqueTO;

public class SaqueDAOTest {

	SaqueTO to;
	SaqueDAO dao;
	java.util.Date utilDate = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());	

	@Before
	public void setUp() throws Exception {

		dao = new SaqueDAO();
		to = new SaqueTO();
		to.setIdCliente(1);
		to.setSaldoAtual(300.00);
		to.setValorSaque(700.00);
		to.setData(sqlDate);

	}

	@Test
	public void test00EfetuarSaque() {

		dao.efetuarSaque(to);
		SaqueTO copy = dao.carregaUtilmoSaque(to.getIdCliente());
		copy.setData(to.getData());
		assertEquals("Teste", copy, to);
	}
	

	@Test
	public void test01CarreUltimoSaque(){
		//para funcionar o ULTIMO saque do cliente 1 deve ter sido carregado no banco por fora
		//INSERT INTO SAQUE (idSaque, CodCliente, ValorSaque, SaldoAtual, DataSaque) VALUES (1, 1, 500.00, 1000.00, '2016-08-12');
		//INSERT INTO SAQUE (idSaque, CodCliente, ValorSaque, SaldoAtual, DataSaque) VALUES (2, 1, 700.00, 300.00, '2016-08-16');
		SaqueTO fixture = new SaqueTO();
		fixture.setIdCliente(1);
		fixture.setSaldoAtual(300.00);
		fixture.setValorSaque(700.00);
		fixture.setData(to.getData());
		SaqueTO novo = dao.carregaUtilmoSaque(1);
		novo.setData(to.getData());
		assertEquals("Teste Carregar Ultimo Saque", novo, fixture);	
	}

}
