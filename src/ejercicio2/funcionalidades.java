package ejercicio2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import excepciones.Excepciones;

public class funcionalidades {

	static Excepciones ex = new Excepciones();
	static ArrayList<Departamento> ListaDepart = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);

	/**
	 * Metodo que inicia el menu principal del programa
	 * 
	 * @throws IOException
	 */
	public static void Menuprincipal() throws IOException {

		cargarArchivo();
		int opcion = 0;

		do {
			System.out.println("============================================");
			System.out.println("========== Gestion de Departamentos ========");
			System.out.println("============================================");
			System.out.println();
			System.out.println("1.-Aniadir un Departamento.");
			System.out.println("2.-Listar Departamentos.");
			System.out.println("3.-Borrar un videojuego.");
			System.out.println("4.-Guardar datos en fichero.");
			System.out.println("5.-Recuperar datos desde fichero.");
			System.out.println("6.-Modificar Departamento.");
			System.out.println();
			System.out.println("0.-Salir de la aplicacion.");
			System.out.println("============================================");
			System.out.println("Introduzca la opcion elegida");

			opcion = ex.controlInt();
			switch (opcion) {
			case 1:
				aniadirDepartamento();
				break;
			case 2:
				leerDepartamento();
				break;
			case 3:
				BorrarDepartamento();
				break;
			case 4:
				guardarArchivo();
				break;
			case 5:
				recuperarDatos();
				break;
			case 6:
				modDepartamento();
				break;
			case 0:
				comprobarGuardado();
				break;
			default:
				System.out.println("Por favor seleccione una opcion del 0-5");
				break;
			}

		} while (opcion != 0);
	}

	/**
	 * Metodo que nos permite crear un nuevo departamento.
	 */
	public static void aniadirDepartamento() {

		boolean NumUsado = false;
		do {

			Departamento departamento = new Departamento();
			System.out.println("Por Favor Introduzca el numero de Departamento");
			int numero = ex.controlInt();
			departamento = ListaDepart.stream().filter(r -> r.getNumDepart() == numero).findFirst()
					.orElse(new Departamento());
			if (numero == departamento.getNumDepart()) {
				System.out.println("El numero de departamento esta en uso");
				NumUsado = true;
			} else {
				departamento.setNumDepart(numero);
				System.out.println("Por Favor Introduzca el nombre del Departamento");
				String nombre = sc.nextLine();
				departamento.setNombre(nombre);
				System.out.println("Por Favor Introduzca la localidad del Departamento");
				String localidad = sc.nextLine();
				departamento.setLocalidad(localidad);
				ListaDepart.add(departamento);
				NumUsado = false;
			}

		} while (NumUsado == true);

	}

	/**
	 * Metodo que nos permite imprimir por pantalla.
	 */
	public static void leerDepartamento() {
		if (ListaDepart.isEmpty()) {
			System.out.println("No existe ningun Departamento");
		} else {
			ListaDepart.stream().forEach(d -> System.out.println("\n<--------Departamentos-------->" + d.toString()));
			System.out.println("");
			System.out.println("Se han mostrado " + ListaDepart.size() + " Departamentos");
		}
	}

	/**
	 * Método que nos permite guardar las acciones que llevemos a cabo en nuestro
	 * programa en el archivo de guardado.
	 */
	public static void guardarArchivo() {
		try {

			FileOutputStream fs = new FileOutputStream("Departamento.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fs);

			oos.writeObject(ListaDepart);

			if (oos != null) {
				oos.close();
				fs.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Se han Guardado los datos correctamente");
	}

	/**
	 * Metodo que nos permite cargar los datos que teniamos guardados en el archivo.
	 */
	public static void cargarArchivo() {
		try {
			File f = null;
			ObjectInputStream Obj = null;
			FileInputStream fe = null;
			try {
				f = new File("Departamento.dat");
				if (f.exists()) {
					fe = new FileInputStream(f);
					Obj = new ObjectInputStream(fe);
					while (true) {
						ListaDepart = (ArrayList<Departamento>) Obj.readObject();
					}
				}
			} catch (EOFException eof) {
				System.out.println("");
			} catch (FileNotFoundException fnf) {
				System.err.println("Fichero no encontrado " + fnf);
			} catch (IOException e) {
				System.err.println("Se ha producido una IOException");
				e.printStackTrace();
			} catch (Throwable e) {
				System.err.println("Error de programa: " + e);
				e.printStackTrace();
			} finally {
				if (Obj != null) {
					Obj.close();
					fe.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
		if (!ListaDepart.isEmpty()) {
			System.out.println("Se han cargado Datos anteriores.");
		}

	}

	/**
	 * Metodo que nos Avisa si al querer salir del programa no hemos guardado
	 * cualquier cambio que hayamos hecho previamente.
	 * 
	 * @throws IOException
	 */
	public static void comprobarGuardado() throws IOException {
		ArrayList<Departamento> ListaTemporal = new ArrayList<>();
		try {
			File f = null;
			ObjectInputStream Obj = null;
			FileInputStream fe = null;
			try {
				f = new File("Departamento.dat");
				if (f.exists()) {
					fe = new FileInputStream(f);
					Obj = new ObjectInputStream(fe);
					while (true) {
						ListaTemporal = (ArrayList<Departamento>) Obj.readObject();
					}
				}
			} catch (EOFException eof) {
				System.out.println("");
			} catch (FileNotFoundException fnf) {
				System.err.println("Fichero no encontrado " + fnf);
			} catch (IOException e) {
				System.err.println("Se ha producido una IOException");
				e.printStackTrace();
			} catch (Throwable e) {
				System.err.println("Error de programa: " + e);
				e.printStackTrace();
			} finally {
				if (Obj != null) {
					Obj.close();
					fe.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!ListaDepart.equals(ListaTemporal)) {
			System.out.println("Ha realizado cambios que no ha guardado en disco.\n"
					+ "Si continua se perderan los cambios no guardados.\n"
					+ "¿Desea continuar y guardar los datos en disco? (S/N)");

			String confirmar_accion = ex.textosinnumeros(sc.nextLine());
			if (confirmar_accion.equalsIgnoreCase("S")) {
				guardarArchivo();
				System.out.println("Se han guardado los datos. Saliendo Del Programa ...");
			} else if (confirmar_accion.equalsIgnoreCase("N")) {
				System.out.println("No se han guardado los datos. Saliendo del Programa ...");
			} else {
				System.out.println("Introduzca la opcion correcta");
			}
		} else {
			System.out.println("Saliendo del Programa ...");
		}

	}

	/**
	 * Metodo que nos permite restablecer los datos que teniamos guardados en el
	 * archivo y borrara los datos actuales por estos mismos.
	 * 
	 * @throws IOException
	 */
	public static void recuperarDatos() throws IOException {
		ArrayList<Departamento> ListaTemporal = new ArrayList<>();
		try {
			File f = null;
			ObjectInputStream Obj = null;
			FileInputStream fe = null;
			try {
				f = new File("videojue.dat");
				if (f.exists()) {
					fe = new FileInputStream(f);
					Obj = new ObjectInputStream(fe);
					while (true) {
						ListaTemporal = (ArrayList<Departamento>) Obj.readObject();
					}
				}
			} catch (EOFException eof) {
				System.out.println("");
			} catch (FileNotFoundException fnf) {
				System.err.println("Fichero no encontrado " + fnf);
			} catch (IOException e) {
				System.err.println("Se ha producido una IOException");
				e.printStackTrace();
			} catch (Throwable e) {
				System.err.println("Error de programa: " + e);
				e.printStackTrace();
			} finally {
				if (Obj != null) {
					Obj.close();
					fe.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!ListaDepart.equals(ListaTemporal)) {
			System.out.println("Ha realizado cambios que no ha guardado en disco.\n"
					+ "Si continua la carga del archivo se restauraran los datos\n"
					+ "de disco y se perderan los cambios no guardados.\n"
					+ "¿Desea continuar con la carga y restaurar los datos del archivo? (S/N)");

			String confirmar_accion = ex.textosinnumeros(sc.nextLine());
			if (confirmar_accion.equalsIgnoreCase("S")) {
				cargarArchivo();
				System.out.println("Se han restaurado los datos.");
			} else if (confirmar_accion.equalsIgnoreCase("N")) {
				System.out.println("No se han restaurado los datos.");
			} else {
				System.out.println("Introduzca la opcion correcta");
			}

		}

	}

	/**
	 * Metodo que nos permite borrar el departamento que queramos.
	 * 
	 * @throws IOException
	 */
	public static void BorrarDepartamento() throws IOException {
		Departamento tempo = new Departamento();
		boolean comprobar = false;
		if (ListaDepart.isEmpty()) {
			System.out.println("No existe ningun Empleado");
		} else {
			leerDepartamento();
			System.out.println("Introduce el numero de el departamento que desees eliminar:");
			System.out.println("");
			do {
				int busqueda = ex.controlInt();
				tempo = ListaDepart.stream().filter(v -> v.getNumDepart() == busqueda).findFirst()
						.orElse(new Departamento());
				if (tempo.getNombre() != null) {
					ListaDepart.remove(tempo);
					System.out.println("El departamento fue eliminado con exito");
					System.out.println("");
					leerDepartamento();
					System.out.println("");
					comprobar = false;
				} else {
					System.out.println("No existe ningun departamento con ese número. Pruebe otra vez:");
					comprobar = true;
				}

			} while (comprobar == true);
		}

	}

	/**
	 * Metodo que nos permite modificar el departamento que queramos
	 * 
	 * @throws IOException
	 */
	public static void modDepartamento() throws IOException {
		Departamento tempo = new Departamento();

		boolean comprobar = false;
		String nombreTemp, nuevoNombre;
		String localidadTemp, nuevaLocalidad;
		if (ListaDepart.isEmpty()) {
			System.out.println("No existe ningun Empleado");
		} else {
			leerDepartamento();
			System.out.println("Introduce el numero de el departamento que desees Modificar:");
			System.out.println("");

			do {
				int busqueda = ex.controlInt();
				tempo = ListaDepart.stream().filter(v -> v.getNumDepart() == busqueda).findFirst()
						.orElse(new Departamento());
				if (tempo.getNombre() != null) {
					nombreTemp = tempo.getNombre();
					localidadTemp = tempo.getLocalidad();
					comprobar = false;
				} else {
					System.out.println("No existe ningun departamento con ese número. Pruebe otra vez:");
					comprobar = true;
				}
			} while (comprobar == true);

			System.out.println("Introduce el Nuevo nombre de departamento");
			nuevoNombre = sc.nextLine();
			System.out.println("Introduce la nueva localidad de departamento");
			nuevaLocalidad = sc.nextLine();
			System.out.println("¡Atencion! Esta modificando el Departamento " + tempo.getNumDepart() + " con Nombre "
					+ tempo.getNombre() + "\n" + " y Localidad " + tempo.getLocalidad() + " que ahora tendra el nombre "
					+ nuevoNombre + " y la localidad de " + nuevaLocalidad + "\n"
					+ " ¿Seguro que deseea modificar este departamento (S/N)? ");

			String confirmar_accion = ex.textosinnumeros(sc.nextLine());
			if (confirmar_accion.equalsIgnoreCase("S")) {
				tempo.setNombre(nuevoNombre);
				tempo.setLocalidad(nuevaLocalidad);
				System.out.println("El departamento ha sido Modificado correctamente.");
			} else if (confirmar_accion.equalsIgnoreCase("N")) {
				System.out.println("El departamento no ha sido modificado.");
			} else {
				System.out.println("Introduzca la opcion correcta");
			}
		}

	}

}