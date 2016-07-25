package ar.edu.dds.tpa.procesos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.manejoDeArchivos.*;

public class ActualizarPOIs extends Proceso {

	private Buscador buscador;
	private Archivo miArchivo;

	public ActualizarPOIs(Buscador buscador, String pathArchivo) {
		super();
		this.buscador = buscador;
		this.miArchivo = new Archivo(pathArchivo);
	}

	public void ejecutar() {

		String nombreDeFantasia;
		this.abrirArchivo();

		while (miArchivo.distintoDeFinDeArchivo()) {
			nombreDeFantasia = leerNombreDeFantasia();
			this.actualizarPalabrasClaves(nombreDeFantasia);
		}

	}

	public void actualizarPalabrasClaves(String nombreDeFantasia) {

		List<PuntoDeInteres> pisEncontrados = new ArrayList<>();

		pisEncontrados = buscador.buscar(nombreDeFantasia, null);

		if (hayPuntosDeInteres(pisEncontrados)) {

			borrarPalabrasClaves(pisEncontrados);

			while (miArchivo.hayPalabrasClaves()) {

				String palabraClave;

				palabraClave = leerPalabraClave();

				setearPalabraClave(pisEncontrados, palabraClave);

			}

		} else {

			saltearLineaDeArchivo();
		}

	}

	public void saltearLineaDeArchivo() {
		this.leerPalabra('\n');
	}

	public void setearPalabraClave(List<PuntoDeInteres> pisEncontrados, String palabraClave) {
		pisEncontrados.stream().forEach(p1 -> p1.agregarPalabraClave(palabraClave));
	}

	public String leerPalabraClave() {
		return this.leerPalabra(' ');
	}

	public void borrarPalabrasClaves(List<PuntoDeInteres> pisEncontrados) {
		pisEncontrados.stream().forEach(p1 -> p1.borrarPalabrasClaves());
	}

	public boolean hayPuntosDeInteres(List<PuntoDeInteres> pisBuscados) {
		return pisBuscados.size() != 0;
	}

	public String leerNombreDeFantasia() {
		return this.leerPalabra(';');
	}

	public String leerPalabra(char condicionDeCorte) {

		try {
			return miArchivo.leerPalabra(condicionDeCorte);

		} catch (IOException e) {
			fallar();
		}
		return null;

	}

	public void abrirArchivo() {

		try {

			miArchivo.abrirArchivo();

		} catch (FileNotFoundException e) {
			fallar();
		}

	}

}