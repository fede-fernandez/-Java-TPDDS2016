package ar.edu.dds.tpa.model;


import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

public class ServicioPuntosDeInteresBajados {
	
	protected String puntosDeInteresJSON;

	public List<BajaPuntoDeInteres> obtenerPuntosABajar() {
		Gson gson = new Gson();
		return gson.fromJson(puntosDeInteresJSON, new TypeToken<List<BajaPuntoDeInteres>>(){}.getType());		
	}

}
