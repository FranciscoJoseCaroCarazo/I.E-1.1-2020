package ejercicio2;

/**
 * 
 * @author Francisco José Caro Carazo
 *
 */
public class Departamento implements java.io.Serializable{

	//Atributos
	private static final long serialVersionUID = -6002384013700764654L;
	private int numDepart;
	private String nombre, localidad;
	
	
	//Constructores
	
	public Departamento() {
		
	}
	
	public Departamento(int numDepart, String nombre, String localidad) {
		this.numDepart = numDepart;
		this.nombre = nombre;
		this.localidad = localidad;
	}
	
	//Getters y Setters

	public int getNumDepart() {
		return numDepart;
	}

	public void setNumDepart(int numDepart) {
		this.numDepart = numDepart;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Override
	public String toString() {
		return "\nNúmero de Departamento:" + numDepart + "\nNombre: " + nombre + "\nLocalidad:" + localidad;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (numDepart != other.numDepart)
			return false;
		return true;
	}
	
	
}
