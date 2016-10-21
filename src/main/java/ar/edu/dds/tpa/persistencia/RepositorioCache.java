package ar.edu.dds.tpa.persistencia;

public interface RepositorioCache {

	public String consultarValorPor(String unaClave);

	public void insertar(String unaClave, String unValor);
}
