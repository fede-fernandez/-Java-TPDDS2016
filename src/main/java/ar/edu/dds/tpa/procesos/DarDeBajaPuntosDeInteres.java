package ar.edu.dds.tpa.procesos;

import ar.edu.dds.tpa.adapter.BajaDePuntoDeInteresServiceConverter;
import ar.edu.dds.tpa.persistencia.repository.Mapa;
import ar.edu.dds.tpa.procesos.Proceso;
import ar.edu.dds.tpa.service.BajaDePuntoDeInteresService;

public class DarDeBajaPuntosDeInteres extends Proceso {

	private BajaDePuntoDeInteresService servicioDeBajaDePuntosDeInteres;
	private Mapa mapa;

	public void setServicioDeBajaDePuntosDeInteres(BajaDePuntoDeInteresService servicioDeBajaDePuntosDeInteres) {
		this.servicioDeBajaDePuntosDeInteres = servicioDeBajaDePuntosDeInteres;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public void ejecutar() {
		try {
			BajaDePuntoDeInteresServiceConverter
					.obtenerPuntosDeInteresADarDeBajaDelServicio(
							servicioDeBajaDePuntosDeInteres.obtenerPuntosDeInteresADarDeBaja())
					.forEach(unPuntoDeInteresADarDeBaja -> mapa.darDeBaja(unPuntoDeInteresADarDeBaja.getId(),
							unPuntoDeInteresADarDeBaja.getFechaDeBaja()));
		} 
		catch (Exception e) {
			fallar();
		}
	}
}