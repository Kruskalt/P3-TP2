package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algoritmos.KruskalUF;
import grafo.Espias_matriz;

public class testKruskalUF {

	
	Espias_matriz g;
	Espias_matriz f;
	Espias_matriz h;
	Espias_matriz j;
	Espias_matriz z;
	
	@Before
	public void setUp() {
		g = new Espias_matriz(2);
		f = new Espias_matriz(5);
		z = new Espias_matriz(5);
		h = new Espias_matriz(3);
		j = new Espias_matriz(3);
	}
	
	@Test
	public void testMinimo() {
		f.agregarArista(0, 1, 0.8);
		f.agregarArista(1, 2, 0.1);
		f.agregarArista(0, 2, 0.01);
		f.agregarArista(2, 4, 0.9);
		f.agregarArista(3, 0, 0.6);
		f.agregarArista(4, 3, 0.4);
		
		f.agregarNombreAlEspia(0, "0");
		f.agregarNombreAlEspia(1, "1");
		f.agregarNombreAlEspia(2, "2");
		f.agregarNombreAlEspia(3, "3");
		f.agregarNombreAlEspia(4, "4");
		
		z.agregarArista(0, 2, 0.01);
		z.agregarArista(2, 1, 0.1);
		z.agregarArista(0, 3, 0.6);
		z.agregarArista(3, 4, 0.4);
		
		z.agregarNombreAlEspia(0, "0");
		z.agregarNombreAlEspia(1, "1");
		z.agregarNombreAlEspia(2, "2");
		z.agregarNombreAlEspia(3, "3");
		z.agregarNombreAlEspia(4, "4");
		
		
		assertEquals(z, KruskalUF.arbolGeneradorMinimoUF(f));
	}
	
	@Test
	public void testNotMinimo() {
		f.agregarArista(0, 1, 0.8);
		f.agregarArista(1, 2, 0.1);
		f.agregarArista(0, 2, 0.01);
		f.agregarArista(2, 4, 0.9);
		f.agregarArista(3, 0, 0.6);
		f.agregarArista(4, 3, 0.4);
		
		f.agregarNombreAlEspia(0, "0");
		f.agregarNombreAlEspia(1, "1");
		f.agregarNombreAlEspia(2, "2");
		f.agregarNombreAlEspia(3, "3");
		f.agregarNombreAlEspia(4, "4");
		
		z.agregarArista(0, 2, 0.01);
		z.agregarArista(2, 1, 0.1);
		z.agregarArista(0, 3, 0.6);
		z.agregarArista(3, 4, 0.4);
		z.agregarArista(0, 4, 0.5);
		
		z.agregarNombreAlEspia(0, "0");
		z.agregarNombreAlEspia(1, "1");
		z.agregarNombreAlEspia(2, "2");
		z.agregarNombreAlEspia(3, "3");
		z.agregarNombreAlEspia(4, "8");
		
		
		assertNotEquals(z, KruskalUF.arbolGeneradorMinimoUF(f));
	}

	@Test
	public void testGrafoGeneradorMinimo() {
		h.agregarArista(0, 1, 0.1);
		h.agregarArista(1, 2, 0.3);
		
		h.agregarArista(0, 2, 0.6);
		
		h.agregarNombreAlEspia(0, "0");
		h.agregarNombreAlEspia(1, "1");
		h.agregarNombreAlEspia(2, "2");
		
		
		assertTrue(KruskalUF.arbolGeneradorMinimoUF(h).existeArista(0, 1));
		assertTrue(KruskalUF.arbolGeneradorMinimoUF(h).existeArista(1, 2));
		assertFalse(KruskalUF.arbolGeneradorMinimoUF(h).existeArista(0, 2));
	}
	
	@Test 
	public void testGrafoGeneradorMinimo2() {
		f.agregarArista(0, 1, 0.1);
		f.agregarArista(1, 2, 0.3);
		f.agregarArista(2, 3, 0.1);
		f.agregarArista(3, 4, 0.2);
		f.agregarArista(4, 0, 0.3);
		f.agregarArista(4, 1, 0.1);
		f.agregarArista(4, 2, 1);
		
		f.agregarNombreAlEspia(0, "0");
		f.agregarNombreAlEspia(1, "1");
		f.agregarNombreAlEspia(2, "2");
		f.agregarNombreAlEspia(3, "3");
		f.agregarNombreAlEspia(4, "4");
		
		z.agregarArista(0, 1, 0.1);
		z.agregarArista(1, 4, 0.1);
		z.agregarArista(4, 3, 0.2);
		z.agregarArista(3, 2, 0.1);
		
		z.agregarNombreAlEspia(0, "0");
		z.agregarNombreAlEspia(1, "1");
		z.agregarNombreAlEspia(2, "2");
		z.agregarNombreAlEspia(3, "3");
		z.agregarNombreAlEspia(4, "4");
		
		
		
		assertEquals(z, KruskalUF.arbolGeneradorMinimoUF(f));
		
	}
	
	@Test
	public void testGrafoGeneradorMinimo3() {
		j.agregarArista(0, 1, 0);
		j.agregarArista(1, 2, 0);
		
		j.agregarNombreAlEspia(0, "0");
		j.agregarNombreAlEspia(1, "1");
		j.agregarNombreAlEspia(2, "2");
		
		assertEquals(j, KruskalUF.arbolGeneradorMinimoUF(j));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGrafoSinVertices() {
		assertEquals(g,KruskalUF.arbolGeneradorMinimoUF(g));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testGrafoNoEsConexo() {
		g.agregarArista(0, 1, 0.2);
		g.agregarArista(2, 3, 0.1);
		
		KruskalUF.arbolGeneradorMinimoUF(g);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGrafoSinNombres() {
		g.agregarArista(0, 1, 0.2);
		g.agregarArista(2, 3, 0.1);
		
		KruskalUF.arbolGeneradorMinimoUF(g);
	}
}
