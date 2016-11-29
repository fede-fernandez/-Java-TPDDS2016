package ar.edu.dds.tpa.adapter;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;

import ar.edu.dds.tpa.adapter.model.PuntoDeInteresADarDeBaja;

public class BajaDePuntoDeInteresServiceConverter {

	public static Set<PuntoDeInteresADarDeBaja> obtenerPuntosDeInteresADarDeBajaDelServicio(
			String bajaDePuntoDeInteresEnJSON) {
		Set<PuntoDeInteresADarDeBaja> puntosDeInteresADarDeBaja = new HashSet<PuntoDeInteresADarDeBaja>();
		if (!bajaDePuntoDeInteresEnJSON.equals("[]")) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
					(JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> ZonedDateTime
							.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime())
					.create();
			Type setDePuntosDeInteresADarDeBaja = new TypeToken<Set<PuntoDeInteresADarDeBaja>>() {
			}.getType();
			puntosDeInteresADarDeBaja = gson.fromJson(bajaDePuntoDeInteresEnJSON, setDePuntosDeInteresADarDeBaja);
		}
		return puntosDeInteresADarDeBaja;
	}
}