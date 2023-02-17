package algoritmos;

import java.util.ArrayList;
import java.util.List;

import grafo.Contactos;
import grafo.Espias_Lista_Ad;

public class Prim {
	
	private static Espias_Lista_Ad ret;
	private static List<Contactos> E;
	private static List<Integer> V;

	public static Espias_Lista_Ad arbolGeneradorMinimo(Espias_Lista_Ad g) {
		excepciones(g);
		if (g.getTamanio() <= 0) {
			return g;
		}
		
		V = new ArrayList<Integer>();
		V.add(0);
		E = new ArrayList<Contactos>();
		
		int i = 0;
		while (i < g.getTamanio() - 1) {
			aristaMinimaEntreLimites(g);
			
			i++;
		}
		
		ret = new Espias_Lista_Ad(V.size(), E);
		copiarNombres(g);
		
		return ret;
		
	}
	
	private static void copiarNombres(Espias_Lista_Ad g) {
		for (int i = 0; i < g.getTamanio(); i++) {
			ret.agregarNombreAlEspia(i, g.getNombreDelEspia(i));
		}
	}
	
	
	public static void aristaMinimaEntreLimites(Espias_Lista_Ad g) {
		Contactos aristaGuardada = null;
		
		for (Integer verticeGuardado : V) {
			for (Integer verticeNoGuardado: g.vecinos(verticeGuardado)) {
				if (!V.contains(verticeNoGuardado)) {
					if (aristaGuardada == null || aristaGuardada.compareTo(g.aristaYVertices(verticeGuardado, verticeNoGuardado)) > 0) {
						
						aristaGuardada = g.aristaYVertices(verticeGuardado, verticeNoGuardado);
						
					}
				}
				
			}
		}
		
		V.add(aristaGuardada.getVerticeJ());
		E.add(aristaGuardada);
		
	}

	private static void excepciones(Espias_Lista_Ad g) {
		if (g == null) {
			throw new IllegalArgumentException("No ingresaste ningun espia!");
		}
		if (!BFS.esConexo(g)) {
			throw new IllegalArgumentException("Todos los espias deben estar unidos a por lo menos un espia!");
		}
		if (!g.tienenNombreTodos()) {
			throw new IllegalArgumentException("Todos los espias deben tener nombre!");
		}
	}
	
	
	
	
	
	
}
