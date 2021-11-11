package com.bolsadeideas.springboot.web.form.services;

import java.util.List;

import com.bolsadeideas.springboot.web.form.models.domain.Pais;

public interface IPaisServices {

	public List<Pais> listar();
	public Pais obtenerPorId(Integer id);
}
