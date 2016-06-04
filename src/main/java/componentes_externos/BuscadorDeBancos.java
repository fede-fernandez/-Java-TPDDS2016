package componentes_externos;

import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class BuscadorDeBancos extends BuscadorExterno {

	protected List<BancoDTO> bancos = new ArrayList<>();
	
	public BuscadorDeBancos(){
		this.puntosDTO = this::getBancos;
	}
	
	public List<BancoDTO> getBancos(){
		return bancos;
	}
	
	public void parsearJSON(String BancosJSON){
		Gson gson = new Gson();
		bancos.addAll(gson.fromJson(BancosJSON, new TypeToken<List<BancoDTO>>(){}.getType()));
	}
	
}
