package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.Saque;

public class SaqueTest {
	
	Saque saque, copy;	
	
	@Before
	public void setUp() throws Exception {

		saque = new Saque(3, 25.00, 1000.00);
		copy = new Saque(3, 25.00, 1000.00);
		copy.setData(saque.getData());
	}

	@Test
	public void testEfetuarSaque() {
		
		saque.createDate();
		copy.createDate();		
		saque.efetuarSaque();
		copy.efetuarSaque();
		assertEquals("Testa Saque igual copy", saque, copy);
	}
	
	

}
