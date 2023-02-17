package grafo;




import java.util.Objects;

public class Contactos implements Comparable<Contactos>
{
	
    private int i;
    private int j;
    private double probabilidad;
    
    // Constructor
    public Contactos(int i, int j, double probabilidad)
    {
        this.i = i;
        this.j = j;
        this.probabilidad = probabilidad;
    }
    
    
    public boolean esLaMisma(int origen, int destino) {
		return origen == this.i && destino == this.j;
	}
    
    // Returna el vertice i
	public int getVerticeI()
	{
        return this.i;
    }
    
	
	// Returna el vertice j
    public int getVerticeJ() {
        return this.j;
    }
    
    
    // Returna el peso
    public double getPeso()
    {
        return this.probabilidad;
    }
	
    @Override
	public int hashCode() {
		return Objects.hash(i, j, probabilidad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contactos other = (Contactos) obj;
		return i == other.i && j == other.j
				&& Double.doubleToLongBits(probabilidad) == Double.doubleToLongBits(other.probabilidad);
	}


	@Override
    public int compareTo(Contactos o)
    {
        if (this.probabilidad < o.probabilidad) {
            return -1;
        }
        if (this.probabilidad > o.probabilidad) {
            return 1;
        }
        return 0;
    }
    
    
    @Override
	public String toString()
    {
		StringBuilder builder = new StringBuilder();
		builder.append("Espia i: ");
		builder.append(i);
		builder.append(" Espia j: ");
		builder.append(j);
		builder.append(" peso: ");
		builder.append(probabilidad);
		builder.append("\n");
		return builder.toString();
	}
    
}
