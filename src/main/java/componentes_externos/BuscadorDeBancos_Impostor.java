package componentes_externos;

import java.util.List;
import java.util.stream.Collectors;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.PuntoDeInteres;

public class BuscadorDeBancos_Impostor extends BuscadorDeBancos {

	private List<String> palabrasClave;
	
	public void agregarBanco(BancoDTO banco){
		this.bancos.add(banco);
	}
	
	@Override
	public void buscar(List<String> palabrasClave, Mapa mapa) {
		// TODO Auto-generated method stub
		this.palabrasClave = palabrasClave;
		super.buscar(palabrasClave, mapa);
	}
	
	@Override
	public List<PuntoDeInteres> getPuntosDeInteres() {
		// TODO Auto-generated method stub
		return super.getPuntosDeInteres().stream().filter(punto -> palabrasClave.stream().anyMatch(palabra -> punto.estaEtiquetadoPor(palabra))).collect(Collectors.toList());
	}
	
	
}
