package componentes_externos;

import java.util.ArrayList;
import java.util.List;

public class BuscadorDeCGPLento extends BuscadorExterno {

	protected List<CentroDTO> centros = new ArrayList<>();

	public BuscadorDeCGPLento(){
		this.puntosDTO = this::getCentros;
	}
	
	public List<CentroDTO> getCentros() {
		return centros;
	}
}
