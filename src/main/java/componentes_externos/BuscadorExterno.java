package componentes_externos;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.PuntoDeInteres;

public abstract class BuscadorExterno {

	@FunctionalInterface
	protected static interface ObtenerPuntosDeInteres{
		List<? extends PuntosDTO> getPuntosDTO();
	}
	
	public ObtenerPuntosDeInteres puntosDTO;
	
	public void buscar(List<String> palabrasClave, Mapa mapa) {
		// TODO Buscar en el componente externo
		mapa.busquedaTerminada(this);
	}

	public List<PuntoDeInteres> getPuntosDeInteres() {
		return this.puntosDTO.getPuntosDTO().stream().map(PuntosDTO::toPuntoDeInteres).collect(Collectors.toList());
	}
}
