package ar.edu.dds.tpa.procesos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.PuntoDeInteres;


public class ActualizarPOIs extends Proceso {

	private char caracterRetorno;
	private Buscador buscador;
	private BufferedReader buffer;
	private String pathArchivo;

	public ActualizarPOIs(Buscador buscador, String pathArchivo) {
		super();
		this.buscador = buscador;
		this.pathArchivo = pathArchivo;
	}
	


	public void ejecutar() {

		String nombreDeFantasia;
		buffer = this.abrirArchivo();

		while (caracterRetorno != 'f') {
			nombreDeFantasia = this.leerPalabra(';');
			this.actualizarPalabrasClaves(nombreDeFantasia);
		}

	}

	public void actualizarPalabrasClaves(String nombreDeFantasia) {

		List<PuntoDeInteres> pisBuscados = new ArrayList<>();

		pisBuscados = buscador.buscar(nombreDeFantasia,null);

		if (pisBuscados.size() != 0) {

			pisBuscados.stream().forEach(p1 -> p1.borrarPalabrasClaves());
			// borrar todas las palabras claves de esos punto de interes

			while (caracterRetorno != '\n' && caracterRetorno != 'f') {

				String palabraClave;

				palabraClave = this.leerPalabra(' ');
				// armar palabra clave

				pisBuscados.stream().forEach(p1 -> p1.agregarPalabraClave(palabraClave));

			}

		} else {
			this.leerPalabra('\n');
			// saltear linea
		}

	}

	public String leerPalabra(char condicionDeCorte) {
		String palabraClave = "";
		int caracter = 1;

		try {
			caracter = buffer.read();

			do {

				palabraClave = palabraClave + (char) caracter;

				caracter = buffer.read();

			} while (caracter != (-1) && caracter != condicionDeCorte);

		} catch (IOException e) {
			fallar();
		}

		caracterRetorno = (char) caracter;

		if (caracter == (-1)) {
			caracterRetorno = 'f';
		}

		return palabraClave;

	}

	public BufferedReader abrirArchivo() {

		FileReader archivo;
		try {
			archivo = new FileReader(pathArchivo);

			buffer = new BufferedReader(archivo);
			return buffer;

		} catch (FileNotFoundException e) {
			fallar();

		}

		return null;

	}

}