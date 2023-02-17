package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import grafo.Espias_matriz;

public class Grafo_test
{
	
	@Test(expected = IllegalArgumentException.class)
	public void verticeNegativoTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.vecinos(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void verticeExcedidoTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.vecinos(5);
	}

	@Test
	public void todosAisladosTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		assertEquals(0, grafo.vecinos(2).size());
	}
	
	@Test
	public void verticeUniversalTest()
	{
		Espias_matriz grafo = new Espias_matriz(4);
		grafo.agregarArista(1, 0, 0.2);
		grafo.agregarArista(1, 2, 0.7);
		grafo.agregarArista(1, 3, 0.9);
		
		int[] esperado = {0, 2, 3};
		Assert.iguales(esperado, grafo.vecinos(1));
	}
	
	@Test
	public void verticeNormalTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.agregarArista(1, 3, 0.6);
		grafo.agregarArista(2, 3, 0.3);
		grafo.agregarArista(2, 4, 0.2);
		
		int[] esperados = {1, 2};
		Assert.iguales(esperados, grafo.vecinos(3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeNegativoTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.agregarArista(-1, 3, 0.2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeExcedidoTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.agregarArista(5, 2, 0.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.agregarArista(2, -1, 0.5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeExcedidoTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.agregarArista(2, 5, 0.8);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.agregarArista(2, 2, 0.5);
	}

	@Test
	public void aristaExistenteTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.agregarArista(2, 3, 0.2);
		assertTrue( grafo.existeArista(2, 3) );
	}

	@Test
	public void aristaOpuestaTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.agregarArista(2, 3, 0.2);
		assertTrue( grafo.existeArista(3, 2) );
	}

	@Test
	public void aristaInexistenteTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.agregarArista(2, 3, 0.2);
		assertFalse( grafo.existeArista(1, 4) );
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaDosVecesTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.agregarArista(2, 3, 0.5);
		grafo.agregarArista(2, 3, 0.5);
	}
	
	@Test
	public void eliminarAristaExistenteTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.agregarArista(2, 4, 0.5);
		
		grafo.eliminarArista(2, 4);
		assertFalse( grafo.existeArista(2, 4) );
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarAristaInexistenteTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
		grafo.eliminarArista(2, 4);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
    public void aristaPesoIncorrectoTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
        grafo.agregarArista(2, 4, 0.5);
        grafo.agregarArista(4, 2, 0.5);
    }
	
	@Test
    public void aristaPesoCorrectoTest()
	{
		Espias_matriz grafo = new Espias_matriz(5);
        grafo.agregarArista(2, 4, 0.5);
        
        assertEquals(0.5,grafo.getProbabilidadDeEncuentro(2, 4), 0.5);
    }
	
}
