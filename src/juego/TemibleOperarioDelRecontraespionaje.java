package juego;

import algoritmos.BFS;
import algoritmos.KruskalUF;
import algoritmos.Prim;
import algoritmos.recorrerEnOrden;
import grafo.Espias;
import grafo.Espias_Lista_Ad;
import grafo.Espias_matriz;

public class TemibleOperarioDelRecontraespionaje {
	private Espias redBase;
	private Espias redConArbolMinimo;
	
	private int cantEspias;
	
	public TemibleOperarioDelRecontraespionaje(int cantidadDeEspias) {
		
		excepcionesConstructor(cantidadDeEspias);
		cantEspias = cantidadDeEspias;
	}

	public void prepararKruskal() {
		redBase = new Espias_matriz(cantEspias);
	}
	
	public void prepararPrim() {
		redBase = new Espias_Lista_Ad(cantEspias);
	}
	
	public void agregarNombreAlEspia(int numeroEspia, String nombre) {
		redBase.agregarNombreAlEspia(numeroEspia, nombre);
	}
	
	public void agregarDistanciasEntreEspias(int Espia1, int Espia2, double riesgo) {
		redBase.agregarArista(Espia1, Espia2, riesgo);
	}
	
	public void generarArbolMinimo() {
		if (redBase.getClass().getSimpleName().equals("Espias_matriz")) {
			ejecutarKruskalUF();
		}
		else if (redBase.getClass().getSimpleName().equals("Espias_Lista_Ad")) {
			ejecutarPrim();
		}
		
	}
	
	
	
	private void ejecutarKruskalUF() {
		excepcionKruskal();
		redConArbolMinimo = KruskalUF.arbolGeneradorMinimoUF((Espias_matriz) redBase);
	}
	
	private void ejecutarPrim() {
		excepcionPrim();
		redConArbolMinimo = Prim.arbolGeneradorMinimo((Espias_Lista_Ad) redBase);
	}

	public String arbolMinimoToString() {
		excepcionesArbolMinimoToString();
		return redConArbolMinimo.toString();
		
	}

	public String arbolMinimoToStringEnOrden() {
		excepcionesToStringConArbolMinimo();
		
		return recorrerEnOrden.ordenDelRecorrido(redConArbolMinimo);
		
	}

	public String redBaseToString() {
		excepcionesToStringBase();
		
		return redBase.toString();
	}

	public boolean tienenTodosNombre() {
		return redBase.tienenNombreTodos();
	}
	
	public int tamanioDeRed() {
		return redBase.getTamanio();
	}
	
	public boolean esConexo() {
		return BFS.esConexo(redBase);
	}

	private void excepcionesArbolMinimoToString() {
		if (redBase == null) {
			throw new IllegalArgumentException("No se genero una red!");
		}
		if (redConArbolMinimo == null) {
			throw new IllegalArgumentException("No se genero el arbol minimo!");
		}
	
	}

	private void excepcionesToStringConArbolMinimo() {
		excepcionesToStringBase();
		if (redConArbolMinimo == null) {
			throw new IllegalArgumentException("El arbol minimo no fue creado!");
		}
	}

	private void excepcionesToStringBase() {
		if (redBase == null) {
			throw new IllegalArgumentException("El arbol base no fue creado!");
		}
	}

	private void excepcionPrim() {
		if (redBase == null) {
			throw new IllegalArgumentException("La red de espias debe contener espias!");
		}
		if (redBase.getClass().getSimpleName().equals("Espias_matriz")) {
			throw new IllegalArgumentException("El algoritmo de Prim no puede ser ejecutado en: "+redBase.getClass().getSimpleName());
		}
	}

	private void excepcionKruskal() {
		if (redBase == null) {
			throw new IllegalArgumentException("La red de espias debe contener espias!");
		}
		if (redBase.getClass().getSimpleName().equals("Espias_Lista_Ad")) {
			throw new IllegalArgumentException("El algoritmo de KruskalUF no puede ser ejecutado en: "+redBase.getClass().getSimpleName());
		}
	}

	private void excepcionesConstructor(int cantidadDeEspias) {
		if (cantidadDeEspias <= 0) {
			throw new IllegalArgumentException("La cantidad de espias no puede ser: "+ cantidadDeEspias);
		}
	}	
	

}
