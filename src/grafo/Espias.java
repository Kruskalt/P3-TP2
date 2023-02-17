package grafo;

import java.util.Arrays;
import java.util.Set;

public abstract class Espias {
	
	protected String [] nombresEspias;
	
	public Espias(int v) {
		excepcionesConstructor(v);
		nombresEspias = new String[v];
	}

	public abstract void agregarNombreAlEspia(int i, String nombre);
	
	public abstract void agregarArista(int i, int j, double peso);
	
	public abstract boolean existeArista(int i, int j);
	
	public abstract int getTamanio();
	
	public abstract Set<Integer> vecinos(int i);
	
	public abstract double getProbabilidadDeEncuentro(int i, int j);
	
	public abstract String getNombreDelEspia(int i);
	
	public abstract String toString();
	
	public void asignarCantidadEspias(int cantEspias) {
		excepcionesAsignarEspias(cantEspias);
		
		nombresEspias = new String [cantEspias];
	}

	public boolean tienenNombreTodos() {
		for (int i = 0; i < nombresEspias.length; i++) {
			if (nombresEspias[i] == null) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(nombresEspias);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Espias other = (Espias) obj;
		return Arrays.equals(nombresEspias, other.nombresEspias);
	}

	private void excepcionesConstructor(int v) {
		if (v <= 0) {
			throw new IllegalArgumentException("La cantidad de ninjas no puede ser: "+v);
		}
	}

	private void excepcionesAsignarEspias(int e) {
		if (e <= 0) {
			throw new IllegalArgumentException("Error: cantidad de espias no asignable: "+e);
		}
		if (nombresEspias != null) {
			throw new IllegalArgumentException("Error: la cantidad de espias ya fue asignada!");
		}
	}

	protected void excepcionesAgregarNombre(int i, String nombre) {
		if (nombre.equals("") || nombre == null) {
			throw new IllegalArgumentException("Debe agregar un nombre!");
		}
		if (nombresEspias[i] != null) {
			throw new IllegalArgumentException("El espia ya tiene nombre asignado: " + nombresEspias[i]);
		}
		if (tienenNombreTodos()) {
			throw new IllegalArgumentException("Ya se les asigno nombre a todos los ninjas!");
		}
	}
	
	
	

}
