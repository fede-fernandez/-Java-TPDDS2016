package ar.edu.dds.tpa.server;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.Rubro;
import ar.edu.dds.tpa.model.Servicio;
import ar.edu.dds.tpa.model.Terminal;
import ar.edu.dds.tpa.persistencia.Persistible;

public class CargarPOIs implements Persistible {
	
	public void init(){
		
		List<DayOfWeek>deLunesAViernes = new ArrayList<DayOfWeek>();
		deLunesAViernes.addAll(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
		
		Comuna comuna12 = new Comuna(1, "Comuna 12");
		
		Terminal terminalChacabuco = new Terminal("Terminal UTN Campus", new Posicion(5.0, 5.0), comuna12);

		
		ParadaDeColectivo paradaDel46 = new ParadaDeColectivo("Linea 46 Estacion Mozart",
				new Posicion(53.97583, 12.21985),null);
		paradaDel46.agregarPalabraClave("colectivo");

		Rubro kioscoDeDiarios = new Rubro("Kiosco de Diarios", 10.5);
		LocalComercial localDeDiarios = new LocalComercial("Diarin", new Posicion(734.1523, 751.2312), kioscoDeDiarios,null);

		localDeDiarios.agregarHorarioDeAtencionComunEnVariosDias(deLunesAViernes, LocalTime.of(9, 30),
				LocalTime.of(18, 30));
		
		Banco bancoPatagonia = new Banco("Banco Patagonia Sucursal Villa Fiorito", new Posicion(73.002005, 148.42205),null);
		Servicio extracciones = new Servicio("extracciones");
		extracciones.agregarHorarioDeAtencion(DayOfWeek.WEDNESDAY, LocalTime.of(9, 50), LocalTime.of(23, 45));
		bancoPatagonia.agregarServicio(extracciones);

		repositorio.persistir(terminalChacabuco);
		
		repositorio.persistir(paradaDel46);
		repositorio.persistir(localDeDiarios);
		repositorio.persistir(bancoPatagonia);
	}

}
