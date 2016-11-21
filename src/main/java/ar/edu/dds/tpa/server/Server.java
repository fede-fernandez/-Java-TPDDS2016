package ar.edu.dds.tpa.server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {

	public static void main(String[] args) {
		new Inicializar().init();
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}
}