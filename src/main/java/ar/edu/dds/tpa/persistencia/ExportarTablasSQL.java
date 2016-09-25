package ar.edu.dds.tpa.persistencia;

import java.io.File;
import java.util.EnumSet;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

public class ExportarTablasSQL {
	
	private static ServiceRegistry serviceRegistry;
	private static Metadata metadata;
	private static SchemaExport schemaExport;
	private static String rutaDeArchivoDeConfiguracionDeHibernate = "Hibernate.xml";
	private static String rutaDelScript = "CrearTablas.sql";

	public static void main(String[] args) {
		limpiarScriptSiExiste();
		obtenerConfiguracionDeHibernate();
		configurarScript();
		exportarScript();
	}

	public static void limpiarScriptSiExiste() {
		File script = new File(rutaDelScript);
		script.delete();
	}

	public static void obtenerConfiguracionDeHibernate() {
		serviceRegistry = new StandardServiceRegistryBuilder().configure(rutaDeArchivoDeConfiguracionDeHibernate)
				.build();
		metadata = new MetadataSources(serviceRegistry).buildMetadata();
	}

	public static void configurarScript() {
		schemaExport = new SchemaExport();
		schemaExport.setOutputFile(rutaDelScript);
		schemaExport.setDelimiter(";");
		schemaExport.setFormat(true);
	}

	public static void exportarScript() {
		schemaExport.create(EnumSet.of(TargetType.SCRIPT, TargetType.STDOUT), metadata);
		((StandardServiceRegistryImpl) serviceRegistry).destroy();
	}
}