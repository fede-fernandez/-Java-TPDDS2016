package persistencia;

import java.util.EnumSet;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

public class ExportarTablasSQL {
	public static void main(String[] args) {
		ServiceRegistry serviceRegistry = (StandardServiceRegistryImpl) new StandardServiceRegistryBuilder()
				.configure("Hibernate.xml").build();
		Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
		SchemaExport schemaExport = new SchemaExport();
		schemaExport.setOutputFile("CrearTablas.sql");
		schemaExport.create(EnumSet.of(TargetType.SCRIPT, TargetType.STDOUT), metadata);
		((StandardServiceRegistryImpl) serviceRegistry).destroy();
	}
}