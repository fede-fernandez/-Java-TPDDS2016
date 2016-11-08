package ar.edu.dds.tpa.server;

import java.time.DayOfWeek;
import java.time.LocalTime;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.Rubro;
import ar.edu.dds.tpa.model.Servicio;
import ar.edu.dds.tpa.persistencia.Persistible;

public class CargarPOIs implements Persistible {
	
	public void init(){
		
		ParadaDeColectivo paradaDel46 = new ParadaDeColectivo("Linea 46 Estacion Mozart",
				new Posicion(53.97583, 12.21985));

		Rubro kioscoDeDiarios = new Rubro("Kiosco de Diarios", 10.5);
		LocalComercial localDeDiarios = new LocalComercial("Diarin", new Posicion(734.1523, 751.2312), kioscoDeDiarios);

		Banco bancoPatagonia = new Banco("Banco Patagonia Sucursal Villa Fiorito", new Posicion(73.002005, 148.42205));
		Servicio extracciones = new Servicio("extracciones");
		extracciones.agregarHorarioDeAtencion(DayOfWeek.WEDNESDAY, LocalTime.of(9, 50), LocalTime.of(23, 45));
		bancoPatagonia.agregarServicio(extracciones);

		repositorio.persistir(paradaDel46);
		repositorio.persistir(localDeDiarios);
		repositorio.persistir(bancoPatagonia);
	}

}
