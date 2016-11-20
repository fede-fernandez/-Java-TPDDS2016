package ar.edu.dds.tpa.server;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.dds.tpa.datastoreTest.MorphiaDatastoreTest;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.historial.HistorialDeBusqueda;
import ar.edu.dds.tpa.historial.HistorialDeBusquedaEnMongo;
import ar.edu.dds.tpa.model.Administrador;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.PuntosDeInteresEncontrados;
import ar.edu.dds.tpa.model.Rubro;
import ar.edu.dds.tpa.model.Servicio;
import ar.edu.dds.tpa.model.Terminal;
import ar.edu.dds.tpa.persistencia.Persistible;

public class Inicializar implements Persistible {
	
	public void init(){
		
		HistorialDeBusqueda historial = new HistorialDeBusquedaEnMongo(MorphiaDatastoreTest.obtenerInstancia().getDatastore());
		
		List<DayOfWeek>deLunesAViernes = new ArrayList<DayOfWeek>();
		deLunesAViernes.addAll(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
		
		Comuna comuna12 = new Comuna(1, "Comuna 12");
		
		Terminal terminalUTN = new Terminal("Terminal UTN Campus", new Posicion(5.0, 5.0), comuna12);
		terminalUTN.setUsuario("utn");
		terminalUTN.setPassword("1234");
		
		Administrador administrador = new Administrador("admin@admin.com");
		administrador.setUsuario("admin");
		administrador.setPassword("admin");
		
		

		
		ParadaDeColectivo paradaDel46 = new ParadaDeColectivo("Linea 46 Estacion Mozart",
				new Posicion(53.97583, 12.21985),"calle falsa 123");
		paradaDel46.agregarPalabraClave("colectivo");

		Rubro kioscoDeDiarios = new Rubro("Kiosco de Diarios", 10.5);
		LocalComercial localDeDiarios = new LocalComercial("Diarin", new Posicion(734.1523, 751.2312), kioscoDeDiarios,"calle kiosco 123");

		localDeDiarios.agregarHorarioDeAtencionComunEnVariosDias(deLunesAViernes, LocalTime.of(9, 30),
				LocalTime.of(18, 30));
		
		Banco bancoPatagonia = new Banco("Banco Patagonia Sucursal Villa Fiorito", new Posicion(73.002005, 148.42205),"calle banco 123");
		Servicio extracciones = new Servicio("extracciones");
		extracciones.agregarHorarioDeAtencion(DayOfWeek.WEDNESDAY, LocalTime.of(9, 50), LocalTime.of(23, 45));
		bancoPatagonia.agregarServicio(extracciones);
		
				
			
		LocalDate cuatroDeFebreroDe2016 = LocalDate.of(2016, Month.FEBRUARY, 4);
		LocalDate diezDeEneroDe2016 = LocalDate.of(2016, Month.JANUARY, 10);
		

		Busqueda busqueda1 = new Busqueda(terminalUTN, "Banco", new PuntosDeInteresEncontrados(Arrays.asList(bancoPatagonia)), cuatroDeFebreroDe2016, 5.5);
		Busqueda busqueda2 = new Busqueda(terminalUTN, "46", new PuntosDeInteresEncontrados(Arrays.asList(paradaDel46)),diezDeEneroDe2016,1.1);
		Busqueda busqueda3 = new Busqueda(terminalUTN, "colectivo", new PuntosDeInteresEncontrados(Arrays.asList(paradaDel46)), cuatroDeFebreroDe2016, 2.0);
		
//		Busqueda busqueda2;
//		Busqueda busqueda3;
//		Busqueda busqueda4;
//		Busqueda busqueda5;
//
//		LocalComercial localComercial;
//		 ParadaDeColectivo paradaDeColectivo;
//		 Banco banco;
//		 List<PuntoDeInteres> poisEncontrados1;
//		 List<PuntoDeInteres> poisEncontrados2;
//		 List<PuntoDeInteres> poisEncontrados3;
//		 List<PuntoDeInteres> poisEncontrados4;
//				
//		
//		
//		
//		
//		localComercial = new LocalComercial();
//		paradaDeColectivo = new ParadaDeColectivo();
//		banco = new Banco();
//		poisEncontrados1 = new ArrayList<PuntoDeInteres>();
//		poisEncontrados2 = new ArrayList<PuntoDeInteres>();
//		poisEncontrados3 = new ArrayList<PuntoDeInteres>();
//		poisEncontrados1.add(banco);
//		poisEncontrados1.add(paradaDeColectivo);
//		poisEncontrados2.add(localComercial);
//		poisEncontrados2.add(banco);
//		poisEncontrados3.add(paradaDeColectivo);
//		poisEncontrados3.add(banco);
//		poisEncontrados3.add(localComercial);
//		poisEncontrados4 = Arrays.asList(banco,paradaDeColectivo);
//
//		
//		busqueda2 = new Busqueda(terminalChacabuco, "Florida", new PuntosDeInteresEncontrados(poisEncontrados1), cuatroDeFebreroDe2016, 5.0);
//		busqueda3 = new Busqueda(terminalChacabuco, "Ahorro",  new PuntosDeInteresEncontrados(poisEncontrados2), cuatroDeFebreroDe2016, 5.0);
//		busqueda4 = new Busqueda(terminalChacabuco, "Subte",  new PuntosDeInteresEncontrados(poisEncontrados3), diezDeEneroDe2016, 5.0);
//		busqueda5 = new Busqueda(terminalChacabuco, "Florida", new PuntosDeInteresEncontrados(poisEncontrados4),cincoDeSeptiembreDe2014,10.0);
//		
//		historial.registrarBusqueda(busqueda2);
//		historial.registrarBusqueda(busqueda3);
//		historial.registrarBusqueda(busqueda4);
//		historial.registrarBusqueda(busqueda5);
//		
		
		
		
		
		
		
		

		repositorio.persistir(administrador);
		repositorio.persistir(terminalUTN);
		
		repositorio.persistir(paradaDel46);
		repositorio.persistir(localDeDiarios);
		repositorio.persistir(bancoPatagonia);
		
		historial.registrarBusqueda(busqueda1);
		historial.registrarBusqueda(busqueda2);
		historial.registrarBusqueda(busqueda3);
	}

}
