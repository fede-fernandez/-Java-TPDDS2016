package ar.edu.dds.tpa.adapter;

import java.util.List;

import ar.edu.dds.tpa.adapter.model.CentroDTO;
import ar.edu.dds.tpa.model.CGP;

public interface CGPServiceAdapter {
	public List<CGP> obtenerCGPsDeListaCentrosDTO(List<CentroDTO> centrosDTO);
}