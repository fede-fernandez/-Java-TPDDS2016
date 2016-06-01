package componentes_externos;

import java.util.ArrayList;
import java.util.List;

public class BuscadorDeCGPLento_Impostor {
	public BuscadorDeCGPLento_Impostor(){
		
	}
	
	public List<CentroDTO> buscar(List<String> palabrasClave){
		List<CentroDTO> resultado = new ArrayList<CentroDTO>();
		resultado.add(new CentroDTO());
		return resultado;
	}

}
