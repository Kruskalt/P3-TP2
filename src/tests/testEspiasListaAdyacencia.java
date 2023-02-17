package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import algoritmos.BFS;
import grafo.Contactos;
import grafo.Espias_Lista_Ad;



public class testEspiasListaAdyacencia {
	
	Espias_Lista_Ad g;
	
	@Before 
	public void setUp() {
		
		g = new Espias_Lista_Ad(5);
		
	}
	
	@Test
	public void alcanzablesTest() {
		g.agregarArista(0, 1, 0.0);
		g.agregarArista(0, 2, 0.0);
		g.agregarArista(2, 3, 0.0);
		
		Set<Integer> alcanzables = BFS.alcanzables(g, 0);
		int[] esperados = {0, 1, 2, 3};
		
		Assert.iguales(esperados, alcanzables);
	}
	
	@Test
	public void esConexoTest() {
		g.agregarArista(0, 1, 0.0);
		g.agregarArista(1, 2, 0.0);
		g.agregarArista(2, 3, 0.0);
		g.agregarArista(3, 4, 0.0);
		
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void noEsConexoTest() {
		g.agregarArista(0, 1, 0.0);
		g.agregarArista(1, 2, 0.0);
		g.agregarArista(2, 3, 0.0);
		
		assertFalse(BFS.esConexo(g));
	}

	@Test
	public void testImplementarAristas() {
		g.agregarArista(0, 1, 1.0);
		
		assertTrue(g.existeArista(0, 1));
		assertTrue(g.existeArista(1, 0));
	}
	
	@Test
	public void testTamanioArista() {
		g.agregarArista(0, 1, 1.0);
		
		assertEquals(1,g.getProbabilidadDeEncuentro(0, 1), 1);
		assertEquals(1,g.getProbabilidadDeEncuentro(1, 0), 1);
	}
	
	
	@Test
	public void testAristaCompleta() {
		g.agregarArista(0, 1, 1.0);
		
		assertEquals(new Contactos(0, 1, 1), g.aristaYVertices(0, 1));
		assertEquals(new Contactos(1, 0, 1), g.aristaYVertices(1, 0));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testExceptionNoExisteArista1() {
		
		g.aristaYVertices(0, 1);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testExceptionNoExisteArista() {
		
		g.getProbabilidadDeEncuentro(0, 1);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testExceptionCiclo() {
		g.agregarArista(0, 0, 1.0);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testExceptionVerticeExcedido() {
		g.agregarArista(0, 7, 1.0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testTamanioAristaExcedido() {
		g.agregarArista(0, 1, 2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testTamanioAristaNegativo() {
		g.agregarArista(0, 1, -1);
	}

}
