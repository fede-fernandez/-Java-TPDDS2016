package ar.edu.dds.tpa.adapter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.adapter.model.CentroDTO;
import ar.edu.dds.tpa.adapter.model.RangoServicioDTO;
import ar.edu.dds.tpa.adapter.model.ServicioDTO;
import ar.edu.dds.tpa.model.CGP;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.Servicio;
import ar.edu.dds.tpa.service.CGPService;

public class BuscadorDeCGPs implements BuscadorExterno {
	private CGPService servicioDeConsultaDeCGPs;

	public BuscadorDeCGPs(CGPService servicioDeConsultaDeCGPs) {
		this.servicioDeConsultaDeCGPs = servicioDeConsultaDeCGPs;
	}

	@Override
	public List<PuntoDeInteres> buscarPuntosDeInteresDelServicio(List<String> palabrasDeBusqueda) {
		List<PuntoDeInteres> puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();

		palabrasDeBusqueda.forEach(unaPalabraDeBusqueda -> puntosDeInteresEncontrados.addAll(
				obtenerCGPsDeListaCentrosDTO(servicioDeConsultaDeCGPs.getCGPsByCalleOBarrio(unaPalabraDeBusqueda))));

		return puntosDeInteresEncontrados.stream().distinct().collect(Collectors.toList());
	}

	public List<CGP> obtenerCGPsDeListaCentrosDTO(List<CentroDTO> centrosDTO) {
		List<CGP> cgps = new ArrayList<CGP>();
		centrosDTO.forEach(unCGPDelServicio -> cgps.add(convertirCGPDelServicioACGP(unCGPDelServicio)));
		return cgps;
	}

	public CGP convertirCGPDelServicioACGP(CentroDTO cgpDelServicio) {
		String nombreDelCGP = "Centros de Gestion y Participacion CGP N° " + cgpDelServicio.getComuna();
		CGP cgp = new CGP(nombreDelCGP, null);
		cgpDelServicio.getServiciosDTO()
				.forEach(unServicioDTO -> cgp.agregarServicio(convertirServicioDTOAServicio(unServicioDTO)));
		return cgp;
	}

	public Servicio convertirServicioDTOAServicio(ServicioDTO servicioDelServicio) {
		Servicio servicio = new Servicio(servicioDelServicio.getNombreServicio());
		servicioDelServicio.getRangosServicioDTO().forEach(
				unRangoDeHorarioDTO -> convertirRangoServicioDTOAHorarioDeAtencion(unRangoDeHorarioDTO, servicio));
		return servicio;
	}

	public void convertirRangoServicioDTOAHorarioDeAtencion(RangoServicioDTO horarioDeAtencionDelServicio,
			Servicio servicio) {
		DayOfWeek dia = DayOfWeek.of(horarioDeAtencionDelServicio.getNumeroDia());
		LocalTime horarioDesde = LocalTime.of(horarioDeAtencionDelServicio.getHorarioDesde(),
				horarioDeAtencionDelServicio.getMinutosDesde());
		LocalTime horarioHasta = LocalTime.of(horarioDeAtencionDelServicio.getHorarioHasta(),
				horarioDeAtencionDelServicio.getMinutosHasta());
		servicio.agregarHorarioDeAtencion(dia, horarioDesde, horarioHasta);
	}
}