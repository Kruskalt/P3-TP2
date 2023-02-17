package grafo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Espias_Lista_Ad extends Espias {
	private int vertices;
	private LinkedList<Contactos> [] listaAdyacencia;

	@SuppressWarnings("unchecked")
	public Espias_Lista_Ad(int cantEspias) {
		super(cantEspias);
		this.vertices = cantEspias;
		listaAdyacencia = new LinkedList[this.vertices];
		
		for (int i = 0; i < listaAdyacencia.length; i++) {
			listaAdyacencia[i] = new LinkedList<Contactos>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Espias_Lista_Ad(int cantEspias, List<Contactos> aristas) {
		super(cantEspias);
		this.vertices = cantEspias;
		listaAdyacencia = new LinkedList[this.vertices];
		
		for (int i = 0; i < listaAdyacencia.length; i++) {
			listaAdyacencia[i] = new LinkedList<Contactos>();
		}
		
		for (Contactos arista : aristas) {
			this.agregarArista(arista.getVerticeI(), arista.getVerticeJ(), arista.getPeso());
		}
	}
	
	public void agregarNombreAlEspia(int i, String nombre) {
		verificarVertice(i);
		excepcionesAgregarNombre(i, nombre);
		
		
		nombresEspias[i] = nombre;
		
	}
	
	public void agregarArista(int origen, int destino, double peso) {
		
		verificarPeso(peso);
		verificarVertice(origen);
		verificarVertice(destino);
		verificarDistintos(origen, destino);
		verificarAristaCreada(origen, destino, peso);
		
		Contactos arista = new Contactos(origen, destino, peso);
		Contactos arista2 = new Contactos(destino, origen, peso);
		
		listaAdyacencia[origen].addFirst(arista);
		listaAdyacencia[destino].addFirst(arista2);
		
	}

	public boolean existeArista(int origen, int destino) {
		verificarVertice(origen);
		verificarVertice(destino);
		verificarDistintos(origen, destino);
		
		for (LinkedList<Contactos> linkedList : listaAdyacencia) {
			for (Contactos arista : linkedList) {
				if (arista.esLaMisma(origen, destino)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public Contactos aristaYVertices(int origen, int destino) {
		excepcionExisteArista(origen, destino);
		
		for (LinkedList<Contactos> linkedList : listaAdyacencia) {
			for (Contactos arista : linkedList) {
				if (arista.esLaMisma(origen, destino)) {
					return arista;
				}
			}
		}
		
		return null;
	}
	
	public double getProbabilidadDeEncuentro(int origen, int destino) {
		excepcionExisteArista(origen, destino);
		
		for (LinkedList<Contactos> linkedList : listaAdyacencia) {
			for (Contactos arista : linkedList) {
				if (arista.esLaMisma(origen, destino)) {
					return arista.getPeso();
				}
			}
		}
		
		return -1.0;
	}
	
	public Set<Integer> vecinos(int i) {
		verificarVertice(i);
		
		Set<Integer> vecinos = new HashSet<Integer>();
		
		for (Contactos arista : listaAdyacencia[i]) {
			vecinos.add(arista.getVerticeJ());
		}
		
		return vecinos;
	}
	
	public int getTamanio() {
		return vertices;
	}
	
	@Override
	public String getNombreDelEspia(int i) {
		verificarVertice(i);
		
		return nombresEspias[i];
	}
	
	public String toString() {
		StringBuilder bob = new StringBuilder();
		bob.append("\n");
		for (int i = 0; i < listaAdyacencia.length; i++) {
			bob.append("El espia ").append(nombresEspias[i]).append(" se puede comunicar con: ").append("\n");
			
			for (Integer vecinos : vecinos(i)) {
				bob.append(nombresEspias[vecinos]).append(", con una probabilidad de encuentro con el enemigo del: ").append(getProbabilidadDeEncuentro(i, vecinos)).append(".").append("\n");
			}
			bob.append("\n");
		}
		return bob.toString();
	}

	private void verificarPeso(double peso) {
		if (peso < 0 || peso > 1) {
			throw new IllegalArgumentException("Probabilidad equivocada, tiene que ser menor que 1 y mayor que 0: "+peso);
		}
	}

	private void verificarDistintos(int i, int j) {
		if (i == j)
			throw new IllegalArgumentException("Un espia no puede tener una conexion consigo mismo! : (" + i + ", " + j + ")");
	}
	
	
	
	
	private void excepcionExisteArista(int origen, int destino) {
		if (!existeArista(origen, destino)) {
			throw new IllegalArgumentException("NO EXISTE LA ARISTA" + origen + " - " + destino);
		}
	}

	// Verifica que sea un vertice valido
	private void verificarVertice(int i) {
		if (i < 0 || i >= listaAdyacencia.length)
			throw new IllegalArgumentException("Los ID de los espias estan entre 0 y : " + (this.getTamanio() - 1) + ". El ID: " + i + " no existe!");
	}

	private void verificarAristaCreada(int origen, int destino, double peso) {
		if (peso < 0) {
			throw new IllegalArgumentException("Peso invalido: "+ peso);
		}
		if (existeArista(origen, destino)) {
			throw new IllegalArgumentException("Ya existe una conexion entre los espias "  + origen + " - " + destino);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(listaAdyacencia);
		result = prime * result + Objects.hash(vertices);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Espias_Lista_Ad other = (Espias_Lista_Ad) obj;
		return Arrays.equals(listaAdyacencia, other.listaAdyacencia) && vertices == other.vertices;
	}



	

	


	
	
}
