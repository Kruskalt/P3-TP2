package algoritmos;

import java.util.ArrayList;
import java.util.Set;

import grafo.Contactos;
import grafo.Espias_matriz;

public class Kruskal {
	
	static private Espias_matriz arbolMinimo;
	static private ArrayList<Contactos> aristasGrafo;
	static private ArrayList<Contactos> aristasArbolMinimo;
	
	// Genera un AGM con BFS
	public static Espias_matriz arbolGeneradorMinimoBFS(Espias_matriz grafo){	
		esValido(grafo);
		
		arbolMinimo = new Espias_matriz(grafo.getTamanio());
		copiarNombres(grafo, arbolMinimo);
	    aristasGrafo = grafo.getTodasAristas();
	    aristasArbolMinimo = new ArrayList<Contactos>();
	    
	    
	    int i = 0;
		while ( i < aristasGrafo.size() ){ 
			Contactos aristaActual = aristasGrafo.get(i);
			Set<Integer> alcanzables = BFS.alcanzables(arbolMinimo, aristaActual.getVerticeI());
		        
		        // Si la aristaActual NO esta contenia dentro de aristasArbolMinimo
		        // Y si su vertice J NO esta contenido en los alcazables de su vertice I, es decir, no forma ciclo
		    if ( !aristasArbolMinimo.contains(aristaActual) && !alcanzables.contains(aristaActual.getVerticeJ()) ){
		    	aristasArbolMinimo.add(aristaActual);
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
