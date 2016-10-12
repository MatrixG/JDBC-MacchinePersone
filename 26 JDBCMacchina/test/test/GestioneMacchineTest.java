package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Macchina;
import model.Persona;
import service.GestioneMacchine;

public class GestioneMacchineTest {
	
	private GestioneMacchine g = new GestioneMacchine();
	
	@Test
	public void aggiungiMacchinaTest() {
	
		Macchina m = new Macchina("Audi", "EH221JH");
		assertNotNull(m);
		
		Macchina out = g.aggiungiMacchina("Audi", "EH221JH");
		assertNotNull(out);
		
		assertEquals(m.getModello(), out.getModello());
		assertEquals(m.getTarga(), out.getTarga());
		 
		assertTrue(g.cancellaMacchina("EH221JH"));
		
	}
	
	@Test
	public void cercaMacchina(){
		
		Macchina m = g.aggiungiMacchina("Audi", "EH221JH");
		assertNotNull(m);
		
		Macchina out = g.cercaMacchina("EH221JH");
		assertNotNull(out);
		
		assertEquals(m.getModello(), out.getModello());
		assertEquals(m.getTarga(), out.getTarga());
		
		assertTrue(g.cancellaMacchina("EH221JH"));
	}
	
	@Test
	public void cancellaMacchinaTest(){
		
		Macchina m = g.aggiungiMacchina("Audi", "EH221JH");
		assertNotNull(m);
		
		assertTrue(g.cancellaMacchina("EH221JH"));
	}
	
	@Test
	public void modificaMacchinaTest(){
		
		Macchina m = g.aggiungiMacchina("Audi", "EH221JH");
		assertNotNull(m);
		
		m = g.modificaMacchina("EH221JH", "Audi A1");
		
		assertNotNull(m);
		assertEquals(m.getModello(), "Audi A1");
		
		assertTrue(g.cancellaMacchina("EH221JH"));
	}
	
	@Test
	public void aggiungiPersonaTest() {
	
		Persona p = new Persona("Alessandro", "Boaretto", "BRTLSN87T01C722D");
		assertNotNull(p);
		
		Persona out = g.aggiungiPersona("Alessandro", "Boaretto", "BRTLSN87T01C722D");
		assertNotNull(out);
		
		assertEquals(p.getNome(), out.getNome());
		assertEquals(p.getCognome(), out.getCognome());
		assertEquals(p.getCodF(), out.getCodF());
		
		assertTrue(g.cancellaPersona("BRTLSN87T01C722D"));
	}
	
	@Test
	public void cercaPersona(){
		
		Persona p = g.aggiungiPersona("Alessandro", "Boaretto", "BRTLSN87T01C722D");
		assertNotNull(p);
		
		Persona out = g.cercaPersona("BRTLSN87T01C722D");
		assertNotNull(out);
		
		assertEquals(p.getNome(), out.getNome());
		assertEquals(p.getCognome(), out.getCognome());
		assertEquals(p.getCodF(), out.getCodF());
		
		assertTrue(g.cancellaPersona("BRTLSN87T01C722D"));
	}
	
	@Test
	public void cancellaPersonaTest(){
		
		Persona p = g.aggiungiPersona("Alessandro", "Boaretto", "BRTLSN87T01C722D");
		assertNotNull(p);
		
		assertTrue(g.cancellaPersona("BRTLSN87T01C722D"));
	}
	
	@Test
	public void modificaPersonaTest(){
		
		Persona p = g.aggiungiPersona("Alessandro", "Boaretto", "BRTLSN87T01C722D");
		assertNotNull(p);
		
		p = g.modificaPersona("Alex", "Boa", "BRTLSN87T01C722D");
		
		assertEquals(p.getNome(), "Alex");
		assertEquals(p.getCognome(), "Boa");
		assertEquals(p.getCodF(), "BRTLSN87T01C722D");
		
		assertTrue(g.cancellaPersona("BRTLSN87T01C722D"));
	}
	
	@Test
	public void aggiungiMacchinaPersonaTest(){
		
		Persona p = g.aggiungiPersona("Alessandro", "Boaretto", "BRTLSN87T01C722D");
		assertNotNull(p);
		Macchina m = g.aggiungiMacchina("Audi", "EH221JH");
		assertNotNull(m);
		
		assertTrue(g.aggiungiMacchinaPersona(m.getTarga(), p.getCodF()));
		
		assertTrue(g.cancellaMacchina("EH221JH"));
		assertTrue(g.cancellaPersona("BRTLSN87T01C722D"));
	}
	
	@Test
	public void cancellaMacchinaPersonaTest(){
	
		Persona p = g.aggiungiPersona("Alessandro", "Boaretto", "BRTLSN87T01C722D");
		assertNotNull(p);
		Macchina m = g.aggiungiMacchina("Audi", "EH221JH");
		assertNotNull(m);
		
		assertTrue(g.aggiungiMacchinaPersona(m.getTarga(), p.getCodF()));
		
		
		assertTrue(g.cancellaMacchinaPersona(m.getTarga(), p.getCodF()));
		assertTrue(g.cancellaMacchina("EH221JH"));
		assertTrue(g.cancellaPersona("BRTLSN87T01C722D"));
	}
	
	@Test
	public void getTutteMacchinePerPersonaTest(){
		
		Persona p = g.aggiungiPersona("Alessandro", "Boaretto", "BRTLSN87T01C722D");
		assertNotNull(p);
		Macchina m = g.aggiungiMacchina("Audi", "EH221JH");
		assertNotNull(m);
		m = g.aggiungiMacchina("Ford", "FA197XY");
		assertNotNull(m);
		m = g.aggiungiMacchina("Fiat", "CW488GY");
		assertNotNull(m);
		
		//in stesura
		
	}
}
