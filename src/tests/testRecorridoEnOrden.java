package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algoritmos.recorrerEnOrden;
import grafo.Espias;
import grafo.Espias_matriz;

public class testRecorridoEnOrden {
	
	private Espias g;
	
	@Before 
	public void setUp(){
		g = new Espias_matriz(3);
	}
	
	
	@Test
	public void testStringIguales() {
		g.agregarArista(0, 1, 1);
		g.agregarArista(1, 2, 1);
		
		g.agregarNombreAlEspia(0, "0");
		g.agregarNombreAlEspia(1, "1");
		g.agregarNombreAlEspia(2, "2");
		
		String expected = "El espia 0 se debe comunicar con: 1, con una probabilidad de encuentro de: 1.0El espia 1 se debe comunicar con: 2, con una probabilidad de encuentro de: 1.0";
		
		
		assertEquals(expected, recorrerEnOrden.ordenDelRecorrido(g).replace("\n", ""));
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testExcepcionesNoConexo() {
		g.agregarArista(0, 1, 0.1);
		
		recorrerEnOrden.ordenDelRecorrido(g);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testExcepcionesSinNombre() {
		
		recorrerEnOrden.ordenDelRecorrido(g);
				
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testExcepciones3Null() {
		g = null;
		
		recorrerEnOrden.ordenDelRecorrido(g);
	}

}
