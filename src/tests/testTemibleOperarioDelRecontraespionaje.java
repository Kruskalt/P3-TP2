package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import juego.TemibleOperarioDelRecontraespionaje;

public class testTemibleOperarioDelRecontraespionaje {
	 TemibleOperarioDelRecontraespionaje juego;
	@Before
	public void setup() {
		juego = new TemibleOperarioDelRecontraespionaje(4);
		juego.prepararPrim();
		
	}
	
	//tests agregarnombres a los espias
	@Test(expected = IllegalArgumentException.class)
	public void	nombreESPIANOESTA() {
		juego.agregarNombreAlEspia(4, "naza");
	}
	@Test(expected = IllegalArgumentException.class)
	public void	elespiaYAtienenombre() {
		juego.agregarNombreAlEspia(0, "naza");
		juego.agregarNombreAlEspia(0, "naza");
	}
	
	//tests agregar distancia entre espias
	@Test(expected = IllegalArgumentException.class)
	public void	aristaConverticeNoexistente() {
		juego.agregarDistanciasEntreEspias(0, 5, 0.4);		
	}

	@Test(expected = IllegalArgumentException.class)
	public void	aristaConPesoIlegal() {
		juego.agregarDistanciasEntreEspias(0, 3, 2);		
	}
	@Test(expected = IllegalArgumentException.class)
	public void	aristaloop() {
		juego.agregarDistanciasEntreEspias(0, 0, 0.4);		
	}
	@Test(expected = IllegalArgumentException.class)
	public void	aristaExistente() {
		juego.agregarDistanciasEntreEspias(0, 1, 0.4);		
		juego.agregarDistanciasEntreEspias(0, 1, 0.4);		
	}
	
	
	//tests generar arbol minimo
	
	@Test
	public void	generarArbolminimo() {
		juego.agregarNombreAlEspia(0, "0");
		juego.agregarNombreAlEspia(1, "1");
		juego.agregarNombreAlEspia(2, "2");
		juego.agregarNombreAlEspia(3, "3");
		juego.agregarDistanciasEntreEspias(0, 1, 0.4);		
		juego.agregarDistanciasEntreEspias(0, 2, 0.2);		
		juego.agregarDistanciasEntreEspias(1,3 , 0.3);
		juego.agregarDistanciasEntreEspias(3, 2, 0.4);		
		juego.agregarDistanciasEntreEspias(1, 2, 0.5);		
		
		juego.generarArbolMinimo();
		System.out.println("---test temible operario del recontra espionaje---");
		System.out.println(juego.arbolMinimoToString());
		
	}
	@Test
	public void	generarArbolminimoConKruskal() {
		juego.prepararKruskal();
		juego.agregarNombreAlEspia(0, "0");
		juego.agregarNombreAlEspia(1, "1");
		juego.agregarNombreAlEspia(2, "2");
		juego.agregarNombreAlEspia(3, "3");
		juego.agregarDistanciasEntreEspias(0, 1, 0.4);		
		juego.agregarDistanciasEntreEspias(0, 2, 0.2);		
		juego.agregarDistanciasEntreEspias(1,3 , 0.3);
		juego.agregarDistanciasEntreEspias(3, 2, 0.4);		
		juego.agregarDistanciasEntreEspias(1, 2, 0.5);		
		
		juego.generarArbolMinimo();
		System.out.println("---test temible operario del recontra espionaje---");
		System.out.println(juego.arbolMinimoToString());
	}
	
	@Test
	public void	noestaConexo() {
		assertFalse(juego.esConexo());
	}
	@Test 
	public void	faltanNombres() {
		juego.agregarNombreAlEspia(0, "0");
		
		assertFalse(juego.tienenTodosNombre());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void	noSegeneroAgm() {
		juego.arbolMinimoToString();
		
		
	}
	@Test (expected = IllegalArgumentException.class)
	public void	noPuedoImprimirsinGrafo() {
		TemibleOperarioDelRecontraespionaje aux = new TemibleOperarioDelRecontraespionaje(3);
		aux.arbolMinimoToString();
		
		
		
	}
	
	
	
	@Test (expected = NullPointerException.class)
	public void	noSegenerografo() {
		TemibleOperarioDelRecontraespionaje aux = new TemibleOperarioDelRecontraespionaje(3);
		aux.generarArbolMinimo();
		
	}
	
	
	
	
		
	@Test(expected = IllegalArgumentException.class)
	public void crearRedSinEspias() {
		TemibleOperarioDelRecontraespionaje a = new TemibleOperarioDelRecontraespionaje(0);
		
	}
	
	
}
