package grafo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
Representacion con Matriz de Adyacencia
*/
public class Espias_matriz extends Espias
{
	
	private Double[][] grafo;
	private ArrayList<Contactos> aristas = new ArrayList<Contactos>();
	
	
	// Constructor
	public Espias_matriz(int cantEspias)
	{
		super(cantEspias);
		grafo = new Double[cantEspias][cantEspias];
	}
	
	public void agregarNombreAlEspia(int i, String nombre) {
		verificarVertice(i);
		excepcionesAgregarNombre(i, nombre);
		
		
		nombresEspias[i] = nombre;
		
	}
	
	// Agregado de aristas
	public void agregarArista(int i, int j, double peso)
	{
		verificarPeso(peso);
		verificarAristaCreada(i, j, peso);	
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		aristas.add(new Contactos(i, j, peso));
		
		grafo[i][j] = peso;
		grafo[j][i] = peso;
	}

	// Eliminacion de aristas
	public void eliminarArista(int i, int j)
	{
		if( !this.existeArista(i, j) )
			throw new IllegalArgumentException("NO existe la arista (" + i + ", " + j + ")");
		
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		Contactos arista = new Contactos(i, j, grafo[i][j]);
		aristas.remove(arista);

		grafo[i][j] = null;
		grafo[j][i] = null;
	}

	
	// Cambia el peso de una arista
    public void cambiarPesoArista(int i, int j, Double peso)
    {
        grafo[i][j] = peso;
        grafo[j][i] = peso;
        
        eliminarArista(i, j);
        agregarArista(i, j, peso);
    }
    
    
	// Informa si existe la arista especificada
	public boolean existeArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		return grafo[i][j] != null;
	}

	
	// Cantidad de vertices
	public int getTamanio()
	{
		return grafo.length;
	}
	
	
	// Vecinos de un vertice
	public Set<Integer> vecinos(int i)
	{
		verificarVertice(i);
		
		Set<Integer> vecinos = new HashSet<Integer>();
		for(int j = 0; j < getTamanio(); ++j) if( i != j )
		{
			if( this.existeArista(i,j) )
				vecinos.add(j);
		}
		
		return vecinos;		
	}
	
	
	// Returna el peso de una arista
	public double getProbabilidadDeEncuentro(int i, int j)
	{
		excepcionExisteArista(i, j);
        return grafo[i][j];
    }
	
	
	// Returna todas las aristas del grafo	
	public ArrayList<Contactos> getTodasAristas()
	{
		// Se ordena antes para evitar errores en el equals de grafo
		// Esto se debe porque al paracer dos ArrayList son iguales solo si tienen los
		// mismos elmentos ordenados de la misma manera
		Collections.sort(aristas); // Ordena las aristas de la MENOS pesada a la MAS pesada
        return aristas;
    }
	
	@Override
	public String getNombreDelEspia(int i) {
		verificarVertice(i);
		
		return nombresEspias[i];
	}
	
	
	// Retorna el grafo completo
	public Double[][] getGrafo()
	{
        return grafo;
    }

	
	
	private void verificarPeso(double peso) {
		if (peso < 0 || peso > 1) {
			throw new IllegalArgumentException("Probabilidad equivocada, tiene que ser menor que 1 y mayor que 0: "+peso);
		}
		
	}

	private void verificarAristaCreada(int i, int j, double peso) {
		if (peso < 0) {
			throw new IllegalArgumentException("Peso invalido: "+ peso);
		}
		if( this.existeArista(i, j) )
			throw new IllegalArgumentException("Ya existe una conexion entre los espias "  + i + " - " + j);
	}

	// Verifica que sea un vertice valido
	private void verificarVertice(int i)
	{
		if( i < 0 || i >= getTamanio())
			throw new IllegalArgumentException("Los ID de los espias estan entre 0 y : " + (this.getTamanio() - 1) + ". El ID: " + i + " no existe!");
	}

	
	// Verifica que i y j sean distintos
	private void verificarDistintos(int i, int j)
	{
		if( i == j )
			throw new IllegalArgumentException("Un espia no puede tener una conexion consigo mismo! : (" + i + ", " + j + ")");
	}
	
	private void excepcionExisteArista(int origen, int destino) {
		if (!existeArista(origen, destino)) {
			throw new IllegalArgumentException("NO EXISTE LA ARISTA" + origen + " - " + destino);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.deepHashCode(grafo);
		result = prime * result + Objects.hash(aristas);
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
		Espias_matriz other = (Espias_matriz) obj;
		return aristasIguales(other.aristas) && Arrays.deepEquals(grafo, other.grafo);
	}
	
	private boolean aristasIguales(ArrayList<Contactos> c) {
		for (Contactos aristContactos : c) {
			if (!c.contains(aristContactos)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder bob = new StringBuilder();
		bob.append("\n");
		for (int i = 0; i < grafo.length; i++) {
			bob.append("El espia ").append(nombresEspias[i]).append(" se puede comunicar con: ").append("\n");
			for (Integer vecinos : vecinos(i)) {
				bob.append(nombresEspias[vecinos]).append(", con una probabilidad de encuentro con el enemigo del: ").append(getProbabilidadDeEncuentro(i, vecinos)).append(".").append("\n");
			}
			bob.append("\n");
		}
		return bob.toString();
	}



}
