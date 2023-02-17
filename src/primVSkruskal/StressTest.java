package primVSkruskal;

import java.text.DecimalFormat;
import java.util.Random;

import algoritmos.Kruskal;
import algoritmos.KruskalUF;
import algoritmos.Prim;
import grafo.Espias;
import grafo.Espias_Lista_Ad;
import grafo.Espias_matriz;

public class StressTest {
	
	
	public static String stressTest(int v) {
		StringBuilder ret = new StringBuilder();
		
		double contador3= 0;
		for(int r=2 ; r<v; ++r) {
			long inicio3 = System.currentTimeMillis();
			
			Espias_matriz grafo3= aleatorio(r);
			KruskalUF.arbolGeneradorMinimoUF(grafo3);    //kruskal con UF
			
			long fin3 =System.currentTimeMillis();
			double tiempo3= (fin3-inicio3)/1000.0;
			contador3=contador3+tiempo3;
			
		}
		DecimalFormat df = new DecimalFormat("###.###");
		
		ret.append("\n");
		ret.append("Tiempo promedio Kruskal con UnionFind: ");
		ret.append(df.format((double)(contador3/98))).append("\n");
		
		double contador1= 0;
		for(int n=2 ; n<v; ++n) {
			long inicio = System.currentTimeMillis();
			
			Espias_matriz grafo= aleatorio(n);
			Kruskal.arbolGeneradorMinimoBFS(grafo); //kruskal con bfs
			
			long fin =System.currentTimeMillis();
			double tiempo= (fin-inicio)/1000.0;
			contador1=contador1+tiempo;
			
			
		}
		DecimalFormat dr = new DecimalFormat("###.###");
		
		ret.append("Tiempo promedio Kruskal BFS directo: ");
		ret.append(dr.format((double)(contador1/98))).append("\n");
		
		double contador2=0;
			for(int i=2 ; i<v; ++i) {
				long inicio2 = System.currentTimeMillis();
				
				Espias_Lista_Ad grafo2= aleatorio2(i);
				
				Prim.arbolGeneradorMinimo(grafo2); // PRIM
				
				long fin2 =System.currentTimeMillis();
				double tiempo2= (fin2-inicio2)/1000.0;
				contador2=contador2+tiempo2;
			
					
					
			}
		DecimalFormat de = new DecimalFormat("###.###");
			
		ret.append("Tiempo promedio Prim: ");
		ret.append(de.format((double)(contador2/98))).append("\n");
			
		return ret.toString();
	}
	
	public static String stressTestKruskalUF(int v) {
		StringBuilder ret = new StringBuilder();
		
		double contador3= 0;
		for(int r=2 ; r<v; ++r) {
			long inicio3 = System.currentTimeMillis();
			
			Espias_matriz grafo3= aleatorio(r);
			KruskalUF.arbolGeneradorMinimoUF(grafo3);    //kruskal con UF
			
			long fin3 =System.currentTimeMillis();
			double tiempo3= (fin3-inicio3)/1000.0;
			contador3=contador3+tiempo3;
			
		}
		DecimalFormat df = new DecimalFormat("###.###");
		
		ret.append("\n");
		ret.append("Tiempo promedio Kruskal con UnionFind: ");
		ret.append(df.format((double)(contador3/98))).append("\n");
		
		return ret.toString();
	}
	
	public static String stressTestKruskalBFS(int v) {
		StringBuilder ret = new StringBuilder();
		
		double contador1= 0;
		for(int n=2 ; n<v; ++n) {
			long inicio = System.currentTimeMillis();
			
			Espias_matriz grafo= aleatorio(n);
			Kruskal.arbolGeneradorMinimoBFS(grafo); //kruskal con bfs
			
			long fin =System.currentTimeMillis();
			double tiempo= (fin-inicio)/1000.0;
			contador1=contador1+tiempo;
			
			
		}
		DecimalFormat dr = new DecimalFormat("###.###");
		
		ret.append("Tiempo promedio Kruskal BFS directo: ");
		ret.append(dr.format((double)(contador1/98))).append("\n");
			
		return ret.toString();
	}
	
	public static String stressTestPrim(int v) {
		StringBuilder ret = new StringBuilder();
		
		double contador2=0;
		for(int i=2 ; i<v; ++i) {
			long inicio2 = System.currentTimeMillis();
				
			Espias_Lista_Ad grafo2= aleatorio2(i);
				
			Prim.arbolGeneradorMinimo(grafo2); // PRIM
				
			long fin2 =System.currentTimeMillis();
			double tiempo2= (fin2-inicio2)/1000.0;
			contador2=contador2+tiempo2;
			}
			
		DecimalFormat de = new DecimalFormat("###.###");
			
		ret.append("Tiempo promedio Prim: ");
		ret.append(de.format((double)(contador2/98))).append("\n");
			
		return ret.toString();
	}
	
	private static Espias_matriz aleatorio(int n) {
		Espias_matriz grafo = new Espias_matriz(n) ;
		Random random = new Random(0);
		añadirNombres(grafo, n);
		for (int i = 0;i<n ;i++) if(random.nextDouble()<0.3)
			for (int j = 0;j<n ;j++)  
				if (i!=j && grafo.existeArista(i, j)==false)
					grafo.agregarArista(i, j, random.nextDouble());
		return grafo;	
		
	}
	
	
	private static Espias_Lista_Ad aleatorio2(int n) {
		Espias_Lista_Ad grafo = new Espias_Lista_Ad(n) ;
		Random random = new Random(0);
		añadirNombres(grafo, n);
		for (int i = 0;i<n ;i++) if(random.nextDouble()<0.3)
			for (int j = 0;j<n ;j++)  
				if (i!=j && grafo.existeArista(i, j)==false) 
					grafo.agregarArista(i, j, random.nextDouble());
		return grafo;	
		
	}
	
	private static void añadirNombres(Espias e, int n) {
		for (int v = 0; v < n; v++) {
			e.agregarNombreAlEspia(v, ""+v);
		}
	}
		
}
