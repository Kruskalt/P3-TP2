package algoritmos;

import java.util.HashSet;
import java.util.Set;

import grafo.Espias;

public class recorrerEnOrden extends BFS {
	
	private static StringBuilder ret;
	private static Set<Integer> yaAñadidos;
	
	
	public static String ordenDelRecorrido(Espias g) {
		excepcionesOrdenRecorrido(g);
		
		ret = new StringBuilder();
		yaAñadidos = new HashSet<Integer>();
		inicializar(g, 0);
		
		while (ListaPendientes.size() > 0) {
			int i = seleccionarYMarcarVertice(ret, g);
			agregarVecinosNoMarcados(g, ret, i);
			removerSeleccionado();
			
		}
		
		return ret.toString();
	}
	
	private static int seleccionarYMarcarVertice(StringBuilder ret, Espias g) {
		int i = ListaPendientes.get(0);
		marcados[i] = true;
		yaAñadidos.add(i);
		if (!yaAñadidos.containsAll(g.vecinos(i))) {
			ret.append("El espia ").append(g.getNombreDelEspia(i)).append(" se debe comunicar con: ").append("\n");
		}
		return i;
	}
	
	private static void agregarVecinosNoMarcados(Espias g, StringBuilder ret, int i) {
		for (int vecino: g.vecinos(i)) {
			if (!marcados[vecino] && !ListaPendientes.contains(vecino) && !yaAñadidos.contains(vecino)) {
				ListaPendientes.add(vecino);
				ret.append(g.getNombreDelEspia(vecino)).append(", con una probabilidad de encuentro de: ").append(g.getProbabilidadDeEncuentro(vecino, i)).append("\n");
			}		
		}
		ret.append("\n");
	}
	
	private static void excepcionesOrdenRecorrido(Espias g) {
		if (g == null) {
			throw new IllegalArgumentException("La red de espias debe tener algo!");
		}
		if (!g.tienenNombreTodos()){
			throw new IllegalArgumentException("Todos los espias deben tener nombre!");
		}
		
		if (!esConexo(g)) {
			throw new IllegalArgumentException("Todos los espias deben estar conectados!");
		}
	}
}
