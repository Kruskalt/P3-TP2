package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import algoritmos.BFS;
import grafo.Espias_Lista_Ad;



public class testBFS {

	@Test (expected = IllegalArgumentException.class)
	public void testNull() {
		Espias_Lista_Ad g = null;
		
		BFS.esConexo(g);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void vacioTest() {
		Espias_Lista_Ad g = new Espias_Lista_Ad(0);
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void alcanzablesTest() {
		Espias_Lista_Ad g = inicializarGrafo();
		
		Set<Integer> alcanzables = BFS.alcanzables(g, 0);
		int[] esperados = {0, 1, 2, 3};
		
		Assert.iguales(esperados, alcanzables);
	}
	
	@Test
	public void alcanzables() {
		Espias_Lista_Ad g = inicializarGrafo();
		
		List<Integer> alcanzables = BFS.alcanzablesList(g, 0);
		System.out.println(alcanzables.toString());
		
	}
	
	@Test
	public void conexoTest() {
		Espias_Lista_Ad g = inicializarGrafo();
		
		g.agregarArista(3,4, 0.0);
		
		assertTrue(BFS.esConexo(g));
		
	}
	
	@Test
	public void noConexoTest() {
		Espias_Lista_Ad g = inicializarGrafo();
		
		assertFalse(BFS.esConexo(g));
		
	}

	
	private Espias_Lista_Ad inicializarGrafo(){
		Espias_Lista_Ad grafo = new Espias_Lista_Ad(5);
		grafo.agregarArista(0, 1, 0.0);
		grafo.agregarArista(0, 2, 0.0);
		grafo.agregarArista(2, 3, 0.0);
		return grafo;
	}
}
