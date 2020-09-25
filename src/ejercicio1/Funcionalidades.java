package ejercicio1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import excepciones.Excepciones;
/**
 * 
 * @author Curro Caro
 *
 */
public class Funcionalidades {

	static Excepciones ex = new Excepciones();
	static ArrayList<Empleado> ListaEmple = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);

	
	/**
	 * Metodo que carga el menu principal
	 * @throws IOException
	 */
	public static void Menuprincipal() throws IOException {

		leerArchivo();
		int opcion = 0;

		do {
			System.out.println("=======================================");
			System.out.println("========== Gestion de Personas ========");
			System.out.println("=======================================");
			System.out.println();
			System.out.println("1.-Aniadir una persona.");
			System.out.println("2.-Listar Personas.");
			System.out.println("3.-Borrar una persona.");
			System.out.println("4.-Guardar datos en fichero.");
			System.out.println("5.-Modificar Persona.");
			System.out.println();
			System.out.println("0.-Salir de la aplicacion.");
			System.out.println("=======================================");
			System.out.println("Introduzca la opcion elegida");

			opcion = ex.controlInt();
			switch (opcion) {
			case 1:
				aniadirEmple();
				break;
			case 2:
				leerEmpleado();
				break;
			case 3:
				BorrarEmpleado();
				break;
			case 4:
				guardarEmpleados();
				break;
			case 5:
				modEmple();
				break;
			case 0:
				System.out.println("Saliendo del Programa...");
				break;
			default:
				System.out.println("Por favor seleccione una opcion del 0-6");
				break;
			}

		} while (opcion != 0);
	}

	
	/**
	 * Metodo que nos permite crar un empleado
	 */
	public static void aniadirEmple() {

		boolean NumUsado = false;
		do {

			Empleado emple = new Empleado();
			System.out.println("Por Favor Introduzca el numero de Empleado");
			int numero = ex.controlInt();
			emple = ListaEmple.stream().filter(e -> e.getId() == numero).findFirst().orElse(new Empleado());
			if (numero == emple.getId()) {
				System.out.println("El numero de empleado esta en uso");
				NumUsado = true;
			} else {
				emple.setId(numero);
				System.out.println("Por Favor Introduzca el apellido del Empleado");
				String apellido = sc.nextLine();
				emple.setApellido(apellido);
				System.out.println("Por Favor Introduzca el Departamento del empleado");
				int departamento = ex.controlInt();
				emple.setDepartamento(departamento);
				System.out.println("Por Favor Introduzca el Salario del empleado");
				Double salario = ex.controlDouble();
				emple.setSalario(salario);
				ListaEmple.add(emple);
				NumUsado = false;
			}

		} while (NumUsado == true);

	}

	/**
	 * Metodo que nos permite imprimir por pantalla la lista de empleados
	 */
	public static void leerEmpleado() {
		if (ListaEmple.isEmpty()) {
			System.out.println("No existe ningun Empleado");
		} else {
			ListaEmple.stream().forEach(e -> System.out.println("\n<----------Empleados---------->" + e.toString()));
			System.out.println("");
			System.out.println("Se han mostrado " + ListaEmple.size() + " Empleados");
		}
	}

	/**
	 * Metodo que nos permite borrar un empleado
	 * @throws IOException
	 */
	public static void BorrarEmpleado() throws IOException {
		Empleado tempo = new Empleado();
		boolean comprobar = false;
		if (ListaEmple.isEmpty()) {
			System.out.println("No existe ningun Empleado");
		} else {
			leerEmpleado();
			System.out.println("Introduce el numero del Empleado que desees eliminar:");
			System.out.println("");
			do {
				int busqueda = ex.controlInt();
				tempo = ListaEmple.stream().filter(v -> v.getId() == busqueda).findFirst().orElse(new Empleado());
				if (tempo.getApellido() != null) {
					ListaEmple.remove(tempo);
					System.out.println("El Empleado fue eliminado con exito");
					System.out.println("");
					leerEmpleado();
					System.out.println("");
					comprobar = false;
				} else {
					System.out.println("No existe ningun empleado con esa ID. Pruebe otra vez:");
					comprobar = true;
				}

			} while (comprobar == true);
		}

	}

	/**
	 * Metodo que nos permite modificar el empleado que queramos de los
	 * que haya creado
	 * @throws IOException
	 */
	public static void modEmple() throws IOException {
		Empleado tempo = new Empleado();
		boolean comprobar = false;
		String apellidoTemp, nuevoApellido;
		int departamentoTemp, nuevoDepartamento;
		Double salarioTemp, nuevoSalario = 0.0;
		if (ListaEmple.isEmpty()) {
			System.out.println("No existe ningun Empleado");
		} else {
			leerEmpleado();
			System.out.println("Introduce el numero del empleado que desees Modificar:");
			System.out.println("");

			do {
				int busqueda = ex.controlInt();
				tempo = ListaEmple.stream().filter(v -> v.getId() == busqueda).findFirst().orElse(new Empleado());
				if (tempo.getApellido() != null) {
					apellidoTemp = tempo.getApellido();
					departamentoTemp = tempo.getDepartamento();
					salarioTemp = tempo.getSalario();
					comprobar = false;
				} else {
					System.out.println("No existe ningun Empleado con ese número. Pruebe otra vez:");
					comprobar = true;
				}
			} while (comprobar == true);

			System.out.println("Introduce el Nuevo apellido del Empleado");
			nuevoApellido = sc.nextLine();
			System.out.println("Introduce el nuevo departamento del Empleado");
			nuevoDepartamento = ex.controlInt();
			System.out.println("Introduce el nuevo salario del Empleado");
			nuevoSalario = ex.controlDouble();
			System.out.println("¡Atencion! Esta modificando el Empleado " + tempo.getId() + " con Apellido "
					+ tempo.getApellido() + "\n" + "y Departamento " + tempo.getDepartamento() + " y un Salario de "
					+ tempo.getSalario() + " Euros que \nahora tendra el Apellido " + nuevoApellido + " , Departamento "
					+ nuevoDepartamento + " \nY Salario de " + nuevoSalario + " Euros\n"
					+ "¿Seguro que deseea modificar este departamento (S/N)? ");

			String confirmar_accion = ex.textosinnumeros(sc.nextLine());
			if (confirmar_accion.equalsIgnoreCase("S")) {
				tempo.setApellido(nuevoApellido);
				tempo.setDepartamento(nuevoDepartamento);
				tempo.setSalario(nuevoSalario);
				System.out.println("El Empleado ha sido Modificado correctamente.");
			} else if (confirmar_accion.equalsIgnoreCase("N")) {
				System.out.println("El Empleado no ha sido modificado.");
			} else {
				System.out.println("Introduzca la opcion correcta");
			}
		}

	}

	/**
	 * Metodo que nos permite guardar los datos  en el archivo Aleatorio.dat
	 * @throws IOException
	 */
	public static void guardarEmpleados() throws IOException {

		RandomAccessFile file = new RandomAccessFile("AleatorioEmple.dat", "rw");
		StringBuffer buffer = null;
		for (Empleado empleado : ListaEmple) {
			file.writeInt(empleado.getId());
			buffer = new StringBuffer(empleado.getApellido());
			buffer.setLength(10);
			file.writeChars(buffer.toString());
			file.writeInt(empleado.getDepartamento());
			file.writeDouble(empleado.getSalario());

		}
		System.out.println("Datos Guardados con Exito");
		file.close();

	}

	/**
	 * Método que permite leer el archivo en la que se guardan los datos
	 * @throws IOException
	 */
	public static void leerArchivo() throws IOException {

		if (ListaEmple.isEmpty()) {
			RandomAccessFile file = new RandomAccessFile("AleatorioEmple.dat", "r");
			char apellido[] = new char[10], aux;
			int id, dep, posicion = 0;
			Double salario;

			for (;;) {
				file.seek(posicion);
				id = file.readInt();

				for (int i = 0; i < apellido.length; i++) {
					aux = file.readChar();
					apellido[i] = aux;
				}
				String apellidos = new String(apellido);
				dep = file.readInt();
				salario = file.readDouble();
				Empleado temp = new Empleado(id, apellidos, dep, salario);
				ListaEmple.add(temp);
				posicion = posicion + 36;
				if (file.getFilePointer() == file.length())
					break;
			}
			file.close();
			System.out.println("Datos cargados con exito");
		} else {
			System.out.println("");
		}

	}
	
	

}
