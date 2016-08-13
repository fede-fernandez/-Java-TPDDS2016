package ar.edu.dds.tpa.service;

import java.util.List;

import ar.edu.dds.tpa.adapter.model.CentroDTO;

public interface CGPService {
	public List<CentroDTO> getCGPsByCalleOBarrio(String calleOBarrio);
}