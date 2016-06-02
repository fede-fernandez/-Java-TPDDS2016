package componentes_externos;

import java.util.ArrayList;
import java.util.List;

public class BuscadorDeCGPLento extends BuscadorExterno {

	protected List<PuntosDTO> centros = new ArrayList<>();

	public BuscadorDeCGPLento(){
		this.puntosDTO = this::getCentros;
	}
	
	public List<PuntosDTO> getCentros() {
		return centros;
	}

	private void addCentro(CentroDTO centro) {
		this.centros.add(centro);
	}
	
	


}
