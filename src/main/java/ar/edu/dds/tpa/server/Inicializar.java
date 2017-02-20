package ar.edu.dds.tpa.server;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.PuntosDeInteresEncontrados;
import ar.edu.dds.tpa.model.Rubro;
import ar.edu.dds.tpa.model.Servicio;
import ar.edu.dds.tpa.model.usuario.Administrador;
import ar.edu.dds.tpa.model.usuario.Terminal;
import ar.edu.dds.tpa.persistencia.MorphiaDatastoreMock;
import ar.edu.dds.tpa.persistencia.Persistible;
import ar.edu.dds.tpa.persistencia.repository.historial.HistorialDeBusqueda;
import ar.edu.dds.tpa.persistencia.repository.historial.HistorialDeBusquedaEnMongo;

public class Inicializar implements Persistible {

	public void init() {

		HistorialDeBusqueda historial = new HistorialDeBusquedaEnMongo(
				MorphiaDatastoreMock.obtenerInstancia().getDatastore());

		List<DayOfWeek> deLunesAViernes = new ArrayList<DayOfWeek>();
		deLunesAViernes.addAll(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));

		Comuna comuna12 = new Comuna(8, "Lugano");
		Comuna flores = new Comuna(7, "Flores");
		Comuna caballito = new Comuna(9, "Caballito");
		Comuna mataderos = new Comuna(14, "Mataderos");

		
		Terminal terminalUTN = new Terminal("Terminal UTN Campus", new Posicion(5.0, 5.0), comuna12);
		terminalUTN.setUsuario("utn");
		terminalUTN.setPassword("1234");		
		Terminal terminalDePrueba1 = new Terminal("Terminal Subte Linea E Virreyes", new Posicion(2.42300, 2.04212),flores);
		Terminal terminalDePrueba2 = new Terminal("Terminal Eva Peron y Varela", new Posicion(1.9353, 9.3493), flores);
		Terminal terminalDePrueba3 = new Terminal("Terminal Acoyte y Rivadavia", new Posicion(15.15, 9.3848),caballito);	
		terminalDePrueba3.setUsuario("terminalDePrueba3");
		terminalDePrueba3.setPassword("1234");
		Terminal terminalDePrueba4 = new Terminal("Terminal Central Olivera", new Posicion(8.3, 8.7), mataderos);


		Administrador administrador = new Administrador("admin@admin.com", "admin", "admin");

		ParadaDeColectivo paradaDel46 = new ParadaDeColectivo("Linea 46 Estacion Mozart",
				new Posicion(53.97583, 12.21985), "calle falsa 123");
		paradaDel46.agregarPalabraClave("colectivo");

		Rubro kioscoDeDiarios = new Rubro("Kiosco de Diarios", 10.5);
		LocalComercial localDeDiarios = new LocalComercial("Diarin", new Posicion(734.1523, 751.2312), kioscoDeDiarios,
				"calle kiosco 123");

		localDeDiarios.agregarHorarioDeAtencionComunEnVariosDias(deLunesAViernes, LocalTime.of(9, 30),
				LocalTime.of(18, 30));

		Banco bancoPatagonia = new Banco("Banco Patagonia Sucursal Villa Fiorito", new Posicion(73.002005, 148.42205),
				"calle banco 123");
		Servicio extracciones = new Servicio("extracciones");
		extracciones.agregarHorarioDeAtencion(DayOfWeek.WEDNESDAY, LocalTime.of(9, 50), LocalTime.of(23, 45));
		bancoPatagonia.agregarServicio(extracciones);

		LocalDate cuatroDeFebreroDe2016 = LocalDate.of(2016, Month.FEBRUARY, 4);
		LocalDate diezDeEneroDe2016 = LocalDate.of(2016, Month.JANUARY, 10);

		Busqueda busqueda1 = new Busqueda(terminalUTN, "Banco",
				new PuntosDeInteresEncontrados(Arrays.asList(bancoPatagonia,paradaDel46)), cuatroDeFebreroDe2016, 5.5);
		Busqueda busqueda2 = new Busqueda(terminalUTN, "46", new PuntosDeInteresEncontrados(Arrays.asList(paradaDel46)),
				diezDeEneroDe2016, 1.1);
		Busqueda busqueda3 = new Busqueda(terminalUTN, "colectivo",
				new PuntosDeInteresEncontrados(Arrays.asList(paradaDel46,paradaDel46)), cuatroDeFebreroDe2016, 2.0);
		
		Busqueda busqueda = new Busqueda(terminalDePrueba2, "colectivo",
				new PuntosDeInteresEncontrados(Arrays.asList(paradaDel46,localDeDiarios)), cuatroDeFebreroDe2016, 10.4);
				

		repositorio.persistir(administrador);
		repositorio.persistir(terminalUTN);
		repositorio.persistir(terminalDePrueba1);
		repositorio.persistir(terminalDePrueba2);
		repositorio.persistir(terminalDePrueba3);
		repositorio.persistir(terminalDePrueba4);


		repositorio.persistir(paradaDel46);
		repositorio.persistir(localDeDiarios);
		repositorio.persistir(bancoPatagonia);
		
//		historial.registrarBusqueda(busqueda);
//		historial.registrarBusqueda(busqueda1);
//		historial.registrarBusqueda(busqueda2);
		historial.registrarBusqueda(busqueda3);
		

	
	}

}