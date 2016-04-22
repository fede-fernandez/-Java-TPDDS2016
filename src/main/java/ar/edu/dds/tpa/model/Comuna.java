package ar.edu.dds.tpa.model;

import java.util.List;

import org.uqbar.geodds.*;;

public class Comuna {
	
	Polygon comuna;
	
	
	public Comuna(List<Point> vertices){
		comuna = new Polygon(vertices);
	}
	
	public Boolean estaEnComuna(Point coordenadas){
		return comuna.isInside(coordenadas);
	}
	

}
