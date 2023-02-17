package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import grafo.Espias;
import grafo.Espias_Lista_Ad;

public class testNombresEspiasLista {
	Espias m;
	
	@Before
	public void setUp() {
		m = new Espias_Lista_Ad(5);
	}
	
	@Test
	public void testAgregarNombreMatriz() {
		m.agregarNombreAlEspia(0, "pepe");
		
		assertEquals("pepe",m.getNombreDelEspia(0));
	}
	
	@Test 
	public void testTodosTienenNombre() {
		m.agregarNombreAlEspia(0, "pepe");
		m.agregarNombreAlEspia(1, "pepe");
		m.agregarNombreAlEspia(2, "pepe");
		m.agregarNombreAlEspia(3, "pepe");
		m.agregarNombreAlEspia(4, "pepe");
		
		assertTrue(m.tienenNombreTodos());
	}
	
	@Test 
	public void testNoTodosTienenNombre() {
		m.agregarNombreAlEspia(0, "pepe");
		m.agregarNombreAlEspia(1, "pepe");
		
		assertFalse(m.tienenNombreTodos());
	}
	
	@Test 
	public void testNingunoTienenNombre() {
		
		assertFalse(m.tienenNombreTodos());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAgregarNombreInvalido() {
		m.agregarNombreAlEspia(5, "");
	}
	@Test (expected = IllegalArgumentException.class)
	public void testAgregarNombreInvalido2() {
		m.agregarNombreAlEspia(5, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAgregarNombreVerticeInvalido() {
		m.agregarNombreAlEspia(5, "pepe");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAgregarNombreVerticeInvalido2() {
		m.agregarNombreAlEspia(-1, "pepe");
	}
}
