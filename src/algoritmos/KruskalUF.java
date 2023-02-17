package algoritmos;

import java.util.ArrayList;
import java.util.Collections;

import grafo.Contactos;
import grafo.Espias_matriz;


public class KruskalUF {
	
	static private Espias_matriz arbolMinimo;
	static private ArrayList<Contactos> aristasGrafo;
	static private UnionFind aristasArbolMinimo;
	
	// Genera un AGM con Union Find
	public static Espias_matriz arbolGeneradorMinimoUF(Espias_matriz grafo){	
		esValido(grafo);
		
		arbolMinimo = new Espias_matriz(grafo.getTamanio());
		copiarNombres(grafo, arbolMinimo);
	    aristasGrafo = grafo.getTodasAristas();
	    aristasArbolMinimo = new UnionFind(grafo.getTamanio());
	    
	    Collections.sort(aristasGrafo); // Ordena las aristas de la MENOS pesada a la MAS pesada
	    
	    int i = 0;
		while ( i < aristasGrafo.size() ){ 
			Contactos aristaActual = aristasGrafo.get(i);
		        
			// Si el vertice J NO esta contenido en la componente conexa del vertice I
			if ( !aristasArbolMinimo.find(aristaActual.getVerticeI(), aristaActual.getVerticeJ()) ){
				aristasArbolMinimo.union( aristaActual.getVerticeI(), aristaActual.getVerticeJ() );
				arbolMinimo.agregarArista( aristaActual.getVerticeI(), aristaActual.getVerticeJ(), aristaActual.getPeso() );
		    }
		    i++;
		}    
	    
	    return arbolMinimo;
	}
	
	private static void copiarNombres(Espias_matriz g, Espias_matriz nuevo) {
		for (int i = 0; i < g.getTamanio(); i++) {
			nuevo.agregarNombreAlEspia(i, g.getNombreDelEspia(i));
		}
	}
	
	// Verifica que el grafo sea valido
	private static void esValido(Espias_matriz grafo)
	{
		if (grafo == null) {
			throw new IllegalArgumentException("No ingresaste ningun espia!");
		}
		if (!BFS.esConexo(grafo)) {
			throw new IllegalArgumentException("Todos los espias deben estar unidos a por lo menos un espia!");
		}
		if (!grafo.tienenNombreTodos()) {
			throw new IllegalArgumentException("Todos los espias deben tener nombre!");
		}
	}

}
