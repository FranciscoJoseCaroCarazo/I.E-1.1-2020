package ejercicio1;

public class Empleado {
	
	//Atributos
	String apellido ;
	int id, departamento;
	double salario; 
	
	//Contructor
	
	public Empleado() {
		
	}

	public Empleado(int id, String apellido, int departamento, double salario) {
		this.id = id;
		this.apellido = apellido;
		this.departamento = departamento;
		this.salario = salario;
	}

	//Getters y Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "\nNúmero de Empleado:" + id + "\nApellido: " + apellido + "\nDepartamento:" + departamento
				+ "\nSalario:" + salario + " €";
	}
	
}


