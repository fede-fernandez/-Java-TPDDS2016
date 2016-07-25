package ar.edu.dds.tpa.manejoDeArchivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Archivo {
	private char ultimoCaracterLeido;
	private BufferedReader buffer;
	private String pathArchivo;

	public Archivo(String pathArchivo) {
		this.pathArchivo = pathArchivo;
	}

	public BufferedReader abrirArchivo() throws FileNotFoundException {

		FileReader archivo;

		archivo = new FileReader(pathArchivo);

		buffer = new BufferedReader(archivo);
		return buffer;

	}

	public String leerPalabra(char condicionDeCorte) throws IOException {
		String palabraClave = "";
		int caracter = 1;

		caracter = buffer.read();

		do {

			palabraClave = palabraClave + (char) caracter;

			caracter = buffer.read();

		} while (caracter != (-1) && caracter != condicionDeCorte);

		ultimoCaracterLeido = (char) caracter;

		if (caracter == (-1)) {
			ultimoCaracterLeido = 'f';
		}

		return palabraClave;

	}

	public boolean distintoDeFinDeArchivo() {
		return ultimoCaracterLeido != 'f';
	}

	public boolean hayPalabrasClaves() {
		return ultimoCaracterLeido != '\n' && ultimoCaracterLeido != 'f';
	}

}
