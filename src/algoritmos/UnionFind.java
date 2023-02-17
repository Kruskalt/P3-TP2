package algoritmos;

import java.util.ArrayList;

public class UnionFind {
	private int[]arreglo;
	
	//el grado de cada componente conexo
	private int[]grado;
	
	public UnionFind(int vertices) {
		grado=new int[vertices];
		arreglo= new int[vertices];
		for(int i=0; i<vertices;i++)
			arreglo[i]=i;
	}
	
	//para encontrar la raiz de un vertice
	public int root(int i) {
		ArrayList<Integer> nodosIntermedios = new ArrayList<Integer>();
		while(arreglo[i]!=i) {
			nodosIntermedios.add(i);
			i=arreglo[i];
			
			grado[i]++;
		}
		
			for(Integer nodo : nodosIntermedios)
				arreglo[nodo]=i;
		
		return i;
	}
	//determinar si estan en la misma componente conexa
	public boolean find(int i, int j) {
			return root(i)==root(j);
	}
	
	//unir dos componentes conexas
	public void union(int i, int j) {
		int ri= root(i);
		int rj=root(j);
		if(grado[ri]<grado[rj]) {
			arreglo[ri]=rj;
			
		}else {
			arreglo[rj]=ri;
			
		}
			
	}
	
	
	public int[] getArreglo() {
		return arreglo;
	}
	
}
